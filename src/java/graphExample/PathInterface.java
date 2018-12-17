public interface PathInterface {

    /*
    metoda, która jest odpowiedzialna za odczytanie pliku graph.txt, zamiane
    informacji zapisanej w postaci binarnej na dziesietna oraz zbudowanie
    grafu na podstawie odczytanego i rozkodowanego kodu binarnego
    */
    Graph buildGraph(String fileName);

    /*
    metoda dekodujaca i zwracajaca ciag znakow znajdujacy sie w zadanym wezle;
    */
    String decompress(Graph graph, int node);

    /*
    metoda znajdująca najkrotsza ścieżkę (numry wezlow) pomiędzy dwoma wezlami
    zadanymi na wejsciu
    */
    String findPath(Graph graph, int beginningNode, int destinationNode);

    /*
    metoda zwracajaca rozkodowany ciag znakow, które sa przechowywane przez
    poszczegolne wezly w najkrotszej sciezce pomiedzy dwoma wezlami oznaczonymi
    jako beginningNode oraz destinationNode
    */
    String getPathString(Graph graph, int beginningNode, int destinationNode);

}
