package lk.ijse.gdse.service;

public interface BoardUI {

    void update(int col,boolean isHuman);
    void notifyWinner(Winner winner);
}
