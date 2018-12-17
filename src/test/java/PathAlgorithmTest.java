import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PathAlgorithmTest {
    private PathAlgorithm pathAlgorithm;
    private Graph correctGraph;

    @Before
    public void setUp(){
       this.pathAlgorithm = new PathAlgorithm();
       this.correctGraph = pathAlgorithm.buildGraph("inputData.txt");

    }

    @Test(expected = NullPointerException.class)
    public void buildGraph_testFileNotExist() {
        pathAlgorithm.buildGraph("data.txt");
    }

    @Test(expected = NoSuchElementException.class)
    public void buildGraph_testEmptyFile() {
        pathAlgorithm.buildGraph("emptyFile.txt");
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildGraph_testFileNotTxt() {
        pathAlgorithm.buildGraph("sampleFile.ts");
    }

    @Test
    public void buildGraph_testCheckNumberOfNodes() {
        int expectedNumberOfNodes = 8;
        assertEquals(expectedNumberOfNodes, correctGraph.getnumberOfNodes());
        assertEquals(expectedNumberOfNodes, correctGraph.nodes.keySet().size());
    }

    @Test
    public void buildGraph_testNeighbours(){
        List<Integer> neighbours = correctGraph.nodes.get(1);
        List<Integer> expectedNeighbours = new ArrayList<>();
        expectedNeighbours.add(2);
        expectedNeighbours.add(3);
        expectedNeighbours.add(4);

        assertTrue(neighbours.containsAll(expectedNeighbours));
    }

    @Test
    public void buildGraph_testGraphValues(){
        assertEquals("TEKS(1x2)T", correctGraph.interiorOfNodes.get(0));
        assertEquals("LI(2x3)", correctGraph.interiorOfNodes.get(1));
    }

    @Test
    public void decompress_testNoBrackets() {
        String value = pathAlgorithm.decompress(correctGraph, 4);
        String expected = "DODAJ";
        assertEquals(expected, value);
    }

    @Test
    public void decompress_testOnePairsOfBrackets() {
        String value = pathAlgorithm.decompress(correctGraph, 1);
        String expected = "TEKSST";
        assertEquals(expected, value);
    }

    @Test
    public void decompress_testTwoPairsOfBrackets() {
        String value = pathAlgorithm.decompress(correctGraph, 6);
        String expected = "ZAMAMMEK";
        assertEquals(expected, value);
    }

    @Test
    public void decompress_testThreePairsOfBrackets() {
        String value = pathAlgorithm.decompress(correctGraph, 8);
        String expected = "SZCZECZEEEELINA";
        assertEquals(expected, value);

    }

    @Test
    public void findPath_theSameNode() {
        String path = pathAlgorithm.findPath(correctGraph, 1, 1);
        String expected = "[1]";
        assertEquals(expected, path);
    }

    @Test
    public void findPath_neighbours() {
        String path = pathAlgorithm.findPath(correctGraph, 1, 2);
        String expected = "[1, 2]";
        assertEquals(expected, path);
    }

    @Test
    public void findPath_from1To8() {
        String path = pathAlgorithm.findPath(correctGraph, 1, 8);
        String expected = "[1, 4, 8]";
        assertEquals(expected, path);
    }


    @Test
    public void findPath_from8To1() {
        String path = pathAlgorithm.findPath(correctGraph, 8, 1);
        String expected = "[8, 4, 1]";
        assertEquals(expected, path);
    }

    @Test
    public void getPathString_from8To1() {
        String path = pathAlgorithm.getPathString(correctGraph, 8 , 1);
        String expected = "SZCZECZEEEELINA, DODAJ, TEKSST";
        assertEquals(expected, path);
    }

    @Test
    public void getPathString_from1To8() {
        String path = pathAlgorithm.getPathString(correctGraph, 1 , 8);
        String expected = "TEKSST, DODAJ, SZCZECZEEEELINA";
        assertEquals(expected, path);
    }

    @Test
    public void getPathString_theSameNode() {
        String path = pathAlgorithm.getPathString(correctGraph, 1 , 1);
        String expected = "TEKSST";
        assertEquals(expected, path);
    }

    @Test
    public void getPathString_neighbours() {
        String path = pathAlgorithm.getPathString(correctGraph, 1 , 2);
        String expected = "TEKSST, LILILI";
        assertEquals(expected, path);
    }



}