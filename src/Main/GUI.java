package Main;

import MinesweeperBackend.FieldState;
import MinesweeperBackend.GameState;
import MinesweeperBackend.MinesweeperField;

import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame;
    JTable GameField;
    JTextArea playersTurnTextArea;
    MinesweeperField msf;
    String player = "Player 1";
    boolean twoPlayerMode = false;

    GUI(int fieldSizeX, int fieldSizeY, int numberOfMines, boolean mode) {
        this.frame = new JFrame("Minesweeper");
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
        this.msf = new MinesweeperField(fieldSizeX, fieldSizeY, numberOfMines);
        this.twoPlayerMode = mode;
        this.playersTurnTextArea = new JTextArea();
        this.playersTurnTextArea.setText(player);
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.updateField(msf.getStateArray());
        //this.frame.setLayout();
        this.frame.add(this.GameField);
        //this.frame.add(this.playersTurnTextArea);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
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
                    case EMPTY_CLICKED: valueToShow = "" + (msf.getMineProximityNumbers(i,j)==0?"||||||":msf.getMineProximityNumbers(i,j)); break;
                    case MINE_CLICKED: valueToShow = "M"; break;
                    case MARKED_EMPTY:
                    case MARKED_MINE: valueToShow = "X"; break;
                    default: valueToShow = "ERROR";
                }
                this.GameField.getModel().setValueAt(valueToShow, i, j);
            }
        }

        if (this.twoPlayerMode) {
            changePlayer();
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
                    showMessage("You Lost.");
                }
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }
    private void changePlayer() {
            if (this.player.equals("Player 1")) {
                this.player = "Player 2";
            } else {
                this.player = "Player 1";
            }
    }

}
