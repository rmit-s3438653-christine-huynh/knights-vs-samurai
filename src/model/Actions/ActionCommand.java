package model.Actions;

import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerAttackPieceException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;
import model.TileButton;

import java.util.ArrayList;

public abstract class ActionCommand {
    // Please catch more specific errors
    public abstract ArrayList<TileButton> execute() throws InvalidPlayerPieceMovementException, GeneralException,
            InvalidPlayerChosenPieceException, InvalidPlayerAttackPieceException;

    public abstract ArrayList<TileButton> undo()
            throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException;
}
