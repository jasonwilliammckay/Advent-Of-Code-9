package AdventOfCode_9;

import java.util.ArrayList;

public class Program 
{
    public static void main(String[] args)
    {
        populateCityData(Constants.INPUT_FILE_NAME);
        tripPlanner();
    }

    // reads file input builds a list of cities with crosslinked
    // distance information to one another
    
    private static void populateCityData(String filename)
    {
        ArrayList<String> dataLine = FileParser.getStrings(filename);
        String[] data;
        
        for (int i = 0; i < dataLine.size(); i++)
        {
             data = dataLine.get(i).split(" ");
             
             try
             { CityManager.processCities(data[0], data[2], Integer.parseInt(data[4])); }
             catch(Exception e)
             { System.out.println(e); }
        }
    }
    
    // Ask PermutationCalc to find all possible routes that n cities
    // could be visited, then for each route, query each city for
    // the distance to the next. Keep a running total of the shortest and 
    // longest routes and report those.
    
    private static void tripPlanner()
    {
        ArrayList<int[]> allRoutes = PermutationCalc.findAllPermutations(CityManager.getNumCities());
        
        int longest = 0;
        int shortest = Integer.MAX_VALUE;
        int tripSize = 0;
        
        for (int i = 0; i < allRoutes.size(); i++)
        {
            tripSize = getCurrentTripDistance(allRoutes, i);

            if (tripSize < shortest)
                shortest = tripSize;

            if (tripSize > longest)
                longest = tripSize;
        }
        
        System.out.println("The shortest trip is: " + shortest);
        System.out.println("The longest trip is: " + longest);
    }
    
    private static int getCurrentTripDistance(ArrayList<int[]> allRoutes, int routeNum)
    {
        int[] currentRoute = allRoutes.get(routeNum);
        int tripSize = 0;

        for (int i = 0; i < currentRoute.length-1; i++)
            tripSize += CityManager.getDistance(currentRoute[i], currentRoute[i+1]);
        
        return tripSize;
    }
}  