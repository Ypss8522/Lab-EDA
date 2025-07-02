package Ejercicio3.src;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class AVLTree<T extends Comparable<T>> {
    // Raíz del árbol AVL
    private NodeAVL<T> root;

    // Bandera para indicar si la altura del árbol ha cambiado
    private boolean heightChanged;

    // Constructor: inicializa el árbol vacío
    public AVLTree() {
        root = null;
    }

    // Verifica si el árbol está vacío
    public boolean isEmpty() {
        return root == null;
    }

    // Elimina todos los nodos del árbol
    public void destroy() {
        root = null;
    }

    // Inserta un nuevo elemento en el árbol
    public void insert(T x) {
        heightChanged = false;
        root = insert(x, root);
    }

    // Elimina un elemento del árbol
    public void remove(T x) {
        heightChanged = false;
        root = remove(x, root);
    }

    public void drawTree() {
        Graph graph = new SingleGraph("AVL Tree");
        if (root != null) drawNode(graph, root, null, "root");

        graph.setAttribute("ui.stylesheet", "node { text-alignment: center; text-size: 18; fill-color: lightblue; } edge { fill-color: gray; }");
        graph.display();
    }

    private void drawNode(Graph graph, NodeAVL<T> node, NodeAVL<T> parent, String side) {
        if (node == null) return;

        String nodeId = node.data.toString();
        graph.addNode(nodeId).setAttribute("ui.label", nodeId);

        if (parent != null) {
            String parentId = parent.data.toString();
            String edgeId = parentId + "-" + nodeId;
            graph.addEdge(edgeId, parentId, nodeId, true);
        }

        if (node.left != null) drawNode(graph, node.left, node, "left");
        if (node.right != null) drawNode(graph, node.right, node, "right");
    }

    // Método recursivo para eliminar un nodo
    private NodeAVL<T> remove(T x, NodeAVL<T> node) {
        if (node == null) return null;

        int cmp = x.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(x, node.left);
            if (heightChanged) node = balanceLeftAfterRemove(node);
        } else if (cmp > 0) {
            node.right = remove(x, node.right);
            if (heightChanged) node = balanceRightAfterRemove(node);
        } else {
            // Nodo encontrado
            if (node.left == null || node.right == null) {
                // Uno o ningún hijo
                node = (node.left != null) ? node.left : node.right;
                heightChanged = true;
            } else {
                // Dos hijos: reemplazar por el menor del subárbol derecho
                NodeAVL<T> min = findMin(node.right);
                node.data = min.data;
                node.right = remove(min.data, node.right);
                if (heightChanged) node = balanceRightAfterRemove(node);
            }
        }
        return node;
    }

    // Encuentra el nodo con el valor mínimo
    private NodeAVL<T> findMin(NodeAVL<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    // Balanceo después de eliminar un nodo desde el subárbol izquierdo
    private NodeAVL<T> balanceLeftAfterRemove(NodeAVL<T> node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                break;
            case 0:
                node.bf = 1;
                heightChanged = false;
                break;
            case 1:
                NodeAVL<T> right = node.right;
                if (right.bf >= 0) {
                    node = rotateLeft(node);
                    if (right.bf == 0) {
                        node.bf = -1;
                        node.left.bf = 1;
                        heightChanged = false;
                    } else {
                        node.bf = 0;
                        node.left.bf = 0;
                    }
                } else {
                    node.right = rotateRight(right);
                    node = rotateLeft(node);
                }
                break;
        }
        return node;
    }

    // Balanceo después de eliminar un nodo desde el subárbol derecho
    private NodeAVL<T> balanceRightAfterRemove(NodeAVL<T> node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                break;
            case 0:
                node.bf = -1;
                heightChanged = false;
                break;
            case -1:
                NodeAVL<T> left = node.left;
                if (left.bf <= 0) {
                    node = rotateRight(node);
                    if (left.bf == 0) {
                        node.bf = 1;
                        node.right.bf = -1;
                        heightChanged = false;
                    } else {
                        node.bf = 0;
                        node.right.bf = 0;
                    }
                } else {
                    node.left = rotateLeft(left);
                    node = rotateRight(node);
                }
                break;
        }
        return node;
    }

    // Método recursivo para insertar un nuevo nodo
    private NodeAVL<T> insert(T x, NodeAVL<T> node) {
        if (node == null) {
            heightChanged = true;
            return new NodeAVL<>(x);
        }

        int cmp = x.compareTo(node.data);
        if (cmp == 0) {
            // Elemento duplicado
            System.out.println("Elemento duplicado: " + x);
            heightChanged = false;
        } else if (cmp < 0) {
            node.left = insert(x, node.left);
            if (heightChanged) {
                switch (node.bf) {
                    case 1:
                        node.bf = 0;
                        heightChanged = false;
                        break;
                    case 0:
                        node.bf = -1;
                        break;
                    case -1:
                        node = balanceRight(node);
                        heightChanged = false;
                        break;
                }
            }
        } else {
            node.right = insert(x, node.right);
            if (heightChanged) {
                switch (node.bf) {
                    case -1:
                        node.bf = 0;
                        heightChanged = false;
                        break;
                    case 0:
                        node.bf = 1;
                        break;
                    case 1:
                        node = balanceLeft(node);
                        heightChanged = false;
                        break;
                }
            }
        }
        return node;
    }

    // Balanceo por inserción en subárbol derecho (caso izquierdo desbalanceado)
    private NodeAVL<T> balanceLeft(NodeAVL<T> node) {
        NodeAVL<T> child = node.right;

        if (child.bf == 1) {
            // Rotación simple a la izquierda
            node.bf = 0;
            child.bf = 0;
            node = rotateLeft(node);
        } else {
            // Rotación doble derecha-izquierda
            NodeAVL<T> grandchild = child.left;

            switch (grandchild.bf) {
                case -1:
                    node.bf = 1;
                    child.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case 1:
                    node.bf = 0;
                    child.bf = -1;
                    break;
            }

            grandchild.bf = 0;
            node.right = rotateRight(child);
            node = rotateLeft(node);
        }

        return node;
    }

    // Balanceo por inserción en subárbol izquierdo (caso derecho desbalanceado)
    private NodeAVL<T> balanceRight(NodeAVL<T> node) {
        NodeAVL<T> child = node.left;

        if (child.bf == -1) {
            // Rotación simple a la derecha
            node.bf = 0;
            child.bf = 0;
            node = rotateRight(node);
        } else {
            // Rotación doble izquierda-derecha
            NodeAVL<T> grandchild = child.right;

            switch (grandchild.bf) {
                case -1:
                    node.bf = 1;
                    child.bf = 0;
                    break;
                case 0:
                    node.bf = 0;
                    child.bf = 0;
                    break;
                case 1:
                    node.bf = 0;
                    child.bf = -1;
                    break;
            }

            grandchild.bf = 0;
            node.left = rotateLeft(child);
            node = rotateRight(node);
        }

        return node;
    }

    // Rotación simple a la izquierda
    private NodeAVL<T> rotateLeft(NodeAVL<T> node) {
        NodeAVL<T> temp = node.right;
        node.right = temp.left;
        temp.left = node;
        return temp;
    }

    // Rotación simple a la derecha
    private NodeAVL<T> rotateRight(NodeAVL<T> node) {
        NodeAVL<T> temp = node.left;
        node.left = temp.right;
        temp.right = node;
        return temp;
    }

    // Búsqueda de un elemento en el árbol
    public boolean search(T x) {
        return search(root, x);
    }

    // Método recursivo de búsqueda
    private boolean search(NodeAVL<T> node, T x) {
        if (node == null) return false;
        int cmp = x.compareTo(node.data);
        if (cmp == 0) return true;
        return (cmp < 0) ? search(node.left, x) : search(node.right, x);
    }

    // Devuelve el valor mínimo del árbol
    public T min() {
        if (isEmpty()) return null;
        NodeAVL<T> temp = root;
        while (temp.left != null) temp = temp.left;
        return temp.data;
    }

    // Devuelve el valor máximo del árbol
    public T max() {
        if (isEmpty()) return null;
        NodeAVL<T> temp = root;
        while (temp.right != null) temp = temp.right;
        return temp.data;
    }

    // Devuelve el predecesor inmediato de un elemento dado
    public T predecessor(T x) {
        NodeAVL<T> curr = root, pred = null;
        while (curr != null) {
            if (x.compareTo(curr.data) > 0) {
                pred = curr;
                curr = curr.right;
            } else {
                curr = curr.left;
            }
        }
        return (pred != null) ? pred.data : null;
    }

    // Devuelve el sucesor inmediato de un elemento dado
    public T successor(T x) {
        NodeAVL<T> curr = root, succ = null;
        while (curr != null) {
            if (x.compareTo(curr.data) < 0) {
                succ = curr;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return (succ != null) ? succ.data : null;
    }

    // Recorrido inorden del árbol (izquierda, raíz, derecha)
    public void inOrder() {
        inOrder(root);
        System.out.println();
    }

    private void inOrder(NodeAVL<T> node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.data + " ");
            inOrder(node.right);
        }
    }

    // Recorrido preorden del árbol (raíz, izquierda, derecha)
    public void preOrder() {
        preOrder(root);
        System.out.println();
    }

    private void preOrder(NodeAVL<T> node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // Recorrido postorden del árbol (izquierda, derecha, raíz)
    public void postOrder() {
        postOrder(root);
        System.out.println();
    }

    private void postOrder(NodeAVL<T> node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
}
