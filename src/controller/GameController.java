package controller;

import model.Actions.ActionCommand;
import model.Actions.AttackPieceAction;
import model.Actions.ChangePieceStanceAction;
import model.Actions.MovePieceAction;
import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.Implementations.GameEngineImpl;
import model.Interfaces.GameEngine;
import model.Pieces.Piece;
import model.Player;
import model.TileButton;
import model.Tiles.Coordinate;
import model.Tiles.Tile;
import model.Utils.PiecePlacementUtils;
import view.AppFrame;
import view.UndoOptionPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

// GameController Calls the gameEngine to do things
public class GameController {
	private GameEngine gameEngine;
	private AppFrame view;
	private TileButton firstSelectedButton = null;
	private TileButton secondSelectedButton = null;
	private ArrayList<TileButton> updateButtons;
	private Stack<ActionCommand> actions;
	private int opt = 3;
	// private PiecePlacementUtils ppu;
	// private Board gameBoard;

	public GameController() {
		this.gameEngine = new GameEngineImpl();
	}

	public void startGame(int numberOfPlayers, int boardHeight, int boardWidth, int numberOfObstacles,
			int numberOfPieces) {
		try {
			updateButtons = new ArrayList<>();
			this.gameEngine.startGame(numberOfPlayers, boardHeight, boardWidth, numberOfObstacles, numberOfPieces);
			view = new AppFrame(this);
			Player currentPlayer = this.gameEngine.getCurrentPlayer(); // Render board bottom based on this
			this.view.updateTeamTurnNo(currentPlayer.getTeamName(), this.gameEngine.getTurnNo());
			actions = new Stack<ActionCommand>();
		} catch (Exception e) {
			// Please catch specific exceptions and handle them appropriately
			System.err.println(e.getMessage());
			promptUser("Error Starting The Game, Please Restart");
			return;
		}
	}

	private void dispatchAction(ActionCommand thisCommand) throws InvalidPlayerChosenPieceException,
			InvalidPlayerPieceMovementException, InvalidPlayerAttackPieceException {
		// Execute and store commands
		try {
			ArrayList<TileButton> buttonsToUpdate = thisCommand.execute();
			updateButtons.addAll(buttonsToUpdate);
			actions.push(thisCommand);
			clearSelectedTiles();
			updateBoardView();
		} catch (GeneralException e) {
			System.err.println(e.getMessage());
		}
	}

	public void undoTurns(int noOfTurns) {
		Player currentPlayer = this.gameEngine.getCurrentPlayer();
		if (currentPlayer.canUndo()) {

			int noOfUndo = noOfTurns * this.gameEngine.getPlayerList().size();
			for (int i = 0; i < noOfUndo; i++) {
				undoAction();
			}
			currentPlayer.didUndo();
		} else {
			promptUser("You have used your undo for the game");
		}
	}

	// TODO change this to private after testing
	public boolean undoAction() {
		boolean success = false;
		ActionCommand thisAction, nextAction;
		if (actions.size() > 0) {
			thisAction = actions.pop();
			try {
				ArrayList<TileButton> undoButtonsToUpdate = thisAction.undo();
				// Undo all stance changes
				if (actions.size() > 0) {
					nextAction = actions.peek();
					if (nextAction instanceof ChangePieceStanceAction) {
						System.out.println(actions);
						System.out.println("Removing change stance action");
						undoAction();
					}
				}
				updateButtons.addAll(undoButtonsToUpdate);
				if (!(thisAction instanceof ChangePieceStanceAction)) {
					this.gameEngine.changePlayerPreviousTurn();
				}
				clearSelectedTiles();
				updateBoardView();
				success = true;
			} catch (InvalidPlayerPieceMovementException | GeneralException | InvalidPlayerChosenPieceException e) {
				System.err.println("Something went wrong while undoing action");
				System.err.println(e.getMessage());
			}
		} else {
			success = false;
		}
		return success;
	}

