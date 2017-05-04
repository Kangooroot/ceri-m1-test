package api;

import fr.univavignon.pokedex.api.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.mockito.runners.*;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IPokemonFactoryTest {
	
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
	@Mock
    protected IPokemonFactory pokemonFactory;
    
    @Before
    public void setUp() throws PokedexException {
    	
		when( pokemonFactory.createPokemon(0, 613, 64, 4000, 4)).thenReturn(new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56));
    }

    @Test
	public void testCreatePokemon() {
    	
		int attack	= 126;
		int defense = 126;
		int stamina = 90;
		double iv	= 56;
    	int index 	= 0;
		int cp 		= 613;
		int hp 		= 64;
		int dust 	= 4000;
		int candy 	= 4;
		
		Pokemon pokemon = pokemonFactory.createPokemon(0, 613, 64, 4000, 4);
		
		Assert.assertEquals("Bulbizarre", 	pokemon.getName());
		Assert.assertEquals(attack, 		pokemon.getAttack());
		Assert.assertEquals(defense, 		pokemon.getDefense());
		Assert.assertEquals(stamina, 		pokemon.getStamina());
		Assert.assertEquals(iv, 			pokemon.getIv(), 0);
		Assert.assertEquals(index, 			pokemon.getIndex());
		Assert.assertEquals(cp, 			pokemon.getCp());
		Assert.assertEquals(hp, 			pokemon.getHp());
		Assert.assertEquals(dust, 			pokemon.getDust());
		Assert.assertEquals(candy, 			pokemon.getCandy());
	}
}