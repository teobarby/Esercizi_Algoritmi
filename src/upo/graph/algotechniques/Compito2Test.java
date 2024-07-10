package upo.graph.algotechniques;

import org.junit.Test;
import upo.graph.base.Vertex;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static upo.graph.algotechniques.DynamicProgramming.*;
import static upo.graph.algotechniques.Greedy.getMaxDisjointIntervals;


public class Compito2Test {

    @Test
    public void getMaxDisjointIntervalsTest() {
        Integer[] starting1 = {2, 5, 6};
        Integer[] ending1 = {5, 7, 8};
        Integer[] expected1 = {0, 2};
        Integer[] result1 = Greedy.getMaxDisjointIntervals(starting1, ending1);
        assertArrayEquals(expected1, result1, "Test case 1 failed");
    }



    @Test
    public void testLongestCommonSubsequence() {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        String expected1 = "GTAB";
        String result1 = LongestCommonSubsequence(s1, s2);
        assertEquals(expected1, result1);

        String s5 = "XMJYAUZ";
        String s6 = "MZJAWXU";
        String expected3 = "MJAU";
        String result3 = LongestCommonSubsequence(s5, s6);
        assertEquals(expected3, result3);

        String s7 = "ABC";
        String s8 = "AC";
        String expected4 = "AC";
        String result4 = LongestCommonSubsequence(s7, s8);
        assertEquals(expected4, result4);
    }


    @Test
    public void testApproxTSP() {

        Vertex v1 = Vertex.getVertexByLabel("1");
        Vertex v2 = Vertex.getVertexByLabel("2");
        Vertex v3 = Vertex.getVertexByLabel("3");
        Vertex v4 = Vertex.getVertexByLabel("4");

    }
}