	public int getActionStackSize() {
		return actions.size();
	}

	private boolean movePlayerPiece(Player player, TileButton originButton, TileButton destinationButton)
			throws InvalidPlayerChosenPieceException, InvalidPlayerAttackPieceException {
		try {
			dispatchAction(new MovePieceAction(this.gameEngine, player, originButton, destinationButton));
			return true;
		} catch (InvalidPlayerPieceMovementException e) {
			System.err.println("Invalid piece move: " + e.getMessage());
			promptUser("Invalid Move");
			clearSelectedTiles();
			return false;
		}
	}

	private boolean attackOpponentPiece(Player player, TileButton playerTileButton, TileButton opponentTileButton) {
		if (player == null || playerTileButton == null || opponentTileButton == null) {
			return false;
		}
		try {
			dispatchAction(new AttackPieceAction(this, gameEngine, view, player, playerTileButton, opponentTileButton));
			return true;
		} catch (InvalidPlayerChosenPieceException | InvalidPlayerPieceMovementException
				| InvalidPlayerAttackPieceException e) {
			System.err.println("Invalid piece attack: " + e.getMessage());
			promptUser("Invalid Attack: " + e.getMessage());
			clearSelectedTiles();
			return false;
		}
	}

	public TileButton respawnOpponentPiece(Tile opponentTile) {
		Coordinate coord;
		opponentTile.getPiece().setNewHealthPool();
		coord = PiecePlacementUtils.spawnPieceRandomlyOnBoard(opponentTile.getPiece(), this.gameEngine.getGameBoard());
		TileButton respawnButton = (view.getTileButtonByCoord(coord));
		updateButtons.add(respawnButton);
		return respawnButton;
	}

	private void promptUser(String prompt) {
		JOptionPane.showMessageDialog(view, prompt);
	}

	public void handleButtonPress(TileButton button) {
		// Check if tile had piece
		// If no piece, return
		Player currentPlayer = this.gameEngine.getCurrentPlayer(); // Render board bottom based on this
		Object[] options = { "Move", "Attack", "Change Stance", "Cancel" };
		// If has piece
		// Check if belongs to current player
		// If not belongs, return, display pop up 'Invalid tile selected'
		// if yes, select it
		// Handle a tile selected
		if (this.firstSelectedButton == null) {
			if (!button.getTile().hasPiece()) {
				return;
			} else {
				if (!currentPlayer.ownPiece(button.getTile().getPiece())) {
					System.out.println("Piece does not belong to player");
					promptUser("Wrong team's piece selected ");
					return;
				}
			}
			int n = JOptionPane.showOptionDialog(view,
					button.getTile().getPiece().getName() + " HP = " + button.getTile().getPiece().getHealthPool()
							+ "\n" + "Current Stance: " + button.getTile().getPiece().getStance().getStanceName()
							+ "\n" + "Attack Value: " + button.getTile().getPiece().getDamageValue(),
					"Please Select an Option", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
					button.getTile().getPiece().getImageIcon(), options, options[3]);

			if (n == 0) {
				System.out.println("Move Piece option has been chosen");
				this.firstSelectedButton = button;
				Tile firstTile = this.firstSelectedButton.getTile();
				firstTile.select();
				firstTile.getCoordinate().print();
				opt = 0;
			} else if (n == 1) {
				System.out.println("Attack a piece option has been chosen");
				this.firstSelectedButton = button;
				Tile firstTile = this.firstSelectedButton.getTile();
				firstTile.select();
				firstTile.getCoordinate().print();
				opt = 1;
			} else if (n == 2) {
				System.out.println("Change Stance option has been chosen");
				try {
					this.firstSelectedButton = button;
					dispatchAction(new ChangePieceStanceAction(this.firstSelectedButton));
				} catch (Exception e) {
					System.err.println("Somthing went wrong changing stance");
					System.err.println(e.getMessage());
				}
			} else {
				System.out.println("Cancel move option has been chosen");
				return;
			}

		} else if (this.firstSelectedButton == button) {
			promptUser("Selected same tile twice, please select again");
		} else if (this.secondSelectedButton == null) {
			this.secondSelectedButton = button;
			Tile secondTile = this.secondSelectedButton.getTile();
			secondTile.select();
			secondTile.getCoordinate().print();
			// Make move here
			if (opt == 0) {
				try {
					if (movePlayerPiece(currentPlayer, this.firstSelectedButton, this.secondSelectedButton)) {
						// Move to next player turn
						this.gameEngine.changePlayerTurn();
					}
				} catch (InvalidPlayerChosenPieceException | InvalidPlayerAttackPieceException e) {
					System.err.println("Something went wrong when trying to move a piece");
					System.err.println(e.getMessage());
				}
			} else if (opt == 1) {
				// attack
				if (attackOpponentPiece(currentPlayer, this.firstSelectedButton, this.secondSelectedButton)) {
					// Check if opponent piece hp = 0; call
					// PiecePlacementUtils.spawnPieceRandomlyOnBoard()
					// removeOpponentPiece(this.secondSelectedButton);
					clearSelectedTiles();
					this.gameEngine.changePlayerTurn();
				}
			}
		} else {
			clearSelectedTiles();
		}
		updateStats();
		updateButtons.add(button);
		updateBoardView();
		// Check win condition (state based)
		try {
			if (gameEngine.checkWinCondition()) {
				Player winner = gameEngine.getWinner();
				String winnerName = winner.getTeamName();
				promptUser(winnerName + " have won!");
			}
		} catch (GeneralException e) {
			System.err.println("Error occurred while checking win condition");
			System.err.println(e.getMessage());
		}
	}

