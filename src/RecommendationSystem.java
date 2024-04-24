import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/*
 * A RecommendationSystem mantém um grafo de livros e suas recomendações e fornece
 * funcionalidades para adicionar recomendações e buscar recomendações de livros para usuários
 * baseadas em seu histórico de leitura.
 */
public class RecommendationSystem {
    private final Map<Book, Set<Book>> graph;

    public RecommendationSystem() {
        this.graph = new HashMap<>();
    }

    // Método para adicionar um livro e suas recomendações ao grafo
    public void addBookAndRecommendations(Book book, Set<Book> recommendations) {
        // Garantindo que cada livro tenha pelo menos duas recomendações
        if (recommendations.size() < 2) {
            throw new IllegalArgumentException("Cada livro precisa ter pelo menos duas recomendações.");
        }
        graph.put(book, recommendations);
    }

    // Método para obter recomendações para um usuário baseado em seu histórico de leitura
    public Set<Book> getRecommendations(User user) {
        Set<Book> recommendations = new HashSet<>();
        Stack<Book> history = user.getReadingHistory(); // Obtém o histórico de leitura do usuário
        Set<Book> readBooks = new HashSet<>(history); // Transforma a pilha de livros lidos em um conjunto para pesquisa rápida.

        for (Book book : history) { // Percorre o histórico de livros lidos
            Set<Book> recs = graph.get(book); // Obtém as recomendações de cada livro
            if (recs != null) {
                for (Book recommendedBook : recs) {
                    // Adiciona à lista de recomendações apenas se o livro recomendado não foi lido pelo usuário
                    if (!readBooks.contains(recommendedBook)) {
                        recommendations.add(recommendedBook);
                    }
                }
            }
        }
        return recommendations;
    }
}
