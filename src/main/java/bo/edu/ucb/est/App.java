package bo.edu.ucb.est;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )  {
        Tree<Integer> tree = new Tree<Integer>();
        tree.add(5);
        tree.add(4);
        tree.add(7);
        tree.add(6);
        Tree.printInOrder(tree.getRoot());
        System.out.println("FIN");

    }
}
