package fireworks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TwoProjectilePanel extends JPanel {

    JLabel title = new JLabel("Two projectile");

    JButton show = new JButton();
    Container container;
    SubPanel subP;
    List<InputsFromUser> ipList;
    List<UserGUI> guiList;
    JButton add_button;
    Integer count = 0;
    InputsFromUser il, list;
    CanvasPanel canvasPanel;

    public TwoProjectilePanel() {

        ipList = new ArrayList<InputsFromUser>();
        list = new InputsFromUser();
        list.setInputsList(ipList);

        BorderLayout layout = new BorderLayout(10, 10);
        setLayout(layout);

        JPanel showP = new JPanel();
        showP.add(show);
        show.setIcon(new ImageIcon(getClass().getResource("/resource/show.jpg")));
        show.setBorderPainted(false);
        show.setFocusPainted(false);
        show.setContentAreaFilled(false);

        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFireworks.canvasPanel.two_cb = true;
                ipList.clear();

                for (UserGUI u : guiList) {
                    il = new InputsFromUser();
                    il.init_velocity = Double.parseDouble(u.init_velocity_text.getText());
                    il.angle = Double.parseDouble(u.angle_text.getText());
                    il.start_x = 0;
                    il.start_y = 0;
                    ipList.add(il);


                }
                list.setInputsList(ipList);

                GUIFireworks.canvasPanel.revalidate();
                GUIFireworks.canvasPanel.repaint();




            }
        });


        subP = new SubPanel();


        JPanel options = new JPanel();
        options.add(show);

        setLayout(new GridLayout(1, 0));
        add(title);
        add(subP);
        add(options);

        setBorder(new LineBorder(Color.GRAY,2));
    }

    class SubPanel extends JPanel {

        public SubPanel() {
            setLayout(new GridLayout(2, 0, 0, 25));

            guiList = new ArrayList<UserGUI>();
            UserGUI gui1 = new UserGUI();
            guiList.add(gui1);
            add(gui1);

            UserGUI gui2 = new UserGUI();
            guiList.add(gui2);
            add(gui2);


        }
    }

    class UserGUI extends JPanel {

        JLabel init_velocity;
        JLabel angle;
        JLabel look;
        JLabel start_x;
        JLabel start_y;
        JTextField init_velocity_text;
        JTextField angle_text;
        JTextField start_x_text;
        JTextField start_y_text;
        JButton remove_button;
        boolean button_press;

        UserGUI() {

            createGUI();
        }

        private void createGUI() {

            BorderLayout layout = new BorderLayout();
            layout.setHgap(70);
            layout.setVgap(30);
            setLayout(layout);




            init_velocity = new JLabel("initial velocity: ");

            init_velocity_text = new JTextField(20);

            angle = new JLabel("angle:(<90) ");

            angle_text = new JTextField(20);


            init_velocity_text.setText("0");
            angle_text.setText("0");



            JPanel pane = new JPanel(new GridLayout(4, 0, 5, 5));
            pane.add(init_velocity);
            pane.add(init_velocity_text);
            pane.add(angle);
            pane.add(angle_text);

            init_velocity_text.setText("0");
            angle_text.setText("0");







            setLayout(new GridLayout(1, 1));
            add(pane);

            setBorder(new LineBorder(Color.GRAY));

        }
    }

    TwoProjectilePanel getObj() {
        return this;
    }
}
