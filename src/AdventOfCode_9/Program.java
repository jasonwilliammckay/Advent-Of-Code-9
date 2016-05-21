package AdventOfCode_9;

import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    final static String INPUT_FILE_NAME = "input.txt";
    
    public static void main(String[] args)
    {
        routeManager(INPUT_FILE_NAME);
    }

    // reads file input to build City objects, and maintains two hashes
    // which can search the cities by id or name without manually asking
    // every object for its details
    private static void routeManager(String filename)
    {
        HashMap <String, City> cityListByName = new HashMap<>();
        HashMap <Integer, City> cityListByID = new HashMap<>();
        ArrayList<String> dataLine = FileParser.getStrings(filename);
        
        for (int i = 0; i < dataLine.size(); i++)
        {
             String[] data = dataLine.get(i).split(" ");

             try
             {
                City.processCities(cityListByName, cityListByID, data[0], data[2], 
                        Integer.parseInt(data[4]));
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
        }
        tripPlanner(cityListByID);
    }
    
    // Ask PermutationCalc to find all possible routes that n cities
    // could be visited, then for each route, query each city for
    // the distance to the next. Keep a running total of the shortest and 
    // longest routes and report those.
    
    private static void tripPlanner(HashMap <Integer, City> cityDetailsByID)
    {
        ArrayList<int[]> allRoutes = PermutationCalc.findAllPermutations(City.getNumCities());
        int shortest = Integer.MAX_VALUE;
        int longest = 0;

        for (int i = 0; i < allRoutes.size(); i++)
        {
            int tripSize = 0;
            int[] currentRoute = allRoutes.get(i);

            for (int j = 0; j < currentRoute.length-1; j++)
                tripSize += City.getDistance(currentRoute[j], currentRoute[j+1], cityDetailsByID);

            if (tripSize < shortest)
                shortest = tripSize;

            if (tripSize > longest)
                longest = tripSize;
        }
        System.out.println("The shortest trip is: " + shortest);
        System.out.println("The longest trip is: " + longest);
    }
}  