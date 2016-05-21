package AdventOfCode_9;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class CityTest {
    
    HashMap <String, City> cityListByName = new HashMap<>();
    HashMap <Integer, City> cityListByID = new HashMap<>();
    String city1 = "Winnipeg";
    String city2 = "Toronto";
    Integer distance = 2235;

    public CityTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        cityListByName.clear();
        cityListByID.clear();
    }
    
    @After
    public void tearDown() {
        City.resetCID();
    }

    /**
     * Test of getNumCities method, of class City.
     */
    @Test
    public void testGetNumCities() {
        System.out.println("getNumCities");
        assertEquals(0, City.getNumCities());
        City.processCities(cityListByName, cityListByID, city1, city2, distance);
        assertEquals(2, City.getNumCities());
        City.processCities(cityListByName, cityListByID, city1, city2, distance);
        assertEquals(2, City.getNumCities());
        City.processCities(cityListByName, cityListByID, "SamePlace", "SamePlace", distance);
        assertEquals(2, City.getNumCities());
        City.processCities(cityListByName, cityListByID, "ToNowhere", "", distance);
        assertEquals(2, City.getNumCities());
    }

    /**
     * Test of getName method, of class City.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        City.processCities(cityListByName, cityListByID, city1, city2, distance);        
        City examine;
        
        // check by string reference, and city id reference
        examine = cityListByName.get(city1);
        assertEquals("Winnipeg", examine.getName());
        examine = cityListByID.get(0);
        assertEquals("Winnipeg", examine.getName());
        
        // repeat for second city
        examine = cityListByName.get(city2);
        assertEquals("Toronto", examine.getName());        
        examine = cityListByID.get(1);
        assertEquals("Toronto", examine.getName());
    }

    /**
     * Test of getID method, of class City.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        City.processCities(cityListByName, cityListByID, city1, city2, distance);        
        
        // ensure both newly added cities get their own unique ID
        City examine = cityListByID.get(0);
        assertEquals(0, examine.getID());
        examine = cityListByID.get(1);
        assertEquals(1, examine.getID());
    }

    /**
     * Test of processCities method, of class City.
     */
    @Test
    public void testProcessCities() {
        System.out.println("processCities");

        City.processCities(cityListByName, cityListByID, city1, city2, distance);    
        
        // check both hashes against their City objects
        City examine = cityListByName.get("Winnipeg");
        assertEquals("Winnipeg", examine.getName());
        assertEquals(0, examine.getID());

        // check both hashes against their City objects for the second input
        examine = cityListByName.get("Toronto");
        assertEquals("Toronto", examine.getName());
        assertEquals(1, examine.getID());

        // check distance crosslinking
        assertEquals(2235, City.getDistance(0, 1, cityListByID));
        assertEquals(2235, City.getDistance(1, 0, cityListByID));
    }

    /**
     * Test of getDistance method, of class City.
     */
    @Test
    public void testGetDistance_3args() {
        System.out.println("getDistance");

        City.processCities(cityListByName, cityListByID, city1, city2, distance);
        
        // from somewhere to itself should be a distance of zero
        assertEquals(0, City.getDistance(0, 0, cityListByID));
        
        // from a valid city to another valid city
        assertEquals(2235, City.getDistance(0, 1, cityListByID));
        
        // from a formerly valid city to a new valid city
        City.processCities(cityListByName, cityListByID, "Winnipeg", "Brandon", 500);
        assertEquals(500, City.getDistance(0, 2, cityListByID));
        
        // check the newer city first to confirm crosslinking
        City.processCities(cityListByName, cityListByID, "Brandon", "Toronto", 2400);
        assertEquals(2400, City.getDistance(2, 1, cityListByID));
        
        // ensure nonsensical values are changed to the 'too large' default of 20k
        City.processCities(cityListByName, cityListByID, "TooFar", "WayTooFar", 999999);
        assertEquals(20000, City.getDistance(3, 4, cityListByID));
    }
}
