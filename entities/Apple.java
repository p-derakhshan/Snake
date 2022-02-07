package com.der.entities;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {

    private int xCoor, yCoor, width, height;

    public Apple(int xCoor, int yCoor, int size) {
        this.xCoor = xCoor;
        this.yCoor = yCoor;
        width = size;
        height = size;
    }

    public void tick() {

    }

    public void draw(Graphics g) {
        g.setColor(new Color(255,80,128));
        g.fillRect(xCoor * width, yCoor * height, width, height);
        g.setColor(Color.RED);
        g.fillRect(xCoor * width + 2, yCoor * height + 2, width-4, height-4);

    }

    public int getxCoor() {
        return xCoor;
    }

    public void setxCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public int getyCoor() {
        return yCoor;
    }

    public void setyCoor(int yCoor) {
        this.yCoor = yCoor;
    }
}
