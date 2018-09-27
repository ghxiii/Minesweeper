package Main;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GUI {
    JFrame frame;
    JTable GameField;

    GUI(int fieldSizeX, int fieldSizeY) {
        this.frame = new JFrame("Minesweeper");
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(400, 400);
        this.frame.setMinimumSize(dim);

        int[][] fieldArray = new int[][]{{2, 4}, {1, 3}};
        this.updateField(fieldArray);

        HashMap<String, Integer> coordinates = getClickedField();
        System.out.println(this.GameField.getModel().getValueAt(
                coordinates.get("row"), coordinates.get("column")));

        this.frame.add(this.GameField);
        this.frame.setVisible(true);
    }

    public void updateField(int[][] field) {
        int xLength = field.length;
        int yLength = field[0].length;

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                this.GameField.getModel().setValueAt(field[i][j], i, j);
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
            }
        });

        return CoordinatesMap;
    }
}
