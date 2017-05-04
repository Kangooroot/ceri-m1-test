package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

/**
 * 
 * @author uapv1400768
 *
 */
public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {

		return new PokemonTrainer(name, team, pokedexFactory.createPokedex(new PokemonMetadataProvider(), new PokemonFactory()));
	}

}