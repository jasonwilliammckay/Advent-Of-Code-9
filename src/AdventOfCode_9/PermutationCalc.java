package AdventOfCode_9;

import java.util.ArrayList;

/*  
    PermutationCalc finds all the possible orders for a set of numbers.

    For example, given a check for '3', this is asking in how many ways a set
    of [1, 2, 3] can be configured, which is 3! = 6 ways. 

    That is: [123][132][213][231][312][321].
    
    So an arraylist with all these combinations would be returned.

    Performs the following steps to provide all permutations:
        1. Check if the array is fully reversed or not by starting at the right 
            of the array, walking left, comparing i to i+1
        2. If not, swap i with the right-most value that is larger than it
        3. Reverse the items from i+1 to the end of the array
            (this intentionally makes our 'sort' less efficient so that we hit
            every combination in the process)
        4. Repeat until the step 1 passes
*/

public class PermutationCalc 
{
    // builds an arrayList composed of arrays, each reflecting one
    // permutation of the possible combinations, by continually
    // checking if the current array is sorted or not yet
    public static ArrayList<int[]> findAllPermutations(int permutations)
    {
        if (permutations < 0 || permutations > 10) // recover from invalid input gracefully
            permutations = 0;
        
        ArrayList<int[]> allRoutes = new ArrayList<>();
        int[] arr = setUpArray(permutations);
        allRoutes.add(arr.clone());
        
        int endOfArr = arr.length-1;
        int checkLeft= endOfArr;
        
        while (checkLeft > 0) // step 1
        {
            checkLeft--;
            if (arr[checkLeft] < arr[checkLeft+1])
            {
                addNewPerm(arr, allRoutes, checkLeft); // steps 2, 3
                checkLeft = endOfArr;  // reset the check
            }
        }
        return allRoutes;
    }

    private static int[] setUpArray(int permutations)
    {
        int[] arr = new int[permutations];
        
        for (int i = 0; i < permutations; i++)
            arr[i] = i;  
        
        return arr;
    }
    
    // steps 2 and 3 from the class description
    private static void addNewPerm(int[] arr, ArrayList<int[]> allRoutes, int checkLeft)
    {
        boolean swap;
        int endOfArr = arr.length-1;
        int checkRight = endOfArr;
        
        swap = false;
        while (swap == false)
        {
            if (arr[checkLeft] < arr[checkRight])
            {
                swap(arr, checkLeft, checkRight); // step 2
                reverseRange(arr, checkLeft+1, endOfArr); // step 3
                allRoutes.add(arr.clone());
                swap = true;
            }
            else
                checkRight--;        
        }
    }

    // reverses the items for a given range of an array
    // the start and end values are inclusive
    private static void reverseRange(int[]arr, int start, int end)
    {
        while (start < end)
        {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
    
    // swaps two entries of an array given by their index number
    private static void swap(int[]arr, int a, int b)
    {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;        
    }    
}