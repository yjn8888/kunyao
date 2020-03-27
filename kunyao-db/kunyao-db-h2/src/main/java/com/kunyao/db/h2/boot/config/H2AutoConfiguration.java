package com.kunyao.db.h2.boot.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author The little blacksmith
 * @Date 2020/3/26 11:23
 * @Version 1.0
 */
@Configuration
public class H2AutoConfiguration {

    @Bean
    public Server h2DBServer() throws SQLException {
        return Server.createTcpServer().start();
    }

}
