package AdventOfCode_9;

import java.util.HashMap;

/*
City tracks all the details of a city for this question.
*/

public class City 
{
    private final String name;
    private final int cID;
    private final HashMap<String, Integer> routes;

    public City(String name, int cID)
    {
        this.cID = cID;
        this.name = name;
        routes = new HashMap<>();
    }

    protected String getName()
    {return name;}
    
    protected int getID()
    {return cID;}    
    
    // define the distance from this city to another
    protected void addDestination(String name, Integer distance)
    {
        routes.put(name, distance);
    }
    
    // check the distancre from this city to another
    protected Integer getDistance(String target)
    {
        Integer distance = 0;
        
        if (routes.get(target) != null)
            distance = routes.get(target);
        
        return distance;
    }    
}