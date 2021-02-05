package view;

import java.awt.GridLayout;
import javax.swing.*;

import controller.GameController;
import controller.listeners.AppStartActionListener;
import controller.listeners.RestartButtonListener;
import controller.listeners.UndoButtonListener;

public class StatusPanel extends JPanel {
    private JLabel lblTurn;
    private JLabel lblPhase;
    private JButton btnUndo;
    private JButton btnRestart;

    public StatusPanel(GameController gameController, AppFrame appFrame) {
        setLayout(new GridLayout(2, 2));

        // hard coded, will be changed at a later date
        int turn = 1;
        String phase = "NEUTRAL";

        // declares labels for phase and turn
        this.lblPhase = new JLabel("PHASE: " + phase);
        this.lblTurn = new JLabel("TURN: " + turn + ", ROUND: " + turn / 2);

        // declares start and end buttons
        this.btnRestart = new JButton("Restart Game");
        btnRestart.addActionListener(new RestartButtonListener(gameController, appFrame));
        this.btnUndo = new JButton("Undo Move");
        btnUndo.addActionListener(new UndoButtonListener(gameController));

        // adds components to the board
        add(this.lblPhase);
        add(this.lblTurn);
        add(this.btnRestart);
        add(this.btnUndo);
    }

    public void updateTeamTurnNo(String currentTeamName, int turn) {
        this.lblPhase.setText("PHASE: " + currentTeamName);
        this.lblTurn.setText("TURN: " + turn + ", ROUND: " + turn / 2);
        this.validate();
        this.repaint();
    }
}
