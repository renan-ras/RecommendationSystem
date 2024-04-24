import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Book {
    String title;
    String author;
    int year;
    Queue<User> waitingList;
    Set<Book> recsForBook;
    RecommendationSystem rs;

    public Book(String bookTitle, String bookAuthor, int bookYear){
        this.title = bookTitle;
        this.author = bookAuthor;
        this.year = bookYear;
        this.waitingList = new LinkedList<>();
        this.recsForBook = new HashSet<>();
        this.rs = new RecommendationSystem();
    }

    @Override
    public String toString(){
        return title + ", " + author + ", " + year;
    }

    // Método para adicionar um usuário à lista de espera
    public void addUserToWaitingList(User user) {
        waitingList.offer(user);  // Adiciona o usuário ao final da fila
    }

    // Método para remover um usuário da lista de espera
    public void removeUserFromWaitingList() {
        User removedUser = waitingList.poll();  // Remove e retorna o usuário do início da fila
        if (removedUser != null) {
            System.out.println(removedUser.name + " foi removido(a) da lista de espera.");
        } else {
            System.out.println("Não há nada para ser removido, a lista de espera está vazia.");
        }
    }

    // Método para imprimir todos os usuários na lista de espera
    public void printWaitingList() {
        if (waitingList.isEmpty()) {
            System.out.println("A lista de espera está vazia.");
        } else {
            System.out.println("Lista de espera para o livro: " + title);
            for (User user : waitingList) {
                System.out.println(user);
            }
        }
    }
}
