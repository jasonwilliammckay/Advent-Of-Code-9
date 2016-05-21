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
    }

    /**
     * Test of getName method, of class City.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        City.processCities(cityListByName, cityListByID, city1, city2, distance);        
        City examine;
        examine = cityListByName.get(city1);
        assertEquals("Winnipeg", examine.getName());
        examine = cityListByID.get(0);
        assertEquals("Winnipeg", examine.getName());
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
        
        City examine = cityListByName.get("Winnipeg");
        assertEquals("Winnipeg", examine.getName());
        assertEquals(0, examine.getID());

        examine = cityListByName.get("Toronto");
        assertEquals("Toronto", examine.getName());
        assertEquals(1, examine.getID());

        assertEquals(2235, City.getDistance(0, 1, cityListByID));
    }

    /**
     * Test of getDistance method, of class City.
     */
    @Test
    public void testGetDistance_3args() {
        System.out.println("getDistance");

        City.processCities(cityListByName, cityListByID, city1, city2, distance);
        City.processCities(cityListByName, cityListByID, "Winnipeg", "Brandon", 500);
        City.processCities(cityListByName, cityListByID, "Brandon", "Toronto", 2400);
        
        assertEquals(2235, City.getDistance(0, 1, cityListByID));
        assertEquals(500, City.getDistance(0, 2, cityListByID));
        assertEquals(2400, City.getDistance(2, 1, cityListByID));
    }
}
