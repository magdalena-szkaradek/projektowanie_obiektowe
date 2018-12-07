import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class PathAlgorithm implements PathInterface {
    @Override
    public Graph buildGraph(String fileName) {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath());
        Graph graph = null;

        Scanner sc;
        try {
            sc = new Scanner(file);
            int numberOfNodes = getNumberOfNodes(sc);
            graph = buildListOfNeighborhoods(sc, numberOfNodes);
            buildInteriorOfNodes(graph, sc, numberOfNodes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }

    @Override
    public String decompress(Graph graph, int node) {
        String originalValue = graph.interiorOfNodes.get(node - 1);
        return decodeText(originalValue);
    }

    private String decodeText(String value) {
        int openingBracketIndex = value.indexOf('(');
        if (openingBracketIndex != -1) {
            int xIndex = value.indexOf('x');
            StringBuilder decoded = new StringBuilder(value.substring(0, openingBracketIndex));
            int numberOfCharsToRepeat = Integer.parseInt(value.substring(openingBracketIndex + 1, xIndex));
            int closingBracketIndex = value.indexOf(')');
            int numberOfTimesToRepeat = Integer.parseInt(value.substring(xIndex + 1, closingBracketIndex));
            String stringToRepeat = decoded.substring(decoded.length() - numberOfCharsToRepeat);
            for (int i = 0; i < numberOfTimesToRepeat - 1; i++) {
                decoded.append(stringToRepeat);
            }
            decoded.append(value.substring(closingBracketIndex + 1));
            return decodeText(decoded.toString());
        }
        return value;
    }

    @Override
    public String findPath(Graph graph, int beginningNode, int destinationNode) {
        return null;
    }

    @Override
    public String getPathString(Graph graph, int beginningNode, int destinationNode) {
        return null;
    }

    public static void main(String[] args) {
        String filename = "inputData.txt";
        PathAlgorithm pathAlgorithm = new PathAlgorithm();
        Graph graph = pathAlgorithm.buildGraph(filename);
        System.out.println(pathAlgorithm.decompress(graph, 3));
    }

    private void buildInteriorOfNodes(Graph graph, Scanner sc, int numberOfNodes) {
        for (int i = numberOfNodes + 2; i < numberOfNodes * 2 + 2; i++) {
            graph.interiorOfNodes.add(sc.nextLine());
        }
    }

    private int getNumberOfNodes(Scanner sc) {
        String firstLine = sc.nextLine();
        return Integer.parseInt(firstLine, 2);
    }

    private Graph buildListOfNeighborhoods(Scanner sc, int numberOfNodes) {
        Graph graph = new Graph(numberOfNodes);
        for (int i = 0; i < numberOfNodes; i++) {
            ArrayList<Integer> nodes = new ArrayList<>();
            String line = sc.nextLine();
            String[] graphValue = line.split(" ");
            Integer nodeNumber = Integer.parseInt(graphValue[0], 2);
            Arrays.stream(graphValue)
                    .skip(2)
                    .map(element -> Integer.parseInt(element, 2))
                    .forEach(nodes::add);
            graph.nodes.put(nodeNumber, nodes);
        }
        return graph;
    }
}
