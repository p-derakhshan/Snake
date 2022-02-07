package com.der.graphics;

import com.der.Frame;
import com.der.entities.Apple;
import com.der.entities.BodyPart;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

    public static final int WIDTH = 600, HEIGHT = 600;
    private Thread thread;
    private boolean running = false;

    private BodyPart b;
    private ArrayList<BodyPart> snake;

    private Apple apple;
    private ArrayList<Apple> apples;

    private Random r;

    private int xCoor = 10, yCoor = 10;
    private int size = 5;
    private int speed = 500000;

    private boolean right = true, left = false, up = false, down = false;

    private int ticks = 0;

    private int score = 0;
    int d = 15;
    int q = WIDTH / d - 1;
    private Key key;

    private JLabel label;

    public Screen() {
        setFocusable(true);
        key = new Key();
        addKeyListener(key);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        label = new JLabel("score:");
        label.setLocation(10, 10);
        label.setVisible(true);
        add(label);

        r = new Random();

        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();

        start();
    }

    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, d);
            snake.add(b);
        }
        if (apples.size() == 0) {
            int xCoor = r.nextInt(q); //    800/10=80
            int yCoor = r.nextInt(q);
            apple = new Apple(xCoor, yCoor, d);
            apples.add(apple);
        }

        for (int i = 0; i < apples.size(); i++) { //snake eats an apple
            if (xCoor == apples.get(i).getxCoor() && yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i--;
                score++;
                speed -= 9000;
                System.out.println(speed);
                label.setText("score: " + score);
            }
        }

        for (int i = 0; i < snake.size(); i++) { //snake collides with itself
            if (xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
                if (i != snake.size() - 1) {
                    stop();
                }
            }
        }

        if (xCoor < 0) {
            xCoor = q;
        } else if (xCoor > q) {
            xCoor = 0;
        } else if (yCoor < 0) {
            yCoor = q;
        } else if (yCoor > q) {
            yCoor = 0;

        }
        ticks++;

        if (ticks > speed) { //speed of moving
            if (right) {
                xCoor++;
            }
            if (left) {
                xCoor--;
            }
            if (up) {
                yCoor--;
            }
            if (down) {
                yCoor++;
            }

            ticks = 0;

            b = new BodyPart(xCoor, yCoor, d);
            snake.add(b);

            if (snake.size() > size) {
                snake.remove(0);
            }
            //moves
        }

    }

    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.BLACK);
        for (int i = 0; i < WIDTH / d; i++) {
            g.drawLine(i * d, 0, i * d, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / d; i++) {
            g.drawLine(0, i * d, WIDTH, i * d);
        }
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }

        for (int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }
    }

    public void start() {
        running = true;
        thread = new Thread(this, "Game Loop");
        thread.start();

    }

    public void stop() {
        running = false;
        JOptionPane.showMessageDialog(null, "Your score: " + score);
        /*int result = JOptionPane.showConfirmDialog(null, "play again?", "GameOver",
                JOptionPane.YES_NO_OPTION, JOptionPane.CLOSED_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            Snake sn = new Snake();
        } else if (result == JOptionPane.NO_OPTION) {

            System.exit(0);
        }*/
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (running) {
            tick();
            repaint();
        }
    }

    private class Key implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT && !left) {
                up = false;
                down = false;
                right = true;
            }
            if (key == KeyEvent.VK_LEFT && !right) {
                up = false;
                down = false;
                left = true;
            }
            if (key == KeyEvent.VK_UP && !down) {
                left = false;
                right = false;
                up = true;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                left = false;
                right = false;
                down = true;
            }

            if (key == KeyEvent.VK_ESCAPE) {
                stop();
                System.exit(0);
            }

        }

        @Override
        public void keyTyped(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
