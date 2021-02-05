package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private KnightPanel knightPanel;
    private SamuraiPanel samuraiPanel;
    private StatusPanel statusPanel;

    public BottomPanel(GameController gameController, AppFrame appFrame) {
        setLayout(new BorderLayout());

        // allocates knight panel to the right most area
        this.knightPanel = new KnightPanel(gameController);
        add(this.knightPanel, BorderLayout.EAST);

        // allocates samurai panel to the left most area
        this.samuraiPanel = new SamuraiPanel(gameController);
        add(this.samuraiPanel, BorderLayout.WEST);

        // allocates status panel to the center area
        this.statusPanel = new StatusPanel(gameController, appFrame);
        add(this.statusPanel, BorderLayout.CENTER);
    }

    public void updateTeamTurnNo(String currentTeamName, int turn) {
        this.statusPanel.updateTeamTurnNo(currentTeamName, turn);
    }
    
    public void updateStats() {
    	this.knightPanel.updateStats();
    	this.samuraiPanel.updateStats();
    }
}
