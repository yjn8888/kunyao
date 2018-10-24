package com.kunyao.db.h2.boot.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;

public class H2AutoConfiguration {
	
	@Bean
	public Server h2DBServer() throws SQLException{
		return Server.createTcpServer().start();
	}

}
