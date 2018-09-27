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
        for (int i = 0; i < fieldSizeX; i++) {
            for (int j = 0; j < fieldSizeY; j++) {
                GameField.getModel().setValueAt(i + j, i, j);
            }
        }
        GameField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = GameField.rowAtPoint(evt.getPoint());
                int col = GameField.columnAtPoint(evt.getPoint());
            }});

        frame.add(GameField);
        frame.setVisible(true);
    }
}
