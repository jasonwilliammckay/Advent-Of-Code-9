package AdventOfCode_9;

import java.util.HashMap;

/*
CityManager handles a list of cities. It includes two hashes for finding
City objects by name or id. It contains functions for adding cities
and interaction between the cities.
*/

public class CityManager 
{
    private static int newCityID = 0;
    private static HashMap <String, City> cityListByName = new HashMap<>();
    private static HashMap <Integer, City> cityListByID = new HashMap<>();    
    
    public static int getNumCities()
    {return newCityID;}
    
    public static HashMap <String,City> getCitiesByName()
    { return cityListByName; }

    public static HashMap <Integer, City> getCitiesByID()
    { return cityListByID; }
    
    public static void reset()
    {
        cityListByName.clear();
        cityListByID.clear();
        newCityID = 0;
    }
    
    // add two cities to the list and crosslink their distance information
    public static void processCities(String city1, String city2, Integer distance)
    {
        if (validNewCityInput(city1, city2, distance))
        {
            addIfUnlisted(city1);
            addIfUnlisted(city2);
            setDistances(city1, city2, distance);
        }
    }
    
    // get the distance between two cities, given their ids
    public static int getDistance(int a, int b)
    {
        int distance = 0;
        
        if ((a != b) && (cityListByID.size() > a) && (cityListByID.size() > b))
        {
            City cityA = cityListByID.get(a);
            String cityB = cityListByID.get(b).getName();
            distance = cityA.getDistance(cityB);
        }
        
        return distance;
    }    

    // check if a city exists on the list or not yet
    private static void addIfUnlisted(String city)
    {
        if (!cityListByName.containsKey(city))
            addCity(city);
    }

    // inputs checks for creating new distance figures between two cities
    private static boolean validNewCityInput(String city1, String city2, Integer distance)
    {
        boolean valid = true;
        
        if (distance < 0 || distance > Constants.DISTANCE_LIMIT)
            valid = false;
        
        if (city1.equalsIgnoreCase(city2))
            valid = false;
        
        if (city1.isEmpty() || city2.isEmpty())
            valid = false;
        
        return valid;
    }

    // helper method that tells each of two cities the distance to the other
    private static void setDistances(String cityName1, String cityName2, Integer distance)
    {
        City city1 = cityListByName.get(cityName1);
        city1.addDestination(cityName2, distance);

        City city2 = cityListByName.get(cityName2);
        city2.addDestination(cityName1, distance);
    }
    
    // helper method the calls the City constructor and updates the
    // hashmaps which allows easy searching of a City by name or id
    private static void addCity(String cityName)
    {
        City newCity = new City(cityName, newCityID);
        cityListByName.put(cityName, newCity);
        cityListByID.put(newCityID, newCity);
        newCityID++;
    }
}