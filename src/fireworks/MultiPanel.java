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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class MultiPanel extends JPanel implements ActionListener{

    JButton show = new JButton();
    Container container;
    SubPanel subP;
    List<InputsFromUser> ipList;
    List<UserGUI> guiList;
    JButton add_button;
    Integer count = 0;
    InputsFromUser il, list;
    CanvasPanel canvasPanel;
    
    protected String[] fireworks = {"Firework 1", "Firework 2", "Firework 3", "Firework 4", "Firework 5"};
    protected JComboBox fireworkType;
    
    public MultiPanel() {
        ipList = new ArrayList<InputsFromUser>();
        list = new InputsFromUser();
        list.setInputsList(ipList);

        JPanel showP = new JPanel(new GridLayout(1, 1));
        showP.add(show);

        
        fireworkType = new JComboBox(fireworks);
        fireworkType.addActionListener(this);
        add(fireworkType);  

        show.setIcon(new ImageIcon(getClass().getResource("/resource/show.jpg")));
        show.setBorderPainted(false);
        show.setFocusPainted(false);
        show.setContentAreaFilled(false);
        show.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFireworks.canvasPanel.two_cb = false;
                ipList.clear();

                for (UserGUI u : guiList) {
                    il = new InputsFromUser();
                    il.init_velocity = Double.parseDouble(u.init_velocity_text.getText());
                    il.angle = Double.parseDouble(u.angle_text.getText());
                    il.start_x = Double.parseDouble(u.start_x_text.getText());
                    il.start_y = Double.parseDouble(u.start_y_text.getText());
                    il.checkBreak = u.chkBreak.isSelected();
                    il.fireworkType = fireworkType.getSelectedIndex();
                    ipList.add(il);
                }
                list.setInputsList(ipList);

                GUIFireworks.canvasPanel.revalidate();
                GUIFireworks.canvasPanel.repaint();


            }
        });


        subP = new SubPanel();

        JScrollPane scrollerSub = new JScrollPane(subP);

        add_button = new JButton();


        add_button.setIcon(new ImageIcon(getClass().getResource("/resource/plus1.jpg")));
        add_button.setBorderPainted(false);
        add_button.setFocusPainted(false);
        add_button.setContentAreaFilled(false);

        add_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                UserGUI gui = new UserGUI();
                guiList.add(gui);
                subP.add(gui);
                subP.revalidate();
                subP.repaint();

            }
        });



        JPanel addP = new JPanel(new GridLayout(1, 0));
        addP.add(add_button);

        JPanel options = new JPanel();
        options.add(add_button);
        options.add(show);

        setLayout(new GridLayout(1, 0));

        add(scrollerSub);
        add(options);


        setBorder(new LineBorder(Color.GRAY, 2));

    }

    class SubPanel extends JPanel {

        public SubPanel() {
            setLayout(new GridLayout(2, 1, 25, 25));
            guiList = new ArrayList<UserGUI>();
            UserGUI gui = new UserGUI();
            guiList.add(gui);
            add(gui);
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
        JCheckBox chkBreak;


        UserGUI() {

            createGUI();
        }

        private void createGUI() {




            init_velocity = new JLabel("init velocity: ");



            init_velocity_text = new JTextField();


            angle = new JLabel("angle (<90) : ");

            angle_text = new JTextField();

            start_x = new JLabel("starting at x (>0) : ");

            start_x_text = new JTextField();

            start_y = new JLabel("starting at y (>0) : ");

            start_y_text = new JTextField();


            chkBreak = new JCheckBox("Break");
            //chkBreak.addItemListener(new ItemListener() {

//                public void itemStateChanged(ItemEvent e) {
//                    statusLabel.setText("Peer Checkbox: "
//                            + (e.getStateChange() == 1 ? "checked" : "unchecked"));
//                }
//            });
            

            JPanel pane = new JPanel(new GridLayout(4, 0, 5, 5));
            pane.add(init_velocity);
            pane.add(init_velocity_text);
            pane.add(angle);
            pane.add(angle_text);
            pane.add(start_x);
            pane.add(start_x_text);
            pane.add(start_y);
            pane.add(start_y_text);
            // pane.add(chkBreak);

            init_velocity_text.setText("0");
            angle_text.setText("0");
            start_x_text.setText("0");
            start_y_text.setText("0");


            remove_button = new JButton();

            remove_button.setIcon(new ImageIcon(getClass().getResource("/resource/remove.jpg")));
            remove_button.setBorderPainted(false);
            remove_button.setFocusPainted(false);
            remove_button.setContentAreaFilled(false);

            remove_button.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {

                    removeP();
                }
            });



            JPanel bp = new JPanel(new GridLayout(2, 0));

            bp.add(remove_button);
            bp.add(chkBreak);
            


            setLayout(new GridLayout(1, 2));
            add(pane);
            add(bp);
            setBorder(new LineBorder(Color.GRAY));
            
        }

        void removeP() {
            subP.remove(this);
            guiList.remove(this);
            subP.revalidate();
            subP.repaint();
        }
    }

    MultiPanel getObj() {
        return this;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setFireworkType(int fireworkType2) {
		// TODO Auto-generated method stub
		
	}
}
