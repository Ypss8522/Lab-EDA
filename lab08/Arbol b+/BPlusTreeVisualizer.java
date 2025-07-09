import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BPlusTreeVisualizer<E extends Comparable<E>> extends JPanel {
    private BPlusTree<E> tree;

    public BPlusTreeVisualizer(BPlusTree<E> tree) {
        this.tree = tree;
        setPreferredSize(new Dimension(1000, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, tree.getRoot(), 500, 40, 250);
    }

    private void drawTree(Graphics g, BPlusNode<E> node, int x, int y, int spacing) {
        if (node == null) return;

        // Dibujar nodo
        String label = node.toString();
        g.setColor(Color.CYAN);
        g.fillRoundRect(x - 20, y - 15, label.length() * 7 + 20, 30, 10, 10);
        g.setColor(Color.BLACK);
        g.drawRoundRect(x - 20, y - 15, label.length() * 7 + 20, 30, 10, 10);
        g.drawString(label, x, y);

        if (!node.isLeaf) {
            int numChildren = node.children.size();
            int childX = x - spacing * (numChildren - 1) / 2;
            for (int i = 0; i < numChildren; i++) {
                BPlusNode<E> child = node.children.get(i);
                int childPosX = childX + i * spacing;
                g.drawLine(x, y + 15, childPosX, y + 60 - 15);
                drawTree(g, child, childPosX, y + 60, spacing / 2);
            }
        }
    }

    public static <E extends Comparable<E>> void showTree(BPlusTree<E> tree) {
        JFrame frame = new JFrame("B+ Tree Visualization");
        BPlusTreeVisualizer<E> panel = new BPlusTreeVisualizer<>(tree);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}
