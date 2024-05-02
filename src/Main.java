/*
* Aluno: Renan Antonio da Silva
* RA: 1112023101779
* Curso: Big Data e Inteligência Analítica
*
* Para facilitar a correção foram adicionados comentários identificando o título dos livros e
* forncecidas algumas saídas do console após os símbolos "->"
*
* Para auxílio a compreensão e visualização do código, veja os anexos:
* 1 - Lista de livros cadastrados e suas recomendações
* 2 - Grafo Livros e Recomendações (Com direção, Sem peso)
* 3 - Árvore binária com 10 livros
* 4 - Árvore binária com 9 livros (sem O Conto da Aia)
* */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        RecommendationSystem rs = new RecommendationSystem();

        //region Criando dez livros
        Book book1 = new Book("1984", "George Orwell", 1949);
        Book book2 = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
        Book book3 = new Book("Admirável Mundo Novo", "Aldous Huxley", 1932);
        Book book4 = new Book("O Conto da Aia", "Margaret Atwood", 1985);
        Book book5 = new Book("A Mão Esquerda da Escuridão", "Ursula K. Le Guin", 1969);
        Book book6 = new Book("Duna", "Frank Herbert", 1965);
        Book book7 = new Book("Fundação", "Isaac Asimov", 1951);
        Book book8 = new Book("Neuromancer", "William Gibson", 1984);
        Book book9 = new Book("Snow Crash", "Neal Stephenson", 1992);
        Book book10 = new Book("Criptonomicon", "Neal Stephenson", 1999);
        //endregion

        //region Adicionando duas recomendações para cada livro
        //Mais detalhes sobre as recomendações no anexo 1 - Lista de livros cadastrados e suas recomendações
        Set<Book> recsForBook1 = new HashSet<>(); //Armazenará as recomendações para o livro: 1984
        recsForBook1.add(book2); //Adiciona recomendação para o livro 1984: Fahrenheit 451
        recsForBook1.add(book3); //Adiciona recomendação para o livro 1984: Admirável Mundo Novo
        rs.addBookAndRecommendations(book1, recsForBook1); //Adicionamos ao grafo, o livro 1 e suas recomendações

        Set<Book> recsForBook2 = new HashSet<>(); //Fahrenheit 451
        recsForBook2.add(book1); //1984
        recsForBook2.add(book4); //O Conto da Aia
        rs.addBookAndRecommendations(book2, recsForBook2);

        Set<Book> recsForBook3 = new HashSet<>(); //Admirável Mundo Novo
        recsForBook3.add(book1); //1984
        recsForBook3.add(book2); //Fahrenheit 451
        rs.addBookAndRecommendations(book3, recsForBook3);

        Set<Book> recsForBook4 = new HashSet<>(); //O Conto da Aia
        recsForBook4.add(book2); //Fahrenheit 451
        recsForBook4.add(book5); //A Mão Esquerda da Escuridão
        rs.addBookAndRecommendations(book4, recsForBook4);

        Set<Book> recsForBook5 = new HashSet<>(); //A Mão Esquerda da Escuridão
        recsForBook5.add(book6); //Duna
        recsForBook5.add(book4); //O Conto da Aia
        rs.addBookAndRecommendations(book5, recsForBook5);

        Set<Book> recsForBook6 = new HashSet<>(); //Duna
        recsForBook6.add(book7); //Fundação
        recsForBook6.add(book5); //A Mão Esquerda da Escuridão
        rs.addBookAndRecommendations(book6, recsForBook6);

        Set<Book> recsForBook7 = new HashSet<>(); //Fundação
        recsForBook7.add(book6); //Duna
        recsForBook7.add(book8); //Neuromancer
        rs.addBookAndRecommendations(book7, recsForBook7);

        Set<Book> recsForBook8 = new HashSet<>(); //Neuromancer
        recsForBook8.add(book9); //Snow Crash
        recsForBook8.add(book7); //Fundação
        rs.addBookAndRecommendations(book8, recsForBook8);

        Set<Book> recsForBook9 = new HashSet<>(); //Snow Crash
        recsForBook9.add(book8); //Neuromancer
        recsForBook9.add(book10); //Criptonomicon
        rs.addBookAndRecommendations(book9, recsForBook9);

        Set<Book> recsForBook10 = new HashSet<>(); //Criptonomicon
        recsForBook10.add(book9); //Snow Crash
        recsForBook10.add(book8); //Neuromancer
        rs.addBookAndRecommendations(book10, recsForBook10);
        //endregion

        //region Adicionando usuários e histórico de leitura
        //Criando usuários
        User user1 = new User("João", "joao@pucpr.edu.br");
        User user2 = new User("Maria", "maria@pucpr.edu.br");
        User user3 = new User("Carlos", "carlos@pucpr.edu.br");

        //Adicionando histórico de leitura aos usuários
        user1.addToReadingHistory(book1); //1984
        user1.addToReadingHistory(book5); //A Mão Esquerda da Escuridão

        user2.addToReadingHistory(book7); //Fundação
        user2.addToReadingHistory(book6); //Duna

        user3.addToReadingHistory(book10); //Criptonomicon
        user3.addToReadingHistory(book9); //Snow Crash
        user3.addToReadingHistory(book8); //Neuromancer
        //endregion

        //region Recomendações por usuário (HashMap)
        System.out.println("\n=============================================");
        System.out.println("Recomendações de livros por usuário (HashMap)");
        System.out.println("=============================================\n");

        //Exibindo recomendações baseadas no histórico de leitura de cada usuário
        //As recomendações incluem apenas livros que não foram lidos pelo usuário
        System.out.println("Recomendações para " + user1.getName() + ":");
        Set<Book> recommendationsUser1 = rs.getRecommendations(user1);
        for (Book b : recommendationsUser1) {
            System.out.println(b.getTitle());
        }
