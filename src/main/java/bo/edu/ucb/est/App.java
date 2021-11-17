package bo.edu.ucb.est;
import java.util.Stack;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Tree<Integer> tree=new Tree<Integer>();
        tree.addRecursive(5); // tree.add(5)
        tree.addRecursive(4); // tree.add(4)
        tree.addRecursive(7); // tree.add(7)
        tree.addRecursive(6); // tree.add(6)
        Tree.printInOrder(tree.getRoot());
        System.out.println("FIN");
        System.out.println();
        tree.printInOrderNonRecursive();
        System.out.println("FIN");
        
        // Necesitamos una pila, probamos java.util.stack
        Stack<String> stringStack=new Stack<>();
        stringStack.push("Alfa");
        stringStack.push("Beta");
        stringStack.push("Bravo");
        stringStack.push("Gamma");
        
        System.out.println("Contenido de la pila: "+stringStack);
        System.out.println("Primer pop: "+stringStack.pop());
        System.out.println("Segundo pop: "+stringStack.pop());
        System.out.println("Primer peek: "+stringStack.peek());
        System.out.println("Segundo peek: "+stringStack.peek());
    }
}
