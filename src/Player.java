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


public class Player {
    int x;
    int y;
    int speed;
    String direction;
    BufferedImage character;

    public void update(){

    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(character, x, y, null);
    }

    Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.speed = 4;

        try {
            character = ImageIO.read(new File("src/shura.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
