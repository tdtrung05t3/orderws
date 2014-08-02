package org.service;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.junit.Before;
import org.junit.Test;

import com.sun.net.httpserver.HttpServer;

public class OrderServiceTest {
	static final URI BASE_URI = getBaseURI();
    HttpServer server;

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(8080).build();
    }
    
    public static void main(String[] args) {
		System.out.println(getBaseURI());
	}
	@Before
    public void startServer() throws IOException  {
    }

    @Test
    public void testGetProducts() {
    }

}
