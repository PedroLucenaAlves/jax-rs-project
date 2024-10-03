package br.com.alura.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.Test;

import junit.framework.Assert;

public class ClienteTeste {
	
	@Test
	public void testaQueAConexaoComOServidorFunciona() {
		
		Client client = ClientBuilder.newClient(); //criando cliente que fara as requisicoes http para o servidor
		
		WebTarget target = client.target("https://run.mocky.io/"); //target = alvo que sera trabalhado (onde as requisicoes seriam enviadas)
		
		String conteudo = target.path("/v3/d3a0b4d2-bc0c-47bf-a185-437d67f4ec3a").request().get(String.class); //requisicao get (endpoint que sera acessado)
		
		//System.out.println(conteudo);
		
		Assert.assertTrue(conteudo.contains("Rua Vergueiro")); //verificando se o xml existe
		Assert.assertTrue(conteudo.contains("3185")); //verificando se o xml existe
		
	}
	
	@Test
    public void testaQueAConexaoComOServidorFuncionaNoPathDeProjetos() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080");
        String conteudo = target.path("/projetos").request().get(String.class);
        
        System.out.println(conteudo);
        
        Assert.assertTrue(conteudo.contains("<nome>Minha loja"));


    }

}
