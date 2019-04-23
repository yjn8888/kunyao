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
package com.kunyao.core.spring.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

import static com.kunyao.core.constant.SystemConstant.KUNYAO_GITHUB_URL;
import static com.kunyao.core.constant.SystemConstant.LINE_SEPARATOR;
import static com.kunyao.core.constant.SystemConstant.getVersion;
import static com.kunyao.core.constant.logo.kunyao;

/**
 * KunYao Welcome Logo {@link ApplicationListener}
 *
 * @author
 * @see
 * @since 1.0.0
 */
@Slf4j
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class WelcomeLogoApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {


    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        String bannerText = buildBannerText();

        if (log.isInfoEnabled()) {
            log.info(bannerText);
        } else {
            System.out.print(bannerText);
        }

    }


    String buildBannerText() {

        StringBuilder bannerTextBuilder = new StringBuilder();

        bannerTextBuilder
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR)
                .append(kunyao)
                .append(" :: Kunyao Spring Boot::(v").append(getVersion(getClass())).append(") : ")
                .append(KUNYAO_GITHUB_URL)
                .append(LINE_SEPARATOR)
                .append(LINE_SEPARATOR);

        return bannerTextBuilder.toString();

    }

}
