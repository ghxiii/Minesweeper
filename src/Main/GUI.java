package Main;

import javax.swing.*;
import java.awt.*;

public class GUI {
    int fieldSizeX;
    int fieldSizeY;

    GUI(int fieldSizeX, int fieldSizeY) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
    }

    public void start() {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim =  new Dimension(400, 400);
        frame.setMinimumSize(dim);
        JTable GameField = new JTable(fieldSizeX, fieldSizeY);
        frame.add(GameField);
        frame.setVisible(true);
    }
}
