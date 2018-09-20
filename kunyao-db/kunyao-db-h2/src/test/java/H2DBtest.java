import java.sql.SQLException;

import org.h2.tools.Server;


public class H2DBtest {
	public static void main(String[] args) throws SQLException {
		Server.createTcpServer().start();
	}
}
