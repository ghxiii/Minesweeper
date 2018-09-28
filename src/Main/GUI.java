package Main;

import MinesweeperBackend.FieldState;
import MinesweeperBackend.GameState;
import MinesweeperBackend.MinesweeperField;

import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame;
    JTable GameField;
    MinesweeperField msf;

    GUI(int fieldSizeX, int fieldSizeY, int numberOfMines) {
        this.frame = new JFrame("Minesweeper");
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
        this.msf = new MinesweeperField(fieldSizeX, fieldSizeY, numberOfMines);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = new Dimension(500, 400);

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
                String valueToShow = "";
                switch (field[i][j]) {
                    case EMPTY:
                    case MINE: valueToShow = " "; break;
                    case EMPTY_CLICKED: valueToShow = "" + (msf.getMineProximityNumbers(i,j)==0?"_":msf.getMineProximityNumbers(i,j)); break;
                    case MINE_CLICKED: valueToShow = "M"; break;
                    case MARKED_EMPTY:
                    case MARKED_MINE: valueToShow = "X"; break;
                    default: valueToShow = "ERROR";
                }
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
                GameState state = msf.getGameState();
                if (state == GameState.WIN) {
                    showMessage("You Won.");
                }
                else if (state == GameState.LOSE) {
                    showMessage("You Lost");
                }
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }
}
