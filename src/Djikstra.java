import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Djikstra {
    public static Map<Book, Integer> djikstraSimples(HashMap<Book, Set<Book>> grafo, Book origem) {
        Map<Book, Integer> distancias = new HashMap<>();
        Queue<Book> fila = new LinkedList<>();
        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            Book atual = fila.poll();
            int distanciaAtual = distancias.get(atual);

            for (Book vizinho : grafo.getOrDefault(atual, new HashSet<>())) {
                if (!distancias.containsKey(vizinho)) {
                    distancias.put(vizinho, distanciaAtual + 1);
                    fila.add(vizinho);
                }
            }
        }
        return distancias;
    }
}
