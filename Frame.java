package com.der;

import com.der.graphics.Screen;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.BLUE);
        
        setTitle("Snake");
        setResizable(false);
        Screen sc = new Screen();
        setSize(WIDTH,HEIGHT);
        add(sc);
        init();
    }

    public void init() {
        // setLayout(new GridLayout(1, 1, 0, 0));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