//        ->
//        Recomendações para João
//        Admirável Mundo Novo
//        Duna
//        Fahrenheit 451
//        O Conto da Aia

        System.out.println();
        System.out.println("Recomendações para " + user2.getName() + ":");
        Set<Book> recommendationsUser2 = rs.getRecommendations(user2);
        for (Book b : recommendationsUser2) {
            System.out.println(b.getTitle());
        }
//        ->
//        Recomendações para Maria
//        A Mão Esquerda da Escuridão
//        Neuromancer

        System.out.println();
        System.out.println("Recomendações para " + user3.getName() + ":");
        Set<Book> recommendationsUser3 = rs.getRecommendations(user3);
        for (Book b : recommendationsUser3) {
            System.out.println(b.getTitle());
        }
//        ->
//        Recomendações para Carlos
//        Fundação
        //endregion

        //region Teste do histórico de leitura do usuário (Pilha)
        System.out.println("\n================================================");
        System.out.println("Teste do histórico de leitura do usuário (Pilha)");
        System.out.println("================================================\n");

        //Imprimir histórico, adicionar e remover livro e imprimir novamente
        //Imprimir histórico de leitura do usuário 1 (João)
        System.out.println("Histórico de leitura do usuário " + user1.getName() + ":");
        user1.printReadingHistory();
//        ->
//        Histórico de leitura do usuário João
//        1984, George Orwell, 1949
//        A Mão Esquerda da Escuridão, Ursula K. Le Guin, 1969

        //Adicionar livro ao histórico
        user1.addToReadingHistory(book6); //Duna

        //Imprimir histórico de leitura do usuário 1 (João)
        System.out.println();
        System.out.println("Histórico de leitura do usuário " + user1.getName() + ":");
        user1.printReadingHistory();
//        ->
//        Histórico de leitura do usuário João
//        1984, George Orwell, 1949
//        A Mão Esquerda da Escuridão, Ursula K. Le Guin, 1969
//        Duna, Frank Herbert, 1965

        //João não gostou do livro e quer removê-lo de seu histórico de leitura
        System.out.println();
        user1.removeFromReadingHistory();
//        ->
//        Livro removido do histórico: Duna, Frank Herbert, 1965

        //Imprimir histórico de leitura do usuário 1 (João)
        System.out.println();
        System.out.println("Histórico de leitura do usuário " + user1.getName() + ":");
        user1.printReadingHistory();
//        ->
//        Histórico de leitura do usuário João
//        1984, George Orwell, 1949
//        A Mão Esquerda da Escuridão, Ursula K. Le Guin, 1969

        //Limpando o histórico do usuário 1
        System.out.println();
        user1.removeFromReadingHistory(); //-> Livro removido do histórico: A Mão Esquerda da Escuridão, Ursula K. Le Guin, 1969
        user1.removeFromReadingHistory(); //-> Livro removido do histórico: 1984, George Orwell, 1949
        user1.removeFromReadingHistory(); //-> Não há nada para ser removido, histórico vazio.
        user1.removeFromReadingHistory(); //-> Não há nada para ser removido, histórico vazio.
        //endregion

        //region Teste da lista de espera de um livro (Fila)
        System.out.println("\n===========================================");
        System.out.println("Teste da lista de espera de um livro (Fila)");
        System.out.println("===========================================\n");

        System.out.println("Lista de espera para o livro: " + book1.getTitle());
        book1.printWaitingList(); //-> A lista de espera está vazia.
        System.out.println();
        book1.addUserToWaitingList(user2); //Maria
        book1.addUserToWaitingList(user3); //Carlos
        book1.printWaitingList();
