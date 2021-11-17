package bo.edu.ucb.est;
import java.util.Stack;

public class Tree<D extends Comparable<D>> {
    private Node root;

    // altura??
    public Tree() {
    
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root=root;
    }

    public void add(D data) {
        Node<D> newNode=new Node<>(data);
        if (root==null) { // árbol vacío
            root=newNode;
        }
        else {
            Node<D> current=root;
            while (current!=null) {
                if ((current.getData()).compareTo(data)>0) { // si la data nueva es menor a la data del current
                    if (current.getLeft()==null) { // tengo el espacio vacío para agregar el nodo
                        current.setLeft(newNode);
                        break; // terminamos el recorrido
                    }
                    else { // continúo bajando por la rama izquierda
                        current=current.getLeft();
                    }
                }
                else if ((current.getData()).compareTo(data)<0) { // si la data nueva es mayor a la data del current
                    if (current.getRight()==null) { // tengo el espacio vacío para agregar el nodo
                        current.setRight(newNode);
                        break; // terminamos el recorrido
                    }
                    else { // continúo bajando por la rama derecha
                        current=current.getRight();
                    }
                }
                else { // igual a cero
                    // entonces el elemento ya existe.
                    throw new RuntimeException("No se puede agregar elementos duplicados a un ABB.");
                }
            }
        }
    }
    
    public void addRecursive(D data) {
        Node<D> newNode=new Node<>(data);
        if (root==null) { // árbol vacío
            root=newNode;
        }
        else {
            addDataToNode(root,newNode);
        }
    }
    
    /*
    Determinar si newNode debe ir a la izquierda o a la derecha.
    Si debe ir a la izquierda o a la derecha, verificar si la rama está libre.
        - Si está libre, agregar newNode a la rama correspondiente.
        - Si no está libre, recorrer la rama recursivamente.
    */
    private void addDataToNode(Node<D> current,Node<D> newNode) {
        if (current!=null) { // este if está por demás
            if ((current.getData()).compareTo(newNode.getData())>0) { // si la data nueva es menor a la data del current
                if (current.getLeft()==null) { // tengo el espacio vacío para agregar el nodo
                    current.setLeft(newNode);
                }
                else { // continúo bajando por la rama izquierda
                    addDataToNode(current.getLeft(),newNode);
                }
            }
            else if ((current.getData()).compareTo(newNode.getData())<0) { // si la data nueva es mayor a la data del current
                if (current.getRight()==null) { // tengo el espacio vacío para agregar el nodo
                    current.setRight(newNode);
                }
                else { // continúo bajando por la rama derecha
                    addDataToNode(current.getRight(),newNode);
                }
            }
            else { // si la data nueva es igual a la data del current
                throw new RuntimeException("No se puede agregar elementos duplicados a un ABB.");
            }
        }
    }
    
    /*
    Hasta que todos los nodos hayan sido atravesados.
    Paso 1 − Recorrer recursivamente el subárbol izquierdo.
    Paso 2 − Visitar el nodo raíz.
    Paso 3 − Recorrer recursivamente el subárbol derecho.
    */
    public static void printInOrder(Node<?> root) {
        if (root==null) {
            // sal de la función
        }
        else {
            printInOrder(root.getLeft()); // paso 1
            System.out.println(root.getData()); // paso 2
            printInOrder(root.getRight()); // paso 3
        }
    }
    
    /*
    1. Crear un stack vacío.
    2. Inicializar el nodo current como root.
    3. Insertar el nodo current al stack y asignar current=current.getLeft(), hasta que el actual sea null.
    4. Si current es null y el stack no está vacío, entonces:
        4.1. Hacer pop del stack.
        4.2. Imprimir el elemento del que se hizo pop y asignar current=popNode.getRight();
        4.3. Ir al paso 3.
    5. Si current es null y el stack está vacío, entonces hemos terminado.
    @param root nodo a partir del cual se comienza a realizar in order recursivo.
    */
    public void printInOrderNonRecursive() {
        Stack<Node<D>> stack=new Stack<>();
        Node<D> current=root;
        Node<D> popNode;
        for (;;) {
            while (current!=null) {
                stack.push(current);
                current=current.getLeft();
            }
            if (stack.isEmpty()==false) {
                popNode=stack.pop();
                System.out.println(popNode.getData());
                current=popNode.getRight();
            }
            else {
                break;
            }
        }
    }
    
