package src;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

public class BST<T extends Comparable<T>> {
    private Node<T> root;

    public BST() {
        this.root = null;
    }

    // Insertar un dato en el BST
    public void insert(T data) {
        root = insertRec(root, data);
    }

    private Node<T> insertRec(Node<T> node, T data) {
        if (node == null) return new Node<>(data);
        if (data.compareTo(node.getData()) < 0)
            node.left = insertRec(node.left, data);
        else if (data.compareTo(node.getData()) > 0)
            node.right = insertRec(node.right, data);
        return node;
    }

    // Recorrido InOrden
    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node<T> node) {
        if (node != null) {
            inOrderRec(node.left);
            System.out.println(node.getData());
            inOrderRec(node.right);
        }
    }

    // Dibujar el árbol usando GraphStream
    public void draw() {
        System.setProperty("org.graphstream.ui", "swing");
        Graph graph = new SingleGraph("BST");

        if (root != null) {
            drawRec(graph, root, null, false);
        }

        graph.display();
    }

    private void drawRec(Graph graph, Node<T> current, Node<T> parent, boolean isLeft) {
        String currentId = current.getData().toString();
        if (graph.getNode(currentId) == null)
            graph.addNode(currentId).setAttribute("ui.label", currentId);

        if (parent != null) {
            String parentId = parent.getData().toString();
            String edgeId = parentId + "-" + currentId;
            if (graph.getEdge(edgeId) == null)
                graph.addEdge(edgeId, parentId, currentId, true);
        }

        if (current.left != null)
            drawRec(graph, current.left, current, true);
        if (current.right != null)
            drawRec(graph, current.right, current, false);
    }
}
