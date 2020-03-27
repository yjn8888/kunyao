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
package com.kunyao.core.spring.boot;

import com.kunyao.core.spring.properties.AbstractConfigBinder;
import com.kunyao.core.spring.properties.ConfigBinder;
import org.springframework.boot.context.properties.bind.BindHandler;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.handler.IgnoreErrorsBindHandler;
import org.springframework.boot.context.properties.bind.handler.NoUnboundElementsBindHandler;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.UnboundElementsSourceFilter;

import static org.springframework.boot.context.properties.source.ConfigurationPropertySources.from;

/**
 * Spring Boot Relaxed {@link ConfigBinder} implementation
 * see org.springframework.boot.context.properties.ConfigurationPropertiesBinder
 *
 */
public class RelaxedConfigBinder extends AbstractConfigBinder {

    @Override
    public <C> void bind(String prefix, C config) {

        // Converts ConfigurationPropertySources
        Iterable<ConfigurationPropertySource> propertySources = from(getPropertySources());

        // Wrap Bindable from Config instance
        Bindable<C> bindable = Bindable.ofInstance(config);

        Binder binder = new Binder(propertySources);

        // Get BindHandler
        BindHandler bindHandler = getBindHandler();

        // Bind
        binder.bind(prefix, bindable, bindHandler);

    }

    private BindHandler getBindHandler() {
        BindHandler handler = BindHandler.DEFAULT;
        if (isIgnoreInvalidFields()) {
            handler = new IgnoreErrorsBindHandler(handler);
        }
        if (!isIgnoreUnknownFields()) {
            UnboundElementsSourceFilter filter = new UnboundElementsSourceFilter();
            handler = new NoUnboundElementsBindHandler(handler, filter);
        }
        return handler;
    }
}
