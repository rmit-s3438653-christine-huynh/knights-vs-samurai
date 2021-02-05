package controller.listeners;

import controller.GameController;
import model.TileButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileActionListener implements ActionListener {
    private TileButton button;
    private GameController gameController;

    public TileActionListener(GameController gameController, TileButton button) {
        super();
        this.button = button;
        this.gameController = gameController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.gameController.handleButtonPress(this.button);
    }
}
