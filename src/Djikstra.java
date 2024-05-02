import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Comparator;

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
        return djikstraSorted(distancias);
    }

    /* Recebe o grafo de recomendações e o histórico de leitura do usuário
    * Retorna todos os níveis de recomendações de cada livro
    * Não retorna os próprios livros do histórico de leitura
    * Para várias recomendações do mesmo livro é retornada a menor distância*/
    public static Map<Book, Integer> djikstraRecommendations(Map<Book, Set<Book>> grafo, Stack<Book> livros) {
        Map<Book, Integer> resultadoFinal = new HashMap<>();
        Set<Book> livrosIniciais = new HashSet<>(livros); // Cria um conjunto a partir da Stack para referência rápida

        for (Book livro : livros) {
            Map<Book, Integer> resultadoTemporario = djikstraSimples((HashMap<Book, Set<Book>>) grafo, livro);

            for (Map.Entry<Book, Integer> entrada : resultadoTemporario.entrySet()) {
                Book livroAtual = entrada.getKey();
                int distanciaAtual = entrada.getValue();

                // Checa se o livro atual já está no resultado final e atualiza se necessário
                if (!livrosIniciais.contains(livroAtual)) {
                    if (resultadoFinal.containsKey(livroAtual)) {
                        if (resultadoFinal.get(livroAtual) > distanciaAtual) {
                            resultadoFinal.put(livroAtual, distanciaAtual);
                        }
                    } else {
                        resultadoFinal.put(livroAtual, distanciaAtual);
                    }
                }
            }
        }

        return djikstraSorted(resultadoFinal); //Retorna resultado ordenado
    }

    //Realiza a ordenação dos conjuntos chave valor Map<Book, Integer>
    //Ordena pela distância e em seguida pelo título do livro
    private static Map<Book, Integer> djikstraSorted(Map<Book, Integer> resultadoFinal) {
        // Criando um TreeMap para ordenar os resultados
        TreeMap<Book, Integer> sortedResults = new TreeMap<>(new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                // Ordena primeiro pela distância
                int distCompare = Integer.compare(resultadoFinal.get(o1), resultadoFinal.get(o2));
                if (distCompare != 0) {
                    return distCompare;
                }
                // Se as distâncias forem iguais, ordena pelo título para garantir consistência
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });

        // Adiciona todos os resultados no TreeMap ordenado
        sortedResults.putAll(resultadoFinal);

        return sortedResults;
    }
}
