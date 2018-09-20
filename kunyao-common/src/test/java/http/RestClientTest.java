package http;

import com.epik.util.rest.RestClient;

public class RestClientTest {
	
	public static void main(String[] args) throws Exception {
		RestClient.URI("http://127.0.0.1:8080/logstash/api/exception/deal").post().sendRequest();
	}
}
