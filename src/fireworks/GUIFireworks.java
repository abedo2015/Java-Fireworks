/*
 * Name: Abraham Do
 * Project 3
 * Lab: Tuesdays and Thursdays @ 11:05-12:20
 * TA: Becky Everson/Jeremiah Bill/Carter Letsky
 *
 * I affirm that I have not given or received any unauthorized help on this assignment, and that this work is my own.
 */

package fireworks;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GUIFireworks extends JFrame {

    private double initVelocity = 30.0;
    private double angle = 49.0;
    private UserPanel userPanel;
    static CanvasPanel canvasPanel;

    public GUIFireworks() {
        super("Projectile explosion");
        angle = ((Math.PI) / 180) * angle;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        createGUI();

    }

    private void createGUI() {
        BorderLayout layout = new BorderLayout();
        layout.setHgap(70);
        layout.setVgap(70);
        setLayout(layout);

        setLayout(new GridLayout(1, 2, 10, 10));
        userPanel = new UserPanel();
        canvasPanel = new CanvasPanel();
        add(userPanel);
        add(canvasPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                GUIFireworks guif = new GUIFireworks();
                guif.setVisible(true);
            }
        });
    }
}
