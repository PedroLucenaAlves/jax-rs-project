package br.com.alura.loja;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Projeto;
import br.com.alura.loja.server.Servidor;
import junit.framework.Assert;

public class ClienteTeste {
	
	private HttpServer server;
	
	//levanta o servidor antes de rodas os testes para evitar subir servidor manualmente
	@Before
	public void startaServidorAntesDosTestes() {
		
		server = Servidor.inicializaServidor();
		
	}
	
	@After
	public void mataServidorAposTestes() {
		server.stop();
	}
	
//	@Test
//	public void testaQueAConexaoComOServidorFunciona() {
//		
//		Client client = ClientBuilder.newClient(); //criando cliente que fara as requisicoes http para o servidor
//		
//		WebTarget target = client.target("https://run.mocky.io/"); //target = alvo que sera trabalhado (onde as requisicoes seriam enviadas)
//		
//		String conteudo = target.path("/v3/d3a0b4d2-bc0c-47bf-a185-437d67f4ec3a").request().get(String.class); //requisicao get (endpoint que sera acessado)
//		
//		//System.out.println(conteudo);
//		
//		Assert.assertTrue(conteudo.contains("Rua Vergueiro")); //verificando se o xml existe
//		Assert.assertTrue(conteudo.contains("3185")); //verificando se o xml existe
//		
//	}
	
	@Test
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		
		Client client = ClientBuilder.newClient(); //criando cliente que fara as requisicoes http para o servidor
		
		WebTarget target = client.target("http://localhost:8080"); //target = alvo que sera trabalhado (onde as requisicoes seriam enviadas)
		
		String conteudo = target.path("/carrinhos").request().get(String.class); //requisicao get (endpoint que sera acessado)
		
		
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo); //verificando se o xml existe
		
		Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
		
		server.stop();
		
		
	}
	
	@Test
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos").request().get(String.class);
        
        Projeto projeto = (Projeto) new XStream().fromXML(conteudo); //verificando se o xml existe
        
        Assert.assertEquals("Minha loja", projeto.getNome());


    }

}
