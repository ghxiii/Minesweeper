package Main;

import MinesweeperBackend.FieldState;
import MinesweeperBackend.MinesweeperField;

import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame;
    JTable GameField;
    MinesweeperField msf;

    GUI(int fieldSizeX, int fieldSizeY) {
        this.frame = new JFrame("Minesweeper");
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
        this.msf = new MinesweeperField(fieldSizeX, fieldSizeY, 15);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(400, 360);

        this.updateField(msf.getStateArray());
        this.frame.add(this.GameField);
        this.frame.setPreferredSize(dim);
        this.frame.pack();
        this.frame.setVisible(true);
        this.setupClickHandler();
    }

    private void updateField(FieldState[][] field) {
        int xLength = field.length;
        int yLength = field[0].length;

        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLength; j++) {
                FieldState valueToShow = (field[i][j] == FieldState.MINE) ?
                        FieldState.EMPTY :
                        field[i][j];
                this.GameField.getModel().setValueAt(valueToShow, i, j);
            }
        }
    }

    public void setupClickHandler() {
        this.GameField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = GameField.rowAtPoint(evt.getPoint());
                int column = GameField.columnAtPoint(evt.getPoint());

                msf.click(row, column);
                updateField(msf.getStateArray());
            }
        });
    }
}
