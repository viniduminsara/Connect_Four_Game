package lk.ijse.gdse.service;

import java.util.Random;

public class AiPlayer extends Player{

    public AiPlayer(Board board) {
        super(board);
    }

    //return the index of the best column found with minimax
    private int findMove(){
        boolean humanWin = false;
        int tie = 0;
        int bestColumn;
        for (int i=0; i<board.NUM_OF_COLS ; i++){
            if (board.isLegalMove(i)){
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i , row , Piece.GREEN);
                int score = minimax(0,false);
                board.updateMove(i , row , Piece.EMPTY);
                if (score == 1){
                    return i;
                }
                if(score == -1){
                    humanWin = true;
                }else{
                    tie = i;
                }
            }
        }
        if (humanWin && board.isLegalMove(tie)){
            bestColumn = tie;
        }else{
            Random random = new Random();
            do {
                bestColumn = random.nextInt(6);
            }while (!board.isLegalMove(bestColumn));
        }
        return bestColumn;
    }

    @Override
    public void movePiece(int col) {

        int bestColumn = findMove();

        board.updateMove(bestColumn,board.findNextAvailableSpot(bestColumn),Piece.GREEN);
        board.getBoardUI().update(bestColumn,false);
        if (board.findWinner().getWinningPiece()==Piece.EMPTY){
            if (!board.existLegalMoves()){
                board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
            }
        }else {
            board.getBoardUI().notifyWinner(board.findWinner());
        }
    }

    private int minimax(int depth, boolean maximizingPlayer) {
        Piece winningPiece = board.findWinner().getWinningPiece();
        if(depth >= 2 || !board.existLegalMoves()){
            return 0;
        }else if (winningPiece == Piece.GREEN){
            return 1;
        }else if (winningPiece == Piece.BLUE){
            return -1;
        }

        if (maximizingPlayer) {
            int maxEval = (int) Double.NEGATIVE_INFINITY;
            for (int i = 0; i < board.NUM_OF_COLS; i++) {
                if (board.isLegalMove(i)){
                    int row = board.findNextAvailableSpot(i);
                    board.updateMove(i, row,Piece.GREEN);
                    int heuristicVal = minimax(depth + 1, false);
                    maxEval = Math.max(heuristicVal,maxEval);
                    board.updateMove(i,row,Piece.EMPTY);

                }
            }
            return maxEval;
        } else {
            int minEval = (int) Double.POSITIVE_INFINITY;
            for (int i = 0; i < board.NUM_OF_COLS; i++) {
                if (board.isLegalMove(i)){
                    int row = board.findNextAvailableSpot(i);
                    board.updateMove(i,row,Piece.BLUE);
                    int heuristicVal = minimax(depth + 1,true);
                    minEval = Math.min(heuristicVal,minEval);
                    board.updateMove(i,row,Piece.EMPTY);
                }
            }
            return minEval;
        }
    }
}
