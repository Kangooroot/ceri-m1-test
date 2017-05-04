package api;

import fr.univavignon.pokedex.api.*;

import org.junit.*;
import org.mockito.*;
import org.mockito.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IPokedexTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    protected static IPokedex pokedex;

    protected static Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);

    protected static Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);

    private static final int[] pokedex_size = {0};

    @Before
    public void setUp() throws PokedexException {

        List<Pokemon> l1 = new ArrayList<>();
        List<Pokemon> l2 = new ArrayList<>();
        
        pokedex_size[0] = 0;
        MockitoAnnotations.initMocks(this);
        
        when(pokedex.size()).thenAnswer(i -> pokedex_size[0]);
        when(pokedex.addPokemon(any())).then(i -> pokedex_size[0]++);

        when(pokedex.getPokemon(0)).thenReturn(bulbizarre);
        when(pokedex.getPokemon(1)).thenThrow(new PokedexException("Invalid index"));

        l1.add(bulbizarre);
        l1.add(aquali);
        
        when(pokedex.getPokemons()).thenReturn(Collections.unmodifiableList(l1));
        
        l2.add(aquali);
        l2.add(bulbizarre);
        
        when(pokedex.getPokemons(any())).thenReturn(Collections.unmodifiableList(l2)).thenReturn(Collections.unmodifiableList(l1));
    }

    @Test
    public void testSize() {
    	
        assertEquals(0, pokedex.size());
    }

    @Test
    public void testAddPokemon() {
    	
        assertEquals(0, pokedex.addPokemon(bulbizarre));
        assertEquals(1, pokedex.size());
        assertEquals(1, pokedex.addPokemon(bulbizarre));
        assertEquals(2, pokedex.size());
    }

    @Test
    public void testGetPokemon() throws PokedexException {
    	
        pokedex.addPokemon(bulbizarre);

        assertEquals("Bulbizarre", pokedex.getPokemon(0).getName());

        try {
            pokedex.getPokemon(1);
            fail("Une PokedexException était attendue");
        }
        catch (PokedexException e) {
            assertEquals("Index introuvable", e.getMessage());
        }
    }

    @Test
    public void testGetPokemons() throws PokedexException {
    	
        pokedex.addPokemon(bulbizarre);
        pokedex.addPokemon(aquali);
        
        List<Pokemon> list = pokedex.getPokemons();

        assertEquals(pokedex.size(), list.size());
        assertEquals(pokedex.getPokemon(0).getName(), list.get(0).getName());

        try {
            list.add(bulbizarre);
            fail("Une PokedexException était attendue");
        }
        catch (UnsupportedOperationException e) {e.printStackTrace();}
    }
}
