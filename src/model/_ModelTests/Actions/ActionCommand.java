package model._ModelTests.Actions;

import model.Exceptions.GeneralException;
import model.Exceptions.InvalidPlayerChosenPieceException;
import model.Exceptions.InvalidPlayerPieceMovementException;

public abstract class ActionCommand {
    //Please catch more specific errors
    public abstract void execute() throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException;
    public abstract void undo() throws InvalidPlayerPieceMovementException, GeneralException, InvalidPlayerChosenPieceException;
}
