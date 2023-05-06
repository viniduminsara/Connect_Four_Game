package lk.ijse.gdse.service;

import javafx.scene.control.Alert;
import lk.ijse.gdse.util.DEPAlert;

public class HumanPlayer extends Player{

    public HumanPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        //check the move is legal
        if(board.isLegalMove(col)){
            board.updateMove(col,board.findNextAvailableSpot(col),Piece.BLUE);
            board.getBoardUI().update(col,true);

            //check there is a winner
            if (board.findWinner().getWinningPiece()==Piece.EMPTY){
                if (!board.existLegalMoves()){
                    board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
                }
            }else{
                board.getBoardUI().notifyWinner(board.findWinner());
            }
        }else{
            new DEPAlert(Alert.AlertType.WARNING,"WARNING","Illegal Move","Please select correct row.").show();
        }
    }
}
