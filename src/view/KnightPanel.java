package view;

import javax.swing.*;

import controller.GameController;

import java.awt.*;

public class KnightPanel extends JPanel{
    private PieceStatPanel pieceStatPanel;
    private PieceStatPanel[] pieceList;

    private GameController gameController;
    
    private int numOfPieces;
	private String pieceName = null;
	private int pieceHP = 0;
	private String pieceStance = null;
    
    public KnightPanel(GameController gameController){
    	this.gameController = gameController;
    	
    	this.numOfPieces = this.gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().size();

    	pieceList = new PieceStatPanel[this.numOfPieces];
        setLayout(new GridLayout(this.numOfPieces,1));

        createKnightStats();
        
    }
    
    public void createKnightStats() {
    	for (int i = 0; i < this.numOfPieces; i++) {
        	this.pieceName = gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().get(i).getName();
        	this.pieceHP = gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().get(i).getHealthPool();
        	this.pieceStance = gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().get(i).getStance().getStanceName();
        	this.pieceStatPanel = new PieceStatPanel(this.pieceName, this.pieceHP, this.pieceStance);
        	add(pieceStatPanel);
        	pieceList[i] = pieceStatPanel;
        }	
    }
    
    public void updateStats() {
    	for (int i = 0; i < this.numOfPieces; i++) {
        	this.pieceHP = gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().get(i).getHealthPool();
        	this.pieceStance = gameController.getGameEngine().getPlayerList().get(1).getPlayerPieces().get(i).getStance().getStanceName();
        	this.pieceList[i].updateStatus(pieceHP, pieceStance);
        }
    }
}
