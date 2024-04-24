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
}
