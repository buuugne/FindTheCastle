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
    int frameTick = 10;
    int maxFrames = 9;
    int directionIndex = 0;//0 - up, 1 - left, 2 - down, 3 - right
    int animationFrame = 0;       // Kuris kadras animacijoje ju yra 9
    int animationSpeed = 10;      // Kiek `update()` kvietimų reikia iki sek. kadro
    int boardWidth, boardHeight;


    boolean up, down, left, right;

    public void update(){
        boolean moving = false;

        if(up) {
            y -= speed;
            directionIndex = 0;
            moving = true;
        }
        if(left) {
            x -= speed;
            directionIndex = 1;
            moving = true;
        }
        if(down) {
            y += speed;
            directionIndex = 2;
            moving = true;
        }
        if(right) {
            x += speed;
            directionIndex = 3;
            moving = true;
        }

        if(x<0) x = 0;
        if(y<0) y = 0;
        if(x>boardWidth-frameWidth) x = boardWidth-frameWidth;
        if(y>boardHeight-frameHeight) y = boardHeight-frameHeight;

        if(moving) {
            frameTick++;
            if(frameTick >= animationSpeed)
            {
                animationFrame++;
                frameTick = 0;
            }
            if(animationFrame >= maxFrames) {
                animationFrame = 0;
            }
        }
        else {
            animationFrame = 0; // kai nejudam – stovim pirmam frame
        }

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
        double scale = 0.3; // tiek mazinsim zmogeliuka nes jo dydis 64 px meanwhile plyteles yra 16 px
        Graphics2D g2d = (Graphics2D) g; //graphics to 2d graphics (daugiau galimybiu)
        character = spreadsheet.getSubimage(animationFrame * frameWidth, directionIndex * frameHeight, frameWidth, frameHeight);

        int drawWidth = (int)(frameWidth * scale);   // 64 * 0.25 = 16, mazinam zmogeliuka cia
        int drawHeight = (int)(frameHeight * scale); // 64 * 0.25 = 16

        g2d.drawImage(character, x, y, drawWidth, drawHeight, null);
    }

    Player(int x, int y, int boardWidth, int boardHeight) {
        this.x = x;
        this.y = y;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.speed = 2;

        try {
            spreadsheet = ImageIO.read(new File("src/shura.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        character = spreadsheet.getSubimage(0, 0, frameWidth, frameHeight);
    }
}