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
package io.appform.ranger.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Service implements Comparable<Service> {
    String namespace;
    String serviceName;

    public String name() {
        return String.format("%s/%s", namespace, serviceName);
    }

    @Override
    public String toString() {
        return name();
    }

    @Override
    public int compareTo(Service service) {
        return namespace.equals(service.getNamespace())
                ? serviceName.compareTo(service.getServiceName())
                : namespace.compareTo(service.getNamespace());
    }
}
