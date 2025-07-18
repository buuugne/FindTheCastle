import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;


import javax.imageio.ImageIO; //tam, kad galeciau sukurti nuotrauka kaip objekta
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.io.*;


public class Player implements KeyListener {
    int x, y, speed; //speed - kiek pikseliu pajudes per viena paspaudima
    BufferedImage character;
    BufferedImage spreadsheet;
    int frameWidth = 64;
    int frameHeight = 64;

    boolean up, down, left, right;

    public void update(){
        if(up) y -= speed;
        if(down) y += speed;
        if(left) x -= speed;
        if(right) x += speed;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) up = true;
        if (code == KeyEvent.VK_DOWN) down = true;
        if (code == KeyEvent.VK_LEFT) left = true;
        if (code == KeyEvent.VK_RIGHT) right = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP) up = false;
        if (code == KeyEvent.VK_DOWN) down = false;
        if (code == KeyEvent.VK_LEFT) left = false;
        if (code == KeyEvent.VK_RIGHT) right = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {};

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(character, x, y, null);
    }

    Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 4;

        try {
            spreadsheet = ImageIO.read(new File("src/shura.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        character = spreadsheet.getSubimage(0, 0, frameWidth, frameHeight);
    }
}