	private void clearSelectedTiles() {
		if (this.firstSelectedButton != null) {
			Tile firstTile = this.firstSelectedButton.getTile();
			firstTile.unselect();
			updateButtons.add(this.firstSelectedButton);
			this.firstSelectedButton = null;
		}
		if (this.secondSelectedButton != null) {
			Tile secondTile = this.secondSelectedButton.getTile();
			secondTile.unselect();
			updateButtons.add(this.secondSelectedButton);
			this.secondSelectedButton = null;
		}
		updateBoardView();
	}

	public void undoOptionPane() {
		UndoOptionPane undoOptionPane = new UndoOptionPane(view, this);
		int turnCount = getGameEngine().getTurnNo();

		if (turnCount >= 2) {
			int userAction = JOptionPane.showConfirmDialog(view, undoOptionPane, "Undo Turn(s)",
					JOptionPane.OK_CANCEL_OPTION);

			if (userAction == JOptionPane.OK_OPTION) {
				System.out.println(undoOptionPane.getNumberOfUndos());
				undoTurns(undoOptionPane.getNumberOfUndos());
			}
		} else {
			System.out.println("ERROR");
			JOptionPane.showMessageDialog(view, "Cannot undo on the first turn");
		}
	}

	public int calculateDifficulty(int diff, int boardDimensions) {
		int numberOfObstacles = 0;

		if (diff == 1) {
			numberOfObstacles = (boardDimensions * boardDimensions) / 8;
		} else if (diff == 2) {
			numberOfObstacles = (boardDimensions * boardDimensions) / 4;
		} else if (diff == 3) {
			numberOfObstacles = (boardDimensions * boardDimensions) / 3;
		}

		return numberOfObstacles;

	}

	public void toggleStance(Piece piece) {

	}

	public void updateBoardView() {
		Player currentPlayer = this.gameEngine.getCurrentPlayer(); // Render board bottom based on this
		updateButtons.forEach(button -> button.render());
		this.view.updateTeamTurnNo(currentPlayer.getTeamName(), this.gameEngine.getTurnNo());
		updateButtons.clear();
	}

	public void updateStats() {
		view.updateStats();
	}

	// GETTERS
	public GameEngine getGameEngine() {
		return gameEngine;
	}
}
