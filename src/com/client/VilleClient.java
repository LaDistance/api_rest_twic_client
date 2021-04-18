package com.client;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import com.beans.Ville;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

public class VilleClient {

	public VilleClient(){
		
	}
	public ArrayList<Ville> getAll() throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ClientResponse responseClient = client.resource(uri).path("villes").get(ClientResponse.class);
		String CorpsRepHttp = responseClient.getEntity(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Ville> listeVilles = mapper.readValue(CorpsRepHttp, new TypeReference<ArrayList<Ville>>(){
		});
		
		System.out.println("Appel GET ALL : taille de la liste : "+ listeVilles.size());
		return listeVilles;
	}
	

	// On récupère un ArrayList de villes pour standardiser le code. 
	// En réalité on en récupérera toujours 1 seule vu que c'est la clé primaire.
	public ArrayList<Ville> getVille(int codeInsee) throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ClientResponse responseClient = client.resource(uri).path("ville").path("insee").path(String.valueOf(codeInsee)).get(ClientResponse.class);
		String CorpsRepHttp = responseClient.getEntity(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Ville> listeVilles = mapper.readValue(CorpsRepHttp, new TypeReference<ArrayList<Ville>>(){
		});
		
		System.out.println("Appel GET [codeInsee] : taille de la liste : "+ listeVilles.size());
		if(listeVilles.size() == 1) {
			System.out.println("Ville demandée : " + listeVilles.get(0));
		}
		return listeVilles;
	}
	

	public ArrayList<Ville> getVilleByNom(String nom) throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ClientResponse responseClient = client.resource(uri).path("ville").path("nom").path(nom).get(ClientResponse.class);
		String CorpsRepHttp = responseClient.getEntity(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Ville> listeVilles = mapper.readValue(CorpsRepHttp, new TypeReference<ArrayList<Ville>>(){
		});
		
		System.out.println("Appel GET [nom] : taille de la liste : "+ listeVilles.size());
		if(listeVilles.size() == 1) {
			System.out.println("Ville demandée : " + listeVilles.get(0));
		}
		return listeVilles;
	}

	
	public ArrayList<Ville> getVilleByCodePostal(int codePostal) throws JsonParseException, JsonMappingException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ClientResponse responseClient = client.resource(uri).path("ville").path("postal").path(String.valueOf(codePostal)).get(ClientResponse.class);
		String CorpsRepHttp = responseClient.getEntity(String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		ArrayList<Ville> listeVilles = mapper.readValue(CorpsRepHttp, new TypeReference<ArrayList<Ville>>(){
		});
		
		System.out.println("Appel GET [code postal] : taille de la liste : "+ listeVilles.size());
		if(listeVilles.size() == 1) {
			System.out.println("Ville demandée : " + listeVilles.get(0));
		}
		return listeVilles;
	}

	public void createVille(Ville ville) throws JsonGenerationException, JsonMappingException, UniformInterfaceException, ClientHandlerException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ObjectMapper mapper = new ObjectMapper();
		client.resource(uri).path("ville").type(MediaType.APPLICATION_JSON)
				.post(ClientResponse.class, mapper.writeValueAsString(ville));
		
		System.out.println("Appel CREATE : succès dans la création de la ville : " + ville);
	}

	public void putVille(Ville ville, int codeInsee) throws JsonGenerationException, JsonMappingException, UniformInterfaceException, ClientHandlerException, IOException {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		ObjectMapper mapper = new ObjectMapper();
		client.resource(uri).path("ville").path(String.valueOf(codeInsee)).type(MediaType.APPLICATION_JSON)
				.put(ClientResponse.class, mapper.writeValueAsString(ville));
		
		System.out.println("Appel PUT : succès dans la modification de la ville d'id : " + codeInsee + ", nouvelle ville : "+ ville);
	}

	public void deleteVille(int codeInsee) {
		Client client = Client.create();
		URI uri = UriBuilder.fromUri("http://localhost:8181/").build();
		client.resource(uri).path("ville").path(String.valueOf(codeInsee)).type(MediaType.APPLICATION_JSON)
				.delete(ClientResponse.class);
		
		System.out.println("Appel DELETE : succès dans la suppression de la ville d'id : " + codeInsee);
	}

}
