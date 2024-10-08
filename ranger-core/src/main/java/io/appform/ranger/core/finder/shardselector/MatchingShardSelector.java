/*
 * Copyright 2015 Flipkart Internet Pvt. Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.appform.ranger.core.finder.shardselector;

import io.appform.ranger.core.finder.serviceregistry.MapBasedServiceRegistry;
import io.appform.ranger.core.model.ServiceNode;
import io.appform.ranger.core.model.ShardSelector;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MatchingShardSelector<T> implements ShardSelector<T, MapBasedServiceRegistry<T>> {

    @Override
    public List<ServiceNode<T>> nodes(Predicate<T> criteria, MapBasedServiceRegistry<T> serviceRegistry) {
        return null == criteria ? serviceRegistry.nodeList() :
            serviceRegistry.nodes()
                .entries()
                .stream()
                .filter(e -> criteria.test(e.getKey()))
                .map(Map.Entry::getValue)
                .toList();
    }
}
