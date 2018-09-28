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
    JPanel panel;
    int playerNumber = 1;
    boolean twoPlayerMode = false;

    GUI(int fieldSizeX, int fieldSizeY, int numberOfMines, boolean mode) {
        this.frame = new JFrame("Minesweeper");
        this.panel = new JPanel(new GridBagLayout());
        this.GameField = new JTable(fieldSizeX, fieldSizeY);
        this.msf = new MinesweeperField(fieldSizeX, fieldSizeY, numberOfMines);
        this.twoPlayerMode = mode;
        this.playersTurnTextArea = new JTextArea();
        this.playersTurnTextArea.setText("Current player:\nPlayer 1");
    }

    public void start() {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.updateField(msf.getStateArray());
        this.panel.add(this.GameField);
        this.panel.add(this.playersTurnTextArea);
        this.frame.add(this.panel);
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
                    case MINE:
                        valueToShow = " ";
                        break;
                    case EMPTY_CLICKED:
                        valueToShow = "" + (msf.getMineProximityNumbers(i, j) == 0 ? "||||||" : msf.getMineProximityNumbers(i, j));
                        break;
                    case MINE_CLICKED:
                        valueToShow = "M";
                        break;
                    case MARKED_EMPTY:
                    case MARKED_MINE:
                        valueToShow = "X";
                        break;
                    default:
                        valueToShow = "ERROR";
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
                } else if (state == GameState.LOSE) {
                    showMessage("You Lost.");
                } else if (twoPlayerMode) {
                    changePlayer();
                }
            }
        });
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
        System.exit(0);
    }

    private void changePlayer() {
        String playerString = "";
        switch (this.playerNumber) {
            case 0: playerString = "Player 1"; playerNumber = 1; break;
            case 1: playerString = "Player 2"; playerNumber = 0; break;
            default: break;
        }
        this.playersTurnTextArea.setText("Current player:\n" + playerString);
    }

}
