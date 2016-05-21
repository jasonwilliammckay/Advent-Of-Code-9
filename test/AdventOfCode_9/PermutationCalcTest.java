package AdventOfCode_9;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class PermutationCalcTest {
    
    public PermutationCalcTest() {
    }
    
    @Test
    public void testFindAllPermutations_invalid() {
        System.out.println("findAllPermutations n = -1");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(-1);
        assertEquals (1, result.size());
        assertEquals("[]", Arrays.toString(result.get(0)));
    }

    @Test
    public void testFindAllPermutations_empty() {
        System.out.println("findAllPermutations n = 0");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(0);
        assertEquals (1, result.size());
        assertEquals("[]", Arrays.toString(result.get(0)));
    }

    @Test
    public void testFindAllPermutations_of_1() {
        System.out.println("findAllPermutations n = 1");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(1);
        assertEquals (1, result.size());
        assertEquals("[0]", Arrays.toString(result.get(0)));
    }

    @Test
    public void testFindAllPermutations_of_2() {
        System.out.println("findAllPermutations n = 2");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(2);
        assertEquals (2, result.size());
        assertEquals("[0, 1]", Arrays.toString(result.get(0)));
        assertEquals("[1, 0]", Arrays.toString(result.get(1)));
    }

    @Test
    public void testFindAllPermutations_of_3() {
        System.out.println("findAllPermutations n = 3");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(3);
        assertEquals (6, result.size());
        assertEquals("[0, 1, 2]", Arrays.toString(result.get(0)));
        assertEquals("[0, 2, 1]", Arrays.toString(result.get(1)));
        assertEquals("[1, 0, 2]", Arrays.toString(result.get(2)));
        assertEquals("[1, 2, 0]", Arrays.toString(result.get(3)));
        assertEquals("[2, 0, 1]", Arrays.toString(result.get(4)));
        assertEquals("[2, 1, 0]", Arrays.toString(result.get(5)));
    }

    @Test
    public void testFindAllPermutations_n_large() {
        System.out.println("findAllPermutations n = 10");
        ArrayList <int[]> result = PermutationCalc.findAllPermutations(10);
        assertEquals (3628800, result.size());
    }
}