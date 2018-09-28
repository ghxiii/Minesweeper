package Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean twoPlayer = askForTwoPlayer();
        GUI gui = new GUI(20, 20, 20, twoPlayer);
        gui.start();
    }

    public static boolean askForTwoPlayer() {
        int mode = JOptionPane.showConfirmDialog(null,
                "Play in 2-Player mode?",
                "Modeselection",
                JOptionPane.YES_NO_OPTION);
        return (mode == 0)? true : false;
    }
}