    /*
    Hasta que todos los nodos hayan sido atravesados.
    Paso 1 − Visitar el nodo raíz.
    Paso 2 − Recorrer recursivamente el subárbol izquierdo.
    Paso 3 − Recorrer recursivamente el subárbol derecho.
    */
    public static void printPreOrder(Node<?> root) {
        if (root==null) {
            // sal de la función
        }
        else {
            System.out.println(root.getData()); // paso 1
            printPreOrder(root.getLeft()); // paso 2
            printPreOrder(root.getRight()); // paso 3
        }
    }
    
    /*
    Hasta que todos los nodos hayan sido atravesados.
    Paso 1 − Recorrer recursivamente el subárbol izquierdo.
    Paso 2 − Recorrer recursivamente el subárbol derecho.
    Paso 3 − Visitar el nodo raíz.
    */
    public static void printPostOrder(Node<?> root) {
        if (root==null) {
            // sal de la función
        }
        else {
            printPostOrder(root.getLeft()); // paso 1
            printPostOrder(root.getRight()); // paso 2
            System.out.println(root.getData()); // paso 3
        }
    }
    
    /*
    Elimina un nodo del árbol y lo rebalancea.
    @param data
    
    1. Si el nodo que se va a eliminar no tiene hijo, es una hoja.
    Este es el caso más simple; dado que un nodo hoja no tiene hijos, no necesitamos preocuparnos por nada.
    Podemos reemplazar el nodo hoja con null y liberar el espacio asignado a este nodo.
    
    2. Si el nodo que se va a eliminar tiene un solo hijo (hijo izquierdo o derecho).
    En este caso, almacenamos el hijo del nodo y eliminamos el nodo de su posición original. Luego, el nodo
    hijo se inserta en la posición original del nodo eliminado.
    
    3. Si el nodo que se va a eliminar tiene hijos, hijo izquierdo y derecho.
    Este es el caso más complicado porque aquí, no podemos simplemente eliminar o reemplazar el nodo con su
    hijo. En este caso, encontramos el nodo más pequeño en el subárbol derecho del nodo minnode. Reemplace
    el valor del nodo que se eliminará con el valor de minnode y llame de forma recursiva a delete en este nodo.
    */
    
    // FALTA CONSIDERAR SI ES EL NODO RAÍZ *******
    public void remove(D data) {
        // BÚSQUEDA
        Node<D> current=root;
        Node<D> padre=null;
        Node<D> eliminacion=null;
        for (;;) {
            if ((current.getData()).compareTo(data)>0) {
                padre=current;
                current=current.getLeft();
            }
            else if ((current.getData()).compareTo(data)<0) {
                padre=current;
                current=current.getRight();
            }
            else { // ya lo encontró
                eliminacion=current;
                break;
            }
        }
        if (eliminacion.getLeft()==null && eliminacion.getRight()==null) { // si es un nodo hoja
            if (padre.getLeft()==eliminacion) {
                padre.setLeft(null);
            }
            else {
                padre.setRight(null);
            }
        }
        else if (eliminacion.getLeft()==null || eliminacion.getRight()==null) { // si el nodo tiene un solo hijo
            if (padre.getLeft()==eliminacion) {
                if (eliminacion.getRight()==null && eliminacion.getLeft()!=null) {
                    padre.setLeft(eliminacion.getLeft());
                }
                else if (eliminacion.getRight()!=null && eliminacion.getLeft()==null) {
                    padre.setLeft(eliminacion.getRight());
                }
            }
            else {
                if (eliminacion.getRight()==null && eliminacion.getLeft()!=null) {
                    padre.setRight(eliminacion.getLeft());
                }
                else if (eliminacion.getRight()!=null && eliminacion.getLeft()==null) {
                    padre.setRight(eliminacion.getRight());
                }
            }
        }
        else { // si el nodo tiene ambos hijos
            if (eliminacion.getRight()!=null && eliminacion.getLeft()!=null) {
                Node<D> minNode=null;
                // BÚSQUEDA DEL minNode
                for (Node<D> pointer=eliminacion.getRight(); pointer!=null;) {
                    minNode=pointer;
                    pointer=pointer.getLeft();
                }
                System.out.println("Se reemplazará con este: "+minNode.getData());
                remove(minNode.getData());
                minNode.setRight(eliminacion.getRight());
                minNode.setLeft(eliminacion.getLeft());
                if (padre.getLeft()==eliminacion) {
                    padre.setLeft(minNode);
                }
                else {
                    padre.setRight(minNode);
                }
            }
        }
    }
}
