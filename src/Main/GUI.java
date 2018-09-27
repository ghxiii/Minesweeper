package Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUI {
    int fieldSizeX;
    int fieldSizeY;
    JFrame frame;
    JTable GameField;

    GUI(int fieldSizeX, int fieldSizeY) {
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.frame = new JFrame("Minesweeper");
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim =  new Dimension(400, 400);
        this.frame.setMinimumSize(dim);
        this.updateField();

        this.frame.add(this.GameField);
        this.frame.setVisible(true);
    }

    public void updateField() {
        for (int i = 0; i < this.fieldSizeX; i++) {
            for (int j = 0; j < this.fieldSizeY; j++) {
                this.GameField.getModel().setValueAt(i + j, i, j);
            }
        }
    }

    public HashMap<String, Integer> getClickedField() {
        HashMap<String, Integer> CoordinatesMap = new HashMap<>();
        this.GameField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CoordinatesMap.put("row", GameField.rowAtPoint(evt.getPoint()));
                CoordinatesMap.put("column", GameField.columnAtPoint(evt.getPoint()));
            }});

        return CoordinatesMap;
    }
}
