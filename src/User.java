import java.util.Stack;

public class User {
    private final String name;
    private final String email;
    private final Stack<Book> readingHistory;

    public User(String userName, String userEmail){
        this.name = userName;
        this.email = userEmail;
        this.readingHistory = new Stack<>();
    }

    public String getName() {
        return name;
    }

    public Stack<Book> getReadingHistory() {
        return readingHistory;
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
