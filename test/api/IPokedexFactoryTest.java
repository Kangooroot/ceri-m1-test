package api;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import fr.univavignon.pokedex.api.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IPokedexFactoryTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokedexFactory pokedexFactory;

    @Mock
    protected static IPokemonMetadataProvider pokemonMetadataProvider;

    @Mock
    protected static IPokemonFactory pokemonFactory;


    @Test
    public void testCreatePokedex() {
    	
        IPokedex newPokedex = pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory);
        assertNotNull(newPokedex);
    }

    @Before
    public void setUp() {
    	
        MockitoAnnotations.initMocks(this);
        IPokedex mockPokedex = mock(IPokedex.class);
        
        when(pokedexFactory.createPokedex(pokemonMetadataProvider, pokemonFactory)).thenReturn(mockPokedex);
    }
}