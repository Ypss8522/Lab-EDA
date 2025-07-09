import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BTreeVisualizer<E extends Comparable<E>> extends JPanel {
    private BTree<E> tree;
    private static final int NODE_WIDTH = 40;
    private static final int NODE_HEIGHT = 30;
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 50;

    public BTreeVisualizer(BTree<E> tree) {
        this.tree = tree;
        setPreferredSize(new Dimension(1200, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!tree.isEmpty()) {
            drawNode(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
        }
    }

    private void drawNode(Graphics g, BNode<E> node, int x, int y, int xOffset) {
        if (node == null) return;

        int width = node.count * NODE_WIDTH;
        int startX = x - width / 2;

        for (int i = 0; i < node.count; i++) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(startX + i * NODE_WIDTH, y, NODE_WIDTH, NODE_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(startX + i * NODE_WIDTH, y, NODE_WIDTH, NODE_HEIGHT);
            g.drawString(String.valueOf(node.keys.get(i)), startX + i * NODE_WIDTH + 10, y + 20);
        }

        for (int i = 0; i <= node.count; i++) {
            BNode<E> child = node.childs.get(i);
            if (child != null) {
                int childX = x - xOffset + i * (2 * xOffset) / node.count;
                int childY = y + VERTICAL_GAP;
                g.drawLine(x, y + NODE_HEIGHT, childX, childY);
                drawNode(g, child, childX, childY, xOffset / 2);
            }
        }
    }
}