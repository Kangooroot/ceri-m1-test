package fr.univavignon.pokedex.api.impl;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;

/**
 * 
 * @author uapv1400768
 *
 */
public class PokedexFactory implements IPokedexFactory {

	@Override
	public IPokedex createPokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		return new Pokedex(metadataProvider, pokemonFactory);
	}
}