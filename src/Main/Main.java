package Main;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int mode = JOptionPane.showConfirmDialog(null,
                "2-Player?",
                "Mode",
                JOptionPane.YES_NO_OPTION);
        boolean twoPlayer = (mode == 0)? true : false;

        GUI gui = new GUI(20, 20, 20, twoPlayer);
        gui.start();
    }
}
