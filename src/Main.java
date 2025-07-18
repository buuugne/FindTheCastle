import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception{
        int rowCount = 43;
        int colCount = 34;
        int tileSize = 16;
        int boardWidth = colCount * tileSize;
        int boardHeight = rowCount * tileSize;

        JFrame frame = new JFrame("Find the Castle");
        //frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FindTheCastle findTheCastleGame = new FindTheCastle();
        frame.add(findTheCastleGame);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}