import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PathAlgorithm implements PathInterface {
    public static void main(String[] args) {
        String filename = "inputData.txt";
        PathAlgorithm pathAlgorithm = new PathAlgorithm();
        Graph graph = pathAlgorithm.buildGraph(filename);
        System.out.println(pathAlgorithm.getPathString(graph, 1,8));
    }

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

    @Override
    public String findPath(Graph graph, int beginningNode, int destinationNode) {

        ArrayList<Integer> nodesList = graph.nodes.get(beginningNode);
        if(beginningNode == destinationNode){
            return Collections.singletonList(beginningNode).toString();
        }
        if(nodesList.contains(destinationNode)){
            List<Integer> path = new ArrayList<>();
            path.add(beginningNode);
            path.add(destinationNode);
            return path.toString();
        }
        List<Integer> notVisitedNodes = new ArrayList<>();
        Map<Integer, Integer> distances = new HashMap<>();
        Map<Integer, Integer> pathToPrev = new HashMap<>();

        for (Integer node : graph.nodes.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
            pathToPrev.put(node, null);
            notVisitedNodes.add(node);
        }
        distances.put(beginningNode, 0);
        while(!notVisitedNodes.isEmpty()){
            Integer nodeWithMinimalDistance = distances.entrySet().stream()
                    .filter(x -> notVisitedNodes.contains(x.getKey()))
                    .min(Comparator.comparing(Map.Entry::getValue)).get().getKey();
            notVisitedNodes.remove(nodeWithMinimalDistance);

            for (Integer neighbour : graph.nodes.get(nodeWithMinimalDistance)) {
                Integer temp = distances.get(nodeWithMinimalDistance) + decompress(graph, neighbour).length() + decompress(graph, nodeWithMinimalDistance).length();
                if(temp < distances.get(neighbour)){
                    distances.put(neighbour, temp);
                    pathToPrev.put(neighbour, nodeWithMinimalDistance);
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        path.add(destinationNode);
        while(pathToPrev.get(destinationNode) != null){
            destinationNode = pathToPrev.get(destinationNode);
            path.add(destinationNode);
        }
        Collections.reverse(path);
        return path.toString();
    }

    @Override
    public String getPathString(Graph graph, int beginningNode, int destinationNode) {
        String pathWithBrackets = findPath(graph, beginningNode, destinationNode);
        String path = pathWithBrackets.substring(1, pathWithBrackets.length() -1);
        String[] nodes = path.split(", ");
        StringBuilder finalPath = new StringBuilder();
        finalPath.append(decompress(graph, Integer.parseInt(nodes[0])));
        Arrays.stream(nodes).skip(1).forEach(element -> finalPath.append(", ").append(decompress(graph, Integer.parseInt(element))));
        return finalPath.toString();
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
}
