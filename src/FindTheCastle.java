import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Random;
import javax.swing.*;
import javax.swing.Timer;

import javax.imageio.ImageIO; //tam, kad galeciau sukurti nuotrauka kaip objekta
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


public class FindTheCastle extends JPanel {
    private int rowCount = 43;
    private int colCount = 34;
    private int tileSize = 16;
    private int boardWidth = colCount * tileSize;
    private int boardHeight = rowCount * tileSize;

    private Player player;

    int [][] groundLayer;
    int [][] objectLayer;
    BufferedImage tileset;

    private void init() {
        try {
            tileset = ImageIO.read(new File("src/tilemap_packed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        groundLayer = new int[][] {
                {0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0, 1, 2, 0},
                {12, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 14},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {24, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 26},
                {36, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 38}
        };

        //Random rand = new Random(); sugeneruoja random skaiciu

        objectLayer = new int[][] {
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, -1, -1, -1, -1, -1, -1},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, -1, -1, -1, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28},
                {-1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, -1, -1, -1, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, -1, -1, -1, -1, -1, -1, -1, 102, 28, 28, 102, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, -1, 126, 28, 28, 126, 28, 28, 28, 28},
                {28, 28, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 28, 28, -1, 28, 28, -1, 28, 28, 28, 28, 28, 28, 28, 96, 126, 100, 100, 126, 98, 28, 28, 28},
                {28, 28, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 28, 28, -1, -1, -1, -1, -1, -1, -1, -1, 120, 126, 126, 126, 126, 122, 25, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 126, 126, 111, 112, 126, 126, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 126, 126, 123, 124, 126, 126, 28, 28, 28},
                {28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28}
        };

        player = new Player(0, 0, boardWidth, boardHeight, objectLayer);
    }



    //konstruktorius
    FindTheCastle() {
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.GRAY);
        init();

        this.setFocusable(true); //kad reaguotu i klavisus kazkas is JPanel klases
        this.addKeyListener(player);//player klase igyvendina KeyListener

        Timer timer = new Timer(16, new ActionListener() {// nesuprantu, bet timeris kuris padaro, kad perpiestu vaizda
            @Override
            public void actionPerformed(ActionEvent e) {
                player.update();  // atnaujina poziciją
                repaint();        // perpiešia visą ekraną
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //isvalo fona ir piesia nauja
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                int tileIndex = groundLayer[r][c];
                int tilesPerRow = tileset.getWidth() / tileSize;

                int tileX = (tileIndex % tilesPerRow) * tileSize;
                int tileY = (tileIndex / tilesPerRow) * tileSize;

                BufferedImage tile = tileset.getSubimage(tileX, tileY, tileSize, tileSize);
                g2d.drawImage(tile, c * tileSize, r * tileSize, tileSize, tileSize, null);

                if(objectLayer[r][c] >= 0){
                    int tileIndexO = objectLayer[r][c];
                    int tileXO = (tileIndexO % tilesPerRow) * tileSize;
                    int tileYO = (tileIndexO / tilesPerRow) * tileSize;

                    BufferedImage tile0 = tileset.getSubimage(tileXO, tileYO, tileSize, tileSize);
                    g2d.drawImage(tile0, c * tileSize, r * tileSize, tileSize, tileSize, null);
                }
            }
        }

        player.draw(g);
    }

}