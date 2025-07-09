import javax.swing.*;

public class VisualizerLauncher {
    public static void showTree(BTree<Integer> tree) {
        JFrame frame = new JFrame("Visualización Árbol B");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().add(new BTreeVisualizer<>(tree));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}