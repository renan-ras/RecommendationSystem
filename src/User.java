import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class User {
    String name;
    String email;
    Stack<Book> readingHistory;

    public User(String userName, String userEmail){
        this.name = userName;
        this.email = userEmail;
        this.readingHistory = new Stack<>();
    }

    // Sobrescrita do método toString
    @Override
    public String toString(){
        return "Usuário: " + name + " - e-mail: " + email;
    }

    // Método para adicionar elemento à pilha do histórico de leitura
    public void addToReadingHistory(Book bookToAdd) {
        readingHistory.push(bookToAdd);
    }

    // Método para remover o último elemento do histórico de leitura
    public void removeFromReadingHistory() {
        if (!readingHistory.isEmpty()) {
            Book removedBook = readingHistory.pop();
            System.out.println("Livro removido do histórico: " + removedBook);
        } else {
            System.out.println("Não há nada para ser removido, histórico vazio.");
        }
    }

    // Método para imprimir todo o histórico de leitura
    public void printReadingHistory() {
        for (Book book : readingHistory) {
            System.out.println(book);
        }
    }
}
