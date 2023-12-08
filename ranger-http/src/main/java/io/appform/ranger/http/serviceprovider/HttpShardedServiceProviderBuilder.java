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
package io.appform.ranger.http.serviceprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.appform.ranger.core.model.NodeDataSink;
import io.appform.ranger.core.model.Service;
import io.appform.ranger.core.serviceprovider.BaseServiceProviderBuilder;
import io.appform.ranger.core.serviceprovider.ServiceProvider;
import io.appform.ranger.http.config.HttpClientConfig;
import io.appform.ranger.http.serde.HttpRequestDataSerializer;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.fluent.Executor;

@Slf4j
public class HttpShardedServiceProviderBuilder<T> extends BaseServiceProviderBuilder<T, HttpShardedServiceProviderBuilder<T>, HttpRequestDataSerializer<T>> {

    private HttpClientConfig clientConfig;
    private ObjectMapper mapper;
    private Executor httpExecutor;

    public HttpShardedServiceProviderBuilder<T> withClientConfiguration(final HttpClientConfig clientConfig) {
        this.clientConfig = clientConfig;
        return this;
    }

    public HttpShardedServiceProviderBuilder<T> withObjectMapper(final ObjectMapper mapper){
        this.mapper = mapper;
        return this;
    }

    public HttpShardedServiceProviderBuilder<T> withHttpExecutor(final Executor executor){
        this.httpExecutor = executor;
        return this;
    }

    @Override
    public ServiceProvider<T, HttpRequestDataSerializer<T>> build() {
        return super.buildProvider();
    }

    @Override
    protected NodeDataSink<T, HttpRequestDataSerializer<T>> dataSink(Service service) {
        return new HttpNodeDataSink<>(service, clientConfig, mapper, httpExecutor);
    }
}
