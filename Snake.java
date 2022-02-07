package com.der;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        setPreferredSize(new Dimension(600, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        JButton b = new JButton("Start");
        b.setBackground(new Color(255,255,255));
        add(b);
        
b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Frame f = new Frame();
                //f.setVisible(true);
                f.setBackground(new Color(255,255,255));
                setVisible(false);

            }
        });
        init();
    }

    public void init() {
        // setLayout(new GridLayout(1, 1, 0, 0));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Snake();
    }

}
