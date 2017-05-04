package fr.univavignon.pokedex.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author uapv1400768
 *
 */
public class PokemonMetadataProvider implements IPokemonMetadataProvider {

	/** Cette variable va contenir les données des pokemons récupérées depuis l'URL */
	private JsonArray json = null;
	
	/** urlStr est l'url depuis lequel nous allons essayer de récupérer les données */
	private final String urlStr = "https://raw.githubusercontent.com/PokemonGoF/PokemonGo-Bot/master/data/pokemon.json";
	
	@Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		
        if(this.json == null) {
        	this.json = getDataFromUrl();
        }

        JsonObject p;
        
        try {
            p = json.get(index).getAsJsonObject();
        }
        catch(IndexOutOfBoundsException e) {
            throw new PokedexException("Index introuvable");
        }
        
        String name = p.get("Name").getAsString();
        int attack = p.get("BaseAttack").getAsInt();
        int defense = p.get("BaseDefense").getAsInt();
        int stamina = p.get("BaseStamina").getAsInt();
        
        return new PokemonMetadata(index, name, attack, defense, stamina);
    }

	/**
	 * Permet de récupérer les métadonnées d'un pokemon depuis l'url
	 * @return JsonArray
	 */
    private JsonArray getDataFromUrl() {
    	
        URL url = null;
        HttpURLConnection request = null;

        try {url = new URL(this.urlStr);}
        catch (MalformedURLException e) {e.printStackTrace();}
        
        try {request = (HttpURLConnection) url.openConnection();}
        catch (IOException e) {e.printStackTrace();}
        
        try {request.connect();}
        catch (IOException e) {e.printStackTrace();}

        JsonElement root = null;
        JsonParser jp = new JsonParser();
        
        try {root = jp.parse(new InputStreamReader((InputStream) request.getContent()));}
        catch (IOException e) {e.printStackTrace();}
        
        return root.getAsJsonArray();
    }
}