//        ->
//        Lista de espera para o livro: 1984
//        Usuário: Maria - e-mail: maria@pucpr.edu.br
//        Usuário: Carlos - e-mail: carlos@pucpr.edu.br

        System.out.println();
        book1.removeUserFromWaitingList(); //Maria foi removido(a) da lista de espera.
        System.out.println();
        book1.printWaitingList();
//        ->
//        Lista de espera para o livro: 1984
//        Usuário: Carlos - e-mail: carlos@pucpr.edu.br

        System.out.println();
        book1.removeUserFromWaitingList(); //-> Carlos foi removido(a) da lista de espera.
        book1.removeUserFromWaitingList(); //-> Não há nada para ser removido, a lista de espera está vazia.
        //endregion

        //Código adicional da somativa 2
        System.out.println("\n===========================================");
        System.out.println("SOMATIVA 2");
        System.out.println("===========================================\n");

        /*
        * Para auxílio a compreensão e visualização do código, veja os anexos:
        * 1 - Lista de livros cadastrados e suas recomendações
        * 2 - Grafo Livros e Recomendações (Com direção, Sem peso)
        * 3 - Árvore binária com 10 livros
        * 4 - Árvore binária com 9 livros (sem O Conto da Aia)
        * */

        //region Árvore binária
        System.out.println("\n===========================================");
        System.out.println("Teste da árvore binária");
        System.out.println("===========================================\n");

        BinaryTree myTree = new BinaryTree();

        myTree.insert(book1);
        myTree.insert(book2);
        myTree.insert(book3);
        myTree.insert(book4);
        myTree.insert(book5);
        myTree.insert(book6);
        myTree.insert(book7);
        myTree.insert(book8);
        myTree.insert(book9);
        myTree.insert(book10);

        //Busca
        System.out.println("A busca de \"Neuromancer\" retornou: " + myTree.search("Neuromancer")); //true
        //Encontra o título "duna" ignorando maiúscula ("Duna")
        System.out.println("A busca de \"duna\" retornou: " + myTree.search("duna")); //true
        System.out.println("A busca de \"Crepusculo\" retornou: " + myTree.search("Crepusculo") + "\n"); //false

        //Mostrar
        //Para melhor visualização ver anexo 3 - Árvore binária com 10 livros.png
        System.out.println("Árvore com 10 livros:");
        myTree.show();

        //Remover
        myTree.remove("O Conto da Aia");
        //Para melhor visualização ver anexo 4 - Árvore binária com 9 livros (sem O Conto da Aia).png
        System.out.println("\nÁrvore com 9 livros (sem \"O Conto da Aia\"):");
        myTree.show();
        //endregion

        //region Djikstra
        System.out.println("\n===========================================");
        System.out.println("Teste Djikstra");
        System.out.println("===========================================\n");

        //***
        //Obter níveis de relacionamento para o livro 1
        //***
        Map<Book, Integer> djikstraBook1 = Djikstra.djikstraSimples(rs.getGraph(), book1);

        //Imprimir a distância entre o livro 1 e os demais
        System.out.println("Relacionamentos do livro: " + book1);
        for (Map.Entry<Book, Integer> entrada : djikstraBook1.entrySet()) {
            System.out.println("Distância do " + book1.getTitle() + " para " + entrada.getKey().getTitle() + " é " + entrada.getValue());
        }
        /*
        * Ver anexo 2 - Grafo Livros e Recomendações (Com direção, Sem peso).png
        * Note que a distância do livro 1 até o 10 é de 8, pois é um grafo com direção
        * o 10 tem conexão para o 8, mas o contrário não é verdadeiro
        * */

        //***
        // Teste de recomendações para usuário baseado em seu histórico de leitura
        //***
        System.out.println("\nHistórico de leitura de " + user2.getName() + ":");
        user2.printReadingHistory();

        //Obter todas as recomendações a partir do histórico de leitura
        Map<Book, Integer> djikstraUser2 = Djikstra.djikstraRecommendations(rs.getGraph(), user2.getReadingHistory());

        System.out.println("\nAs recomendações para " + user2.getName() + " são:");
        for (Map.Entry<Book, Integer> entrada : djikstraUser2.entrySet()) {
            System.out.println("Nível " + entrada.getValue() + " - " + entrada.getKey());
        }

        System.out.println("Quanto menor o nível mais forte é a recomendação!");

        /*Faz a união das recomendações djikstraSimples de cada livro
        * para livros repetidos mantém o de menor distância
        * não inclui nas recomendações os livros já lidos*/

        //endregion
    }
}