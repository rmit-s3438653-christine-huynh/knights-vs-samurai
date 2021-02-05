package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.GameController;
import view.AppStart;

public class AppStartActionListener implements ActionListener {

	private GameController gameController;
	private AppStart appStart;

	public AppStartActionListener(GameController gameController, AppStart appStart) {
		super();
		this.gameController = gameController;
		this.appStart = appStart;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int boardWidth = appStart.getBoardDimensions();
		int boardHeight = appStart.getBoardDimensions();
		int numberOfPlayers = appStart.getNumberOfPlayers();
		int numberOfObstacles = setObstacles();
		int numberOfPieces = appStart.getNumberOfPieces();

		try {
			gameController.startGame(numberOfPlayers, boardHeight, boardWidth, numberOfObstacles, numberOfPieces);
			appStart.dispose();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public int setObstacles() {
		int diff = appStart.getDifficulty();
		int numberOfObstacles = 0;

		if (diff == 1) {
			numberOfObstacles = (appStart.getBoardDimensions() * appStart.getBoardDimensions()) / 8;
		} else if (diff == 2) {
			numberOfObstacles = (appStart.getBoardDimensions() * appStart.getBoardDimensions()) / 4;
		} else if (diff == 3) {
			numberOfObstacles = (appStart.getBoardDimensions() * appStart.getBoardDimensions()) / 3;
		}

		return numberOfObstacles;

	}

}
