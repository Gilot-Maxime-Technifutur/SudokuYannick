package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.exception.SudokuDoublonException;
import be.technifutur.sudoku.exception.SudokuPositionException;
import be.technifutur.sudoku.exception.SudokuValueException;

public interface SudokuModel {
    char EMPTY = 0;

    char getValue(int lig, int col)throws SudokuPositionException;

    void setValue(int lig, int col, char value)
            throws SudokuPositionException, SudokuValueException, SudokuDoublonException;

    void deleteValue(int lig, int col)throws SudokuPositionException;

    boolean isValueValid(char value);

    boolean isPositionValid(int lig, int col);

    int getNbvalues();

    int getMaxSize();
}
