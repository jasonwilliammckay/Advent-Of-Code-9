package AdventOfCode_9;

import java.util.HashMap;

// City tracks all the details of a city for this question
// A city knows its id, name, and a list of distances to other cities

public class City 
{
    private final String name;
    private final int cID;
    private final HashMap<String, Integer> routes;
    private static int newCityID = 0;

    public City(String name, int cID)
    {
        this.cID = cID;
        this.name = name;
        routes = new HashMap<>();
    }

    public static void resetCID()
    {newCityID = 0;}
    
    public static int getNumCities()
    {return newCityID;} 
    
    public String getName()
    {return name;}
    
    public int getID()
    {return cID;}  
    
    // for a given two cities, add them to the list of cities if 
    // not already present, then crosslink their distance references
    // to each other
    public static void processCities(HashMap <String, City> cityListByName, 
            HashMap <Integer, City> cityListByID, 
            String city1, String city2, Integer distance)
    {
        if (!cityListByName.containsKey(city1))
        {
            City.addCity(cityListByName, cityListByID, city1, newCityID);
            newCityID++;
        }

        if (!cityListByName.containsKey(city2))
        {
            City.addCity(cityListByName, cityListByID, city2, newCityID);
            newCityID++;
        }     
        
        City.setDistances(cityListByName, city1, city2, distance);
    }
    
    // get the distance between two cities, given their ids
    public static int getDistance(int a, int b, HashMap <Integer, City> cityListByID)
    {
        int distance;
        
        City cityA = cityListByID.get(a);
        String cityB = cityListByID.get(b).getName();
        distance = cityA.getDistance(cityB);
        
        return distance;
    }    

    // define the distance from this city to another
    private void addDestination(String name, Integer distance)
    {routes.put(name, distance);}
    
    // check the distancre from this city to another
    private Integer getDistance(String target)
    {
        Integer distance = 0;
        
        if (routes.get(target) != null)
            distance = routes.get(target);
        
        return distance;
    }
    
    // static helper method that tells each of two cities the distance to the other
    private static void setDistances(HashMap <String, City> cityListByName, String cityName1, String cityName2, Integer distance)
    {
        City city1 = cityListByName.get(cityName1);
        city1.addDestination(cityName2, distance);
        
        City city2 = cityListByName.get(cityName2);
        city2.addDestination(cityName1, distance);
    }
    
    // static helper method the calls the City constructor and updates the
    // hashmaps which allows easy searching of a City by name or id
    private static void addCity(HashMap <String, City> cityListByName, 
            HashMap <Integer, City> cityListByID, String cityName, int cityID)
    {
        if (!cityListByName.containsKey(cityName))
        {
            City newCity = new City(cityName, cityID);
            cityListByName.put(cityName, newCity);
            cityListByID.put(cityID, newCity);
        }
    }
}