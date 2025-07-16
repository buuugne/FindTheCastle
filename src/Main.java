import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception{
        int rowCount = 32;
        int colCount = 32;
        int tileSize = 32;
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