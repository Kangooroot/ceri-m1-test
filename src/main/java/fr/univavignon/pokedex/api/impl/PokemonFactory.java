package fr.univavignon.pokedex.api.impl;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.Pokemon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 
 * @author uapv1400768
 *
 */
public class PokemonFactory implements IPokemonFactory {
	
    private final String ivCalculatorUrl = "https://pokemon.gameinfo.io/en/tools/iv-calculator";
    
    @Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
    	
        JsonObject jsonObject = null;
        
        try {jsonObject = getStatsFromUrl(index + 1, cp, dust);}
        catch (Exception e) {e.printStackTrace();}

        System.out.println(jsonObject.toString());
        JsonArray stats = jsonObject.get("stats").getAsJsonArray();

        // On récupère les stats du pokemon à créer, depuis le json
        String name = jsonObject.get("pokemon").getAsJsonArray().get(0).toString().replace("\"", "");
        int attack = stats.get(1).getAsInt();
        int defense = stats.get(2).getAsInt();
        int stamina = stats.get(0).getAsInt();
        double iv = jsonObject.get("levels").getAsJsonObject().get("50").getAsDouble() * 100.0;
        
        // On crée le nouveau pokemon
        return new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
    }

    /**
     * Récupère les stats du pokemon depuis l'url
     * @param index
     * @param cp
     * @param dust
     * @return JsonObject
     * @throws Exception
     */
    private JsonObject getStatsFromUrl(int index, int cp, int dust) throws Exception {
    	
        URL url = new URL(ivCalculatorUrl);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setDoOutput(true);
        
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes("p="+index+"&dust%5B%5D="+dust+"&ct=true"+"&ev=false"+"&cp%5B%5D="+cp+"&v=3");
        wr.flush();
        wr.close();

        JsonParser jp = new JsonParser();
        JsonElement root = null;

        // Récupération des données au format json
        try {root = jp.parse(new InputStreamReader(con.getInputStream()));}
        catch (IOException e) {e.printStackTrace();}

        return root.getAsJsonObject();

    }
}
