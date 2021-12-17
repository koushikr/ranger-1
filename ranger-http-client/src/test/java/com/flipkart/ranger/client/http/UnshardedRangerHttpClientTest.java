/*
 * Copyright 2015 Flipkart Internet Pvt. Ltd.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.flipkart.ranger.client.http;

import com.flipkart.ranger.core.model.ServiceNode;
import com.flipkart.ranger.core.units.TestNodeData;
import com.flipkart.ranger.core.utils.RangerTestUtils;
import com.flipkart.ranger.core.utils.TestUtils;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class UnshardedRangerHttpClientTest extends BaseRangerHttpClientTest {

    @Test
    public void testUnshardedRangerHubClient(){
        val client = UnshardedRangerHttpHubClient.<TestNodeData>builder()
                .clientConfig(getHttpClientConfig())
                .namespace("test-n")
                .deserializer(this::read)
                .mapper(getObjectMapper())
                .nodeRefreshIntervalMs(5000)
                .build();
        client.start();

        TestUtils.sleepForSeconds(6);

        val service = RangerTestUtils.getService("test-n", "test-s");
        Optional<ServiceNode<TestNodeData>> node = client.getNode(service);
        Assert.assertTrue(node.isPresent());

        node = client.getNode(service, nodeData -> nodeData.getShardId() == 1);
        Assert.assertTrue(node.isPresent());

        node = client.getNode(service, nodeData -> nodeData.getShardId() == 2);
        Assert.assertFalse(node.isPresent());

        client.stop();
    }
}
