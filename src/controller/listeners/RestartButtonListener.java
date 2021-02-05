package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameController;
import view.AppFrame;
import view.AppStart;

public class RestartButtonListener implements ActionListener {
	private GameController gameController;
	private AppFrame appFrame;
	private AppStart appStart;

	public RestartButtonListener(GameController gameController, AppFrame appFrame) {
		super();
		this.gameController = gameController;
		this.appFrame = appFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		appFrame.dispose();
		appStart = new AppStart(gameController);
	}

}
