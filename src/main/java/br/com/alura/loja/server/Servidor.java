package br.com.alura.loja.server;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {
	
	public static void main(String[] args) throws IOException {
		
		//rodando servidor com suporte jax-rs 
		
		HttpServer server = inicializaServidor();
		
		System.out.println("Servidor rodando");
		
		System.in.read(); //aguarda acionarmos o enter para stopar o servidor
		
		server.stop();
		
		}
	
	public static HttpServer inicializaServidor() {
		ResourceConfig config = new ResourceConfig().packages("br.com.alura.loja"); //configuracao dos meus recursos
		URI uri = URI.create("http://localhost:8080/");
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}

}
