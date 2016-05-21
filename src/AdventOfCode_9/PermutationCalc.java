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
    private static ArrayList<int[]> allRoutes = new ArrayList<>();
    
    // builds an arrayList composed of arrays, each reflecting one
    // permutation of the possible combinations, by continually
    // checking if the current array is sorted or not yet
    
    public static ArrayList<int[]> findAllPermutations(int permutations)
    {
        permutations = validateInput(permutations);
        
        int[] arr = firstPermutation(permutations);
        allRoutes.add(arr.clone()); // add the starting permutation regardless
        
        int checkLeft = arr.length-1;
        
        while (checkLeft > 0) // start step 1
            checkLeft = checkIfSorted(checkLeft, arr);

        return allRoutes;
    }
    
    public static void reset()
    {
        allRoutes.clear();
    }
    
    private static int validateInput(int permutations)
    {
        if (permutations < 0 || permutations > Constants.PERM_LIMIT)
            permutations = 0;
        
        return permutations;
    }

    // simply populates a array with incrementing integers as its default state
    private static int[] firstPermutation(int permutations)
    {
        int[] arr = new int[permutations];
        
        for (int i = 0; i < permutations; i++)
            arr[i] = i;  
        
        return arr;
    }
    
    // step 1
    // checks if steps 2 and 3 are needed
    private static int checkIfSorted(int leftIndex, int[] arr)
    {
        leftIndex--;
        
        if (arr[leftIndex] < arr[leftIndex+1])
        {
            addNewPerm(arr, leftIndex); // steps 2 + 3
            leftIndex = arr.length-1;   // reset the check
        }
        
        return leftIndex;
    }
    
    // prepares steps 2 and 3 from the class description
    private static void addNewPerm(int[] arr, int leftIndex)
    {
        swap(arr, leftIndex, findRightIndex(arr, leftIndex)); // do step 2
        reverseRange(arr, leftIndex+1, arr.length-1);         // do step 3
        allRoutes.add(arr.clone());
    }

    // find the right-index to work with before doing swaps
    private static int findRightIndex(int[] arr, int checkLeft)
    {
        int rightIndex = arr.length-1;
        
        while (arr[checkLeft] > arr[rightIndex])
            rightIndex--;
        
        return rightIndex;
    }    
    
    // step 2
    // swaps two entries of an array given by their index number
    private static void swap(int[]arr, int a, int b)
    {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;        
    }    
    
    // step 3
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
}