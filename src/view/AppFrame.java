package view;

import javax.swing.*;

import controller.GameController;
import model.TileButton;
import model.Tiles.Coordinate;

import java.awt.*;

public class AppFrame extends JFrame {
    private BoardPanel boardPanel;
    private BottomPanel bottomPanel;

    public AppFrame(GameController gameController) {
        // sets up default frame
        super("Knights VS Samurai Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 920);
        setLayout(new BorderLayout());
        setBackground(Color.black);

        // allocates where the board will be generated

        this.boardPanel = new BoardPanel(gameController);
        add(this.boardPanel, BorderLayout.CENTER);

        // allocates the bottom panel
        this.bottomPanel = new BottomPanel(gameController, this);
        add(this.bottomPanel, BorderLayout.SOUTH);

        // makes AppFrame visible
        setVisible(true);
        setResizable(false);

    }

    public BoardPanel getBoardPanel() {
        return this.boardPanel;
    }

    public void updateTeamTurnNo(String currentTeamName, int turn) {
        this.bottomPanel.updateTeamTurnNo(currentTeamName, turn);
    }
    
    public void updateStats() {
    	this.bottomPanel.updateStats();
    }

    public TileButton getTileButtonByCoord(Coordinate coord) {
        return boardPanel.getTileButtonByCoord(coord);
    }
}
