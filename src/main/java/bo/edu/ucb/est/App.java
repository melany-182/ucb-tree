package bo.edu.ucb.est;
import java.util.Stack;
/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Tree<Integer> tree=new Tree<Integer>();
        tree.addRecursive(50); // tree.add(50)
        tree.addRecursive(20); // tree.add(20)
        tree.addRecursive(40); // tree.add(40)
        tree.addRecursive(35); // tree.add(35)
        tree.addRecursive(70); // tree.add(70)
        tree.addRecursive(80); // tree.add(80)
        tree.addRecursive(60); // tree.add(60)
        tree.addRecursive(90); // tree.add(90)
        System.out.println("Recursive: ");
        Tree.printInOrder(tree.getRoot());
        System.out.println("FIN\n");
        tree.remove(4);
        // tree.remove(8); // NullPointerException
        System.out.println("Non recursive: ");
        tree.printInOrderNonRecursive();
        System.out.println("FIN\n");
        
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
