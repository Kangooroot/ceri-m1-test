package api;

import fr.univavignon.pokedex.api.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.junit.*;
import org.mockito.runners.*;


import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IPokemonMetadataProviderTest {

    @Mock
    protected IPokemonMetadataProvider pokemonMetadataProvider;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() throws PokedexException {
    	when(pokemonMetadataProvider.getPokemonMetadata(0))
			.thenReturn(new PokemonMetadata(0, "Bulbizarre", 126, 126, 90));
    }
    
	@Test
	public void testGetPokemonMetadata() {
		try {
			PokemonMetadata metadata = pokemonMetadataProvider.getPokemonMetadata(0);
			
			Assert.assertEquals("Bulbizarre", metadata.getName());
			Assert.assertEquals(0, metadata.getIndex());
			Assert.assertEquals(126, metadata.getAttack());
			Assert.assertEquals(126, metadata.getDefense());
			Assert.assertEquals(90, metadata.getStamina());
		}
		catch (PokedexException e) {
			e.printStackTrace();
		}
	}
}
