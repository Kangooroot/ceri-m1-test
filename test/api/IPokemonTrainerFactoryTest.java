package api;

import fr.univavignon.pokedex.api.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.mockito.runners.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IPokemonTrainerFactoryTest {

	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock 
	private IPokedex pokedex;
	
	@Mock
	private IPokedexFactory pokedexFactory;

	@Mock
	private IPokemonTrainerFactory pokemonTrainerFactory;
	
	@Before 
	public void setUp() throws PokedexException{
		when(pokemonTrainerFactory.createTrainer("red", Team.MYSTIC, pokedexFactory)).thenReturn(new PokemonTrainer("red",Team.MYSTIC,pokedex));
	}
	
	@Test
	public void testCreateTrainer(){
		
		PokemonTrainer red = pokemonTrainerFactory.createTrainer("red", Team.MYSTIC, pokedexFactory);
		assertNotNull(red);
		assertEquals(red.getName(),"red");
		assertEquals(red.getTeam(),Team.MYSTIC);
	}
}