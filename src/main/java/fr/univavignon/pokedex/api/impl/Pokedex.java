package fr.univavignon.pokedex.api.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class Pokedex implements IPokedex {

	private ArrayList<Pokemon> pokemons;
	
	private transient IPokemonMetadataProvider metadataProvider;
	
	private transient IPokemonFactory pokemonFactory;
	
	public Pokedex(IPokemonMetadataProvider metadataProvider, IPokemonFactory pokemonFactory) {
		
		pokemons = new ArrayList<Pokemon>();
		
		this.metadataProvider = metadataProvider;
		this.pokemonFactory = pokemonFactory;
	}
	
	@Override
    public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
        return this.metadataProvider.getPokemonMetadata(index);
	}

	@Override
    public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
        return this.pokemonFactory.createPokemon(index, cp, hp, dust, candy);
	}
	
	@Override
	public int size() {
		return this.pokemons.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		
		int id = pokemon.getIndex();
		
		if(!this.pokemons.contains(pokemon)) {
			this.pokemons.add(id, pokemon);
		}
		return id;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		
		Pokemon p = null;
		try {
            p = this.pokemons.get(id);
        }
		catch (IndexOutOfBoundsException e) {
            throw new PokedexException("Index introuvable");
        }
		return p;
	}

	@Override
	public List<Pokemon> getPokemons() {
		return this.pokemons;
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		
		List<Pokemon> res = new ArrayList<>(this.pokemons);
		res.sort(order);
		return res;
	}
}