/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.kunyao.core.spring.properties;

import org.springframework.context.EnvironmentAware;

/**
 * {@link Object Config} Binder
 *
 * @see EnvironmentAware
 * @since 1.0.0
 */
public interface ConfigBinder extends EnvironmentAware {

    /**
     * Set whether to ignore unknown fields, that is, whether to ignore bind
     * parameters that do not have corresponding fields in the target object.
     * <p>Default is "true". Turn this off to enforce that all bind parameters
     * must have a matching field in the target object.
     *
     * @see #bind
     */
    void setIgnoreUnknownFields(boolean ignoreUnknownFields);

    /**
     * Set whether to ignore invalid fields, that is, whether to ignore bind
     * parameters that have corresponding fields in the target object which are
     * not accessible (for example because of null values in the nested path).
     * <p>Default is "false".
     *
     * @see #bind
     */
    void setIgnoreInvalidFields(boolean ignoreInvalidFields);

    /**
     * Bind the properties to Dubbo Config Object under specified prefix.
     *
     * @param prefix
     * @param config
     */
    <C> void bind(String prefix, C config);
}