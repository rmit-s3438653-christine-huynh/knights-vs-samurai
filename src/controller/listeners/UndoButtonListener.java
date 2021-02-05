package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameController;

public class UndoButtonListener implements ActionListener {
    private GameController gameController;

    public UndoButtonListener(GameController gameController) {
        super();
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameController.undoOptionPane();
    }
}
