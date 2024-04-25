public class BinaryTree {
    Node root;

    public BinaryTree() {
        root = null;
    }

    //Insert node
    public void insert(Book book) {
        root = recursiveInsert(root, book);
    }

    private Node recursiveInsert(Node current, Book book) {
        if (current == null) { return new Node(book); }

        if (book.getTitle().compareToIgnoreCase(current.book.getTitle()) < 0) { // Case insensitive alphabetical comparison
            current.left = recursiveInsert(current.left, book);
        } else if (book.getTitle().compareToIgnoreCase(current.book.getTitle()) > 0) {
            current.right = recursiveInsert(current.right, book);
        }

        return current;
    }

    //Search node
    public boolean search(String title) {
        return recursiveSearch(root, title);
    }

    private boolean recursiveSearch(Node current, String title) {
        if (current == null) { return false; }

        if (current.book.getTitle().equalsIgnoreCase(title)) { return true; }

        return current.book.getTitle().compareToIgnoreCase(title) > 0
                ? recursiveSearch(current.left, title)
                : recursiveSearch(current.right, title);
    }

    //Remove node
    public void remove(String title) {
        root = recursiveRemove(root, title);
    }

    private Node recursiveRemove(Node current, String title) {
        if (current == null) { return null; }

        if (current.book.getTitle().equalsIgnoreCase(title)){
            //Node without sons
            if (current.left == null && current.right == null) { return null; }

            //Node with one son
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }

            //Node with two sons
            Book lastBook = searchLastBook(current.left);
            current.book = lastBook;
            current.left = recursiveRemove(current.left, lastBook.getTitle());
            return current;
        }

        if (current.book.getTitle().compareToIgnoreCase(title) > 0) {
            current.left = recursiveRemove(current.left, title);
            return current;
        }

        current.right = recursiveRemove(current.right, title);
        return current;
    }

    private Book searchLastBook(Node root) {
        return root.right == null ? root.book : searchLastBook(root.right);
    }

    // Mostrar árvore em preordem
    public void show() {
        recursiveShow(root, 0);
    }

    private void recursiveShow(Node current, int level) {
        if (current != null) {
            if (level > 0){
                System.out.print("     ".repeat(level - 1) +  "└----");
            }

            System.out.println(current.book.getTitle());
            // Para cada nível, aumentamos o nível para os nós filhos
            recursiveShow(current.left, level + 1);
            recursiveShow(current.right, level + 1);
        }
    }
}
