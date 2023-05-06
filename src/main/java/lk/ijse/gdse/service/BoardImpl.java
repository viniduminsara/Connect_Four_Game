package lk.ijse.gdse.service;

public class BoardImpl implements Board{

    private final Piece[][] pieces;
    private final BoardUI boardUI;

    public BoardImpl(BoardUI boardUI){
        this.boardUI = boardUI;
        pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];

        //initialize the 2d array
        for (int i = 0; i<pieces.length ; i++){
            for (int j = 0; j<pieces[i].length ; j++){
                pieces[i][j]=Piece.EMPTY;
            }
        }
    }

    //get memory location of boardUI
    @Override
    public BoardUI getBoardUI() {
        return boardUI;
    }

    //check the last empty row
    @Override
    public int findNextAvailableSpot(int col) {
        for (int i = 0; i<pieces[col].length; i++){
            if (pieces[col][i]==Piece.EMPTY){
                return i;
            }
        }
        return -1;
    }

    //return if there is a empty row or not in a column
    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col)==-1){
            return false;
        }
        return true;
    }

    //return if there is a empty row or not in a whole board
    @Override
    public boolean existLegalMoves() {
        for (int i = 0; i<pieces.length ; i++){
            if (findNextAvailableSpot(i)!=-1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {
        Piece aiPlayer = Piece.GREEN;
        Piece humanPlayer = Piece.BLUE;
        int col1;
        int row1;
        int col2;
        int row2;

        //Vertical check
        //Ai player
        for (int i = 0; i<pieces.length; i++){
            for (int j = 3; j<=4 ; j++){
                if (pieces[i][j]==aiPlayer & pieces[i][j-1]==aiPlayer & pieces[i][j-2]==aiPlayer & pieces[i][j-3]==aiPlayer){
                    col1=i;
                    row1=(j-3);
                    col2=i;
                    row2=j;
                    return new Winner(aiPlayer,col1,row1,col2,row2);
                }
            }
        }
        //human player
        for (int i = 0; i<pieces.length; i++){
            for (int j = 3; j<=4 ; j++){
                if (pieces[i][j]==humanPlayer & pieces[i][j-1]==humanPlayer & pieces[i][j-2]==humanPlayer & pieces[i][j-3]==humanPlayer){
                    col1=i;
                    row1=(j-3);
                    col2=i;
                    row2=j;
                    return new Winner(humanPlayer,col1,row1,col2,row2);
                }
            }
        }

        //Horizontal check
        //Ai Player
        for (int i = 0; i<NUM_OF_ROWS ; i++){
            for (int j = 3; j<=5 ; j++){
                if(pieces[j][i]==aiPlayer & pieces[j-1][i]==aiPlayer & pieces[j-2][i]==aiPlayer & pieces[j-3][i]==aiPlayer){
                    col1=(j-3);
                    row1=i;
                    col2=j;
                    row2=i;
                    return new Winner(aiPlayer,col1,row1,col2,row2);
                }
            }
        }

        //Human Player
        for (int i = 0; i<NUM_OF_ROWS ; i++){
            for (int j = 3; j<=5 ; j++){
                if(pieces[j][i]==humanPlayer & pieces[j-1][i]==humanPlayer & pieces[j-2][i]==humanPlayer & pieces[j-3][i]==humanPlayer){
                    col1=(j-3);
                    row1=i;
                    col2=j;
                    row2=i;
                    return new Winner(humanPlayer,col1,row1,col2,row2);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }
}
