package fireworks;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


public class UserPanel extends JPanel {

    static CardLayout card;

    public UserPanel() {

        setLayout(new GridLayout(2, 1, 50, 50));
        MultiPanel multiPanel = new MultiPanel();
        TwoProjectilePanel twoProjectilePanel = new TwoProjectilePanel();

        add(multiPanel);
        add(twoProjectilePanel);

    }
}
