package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.exception.SudokuDoublonException;
import be.technifutur.sudoku.exception.SudokuPositionException;
import be.technifutur.sudoku.exception.SudokuValueException;

public abstract class AbstractSudokuModel implements SudokuModel {

    private Cell[][] grille;
    public AbstractSudokuModel(Cell[][] grille){
        this.grille = grille;
//        grille = new Cell[size][size];
//        for (int i=0;i<size;i++){
//            for (int j=0;j<size;j++){
//                this.grille[i][j] = new Cell();
//            }
//        }
    }

    @Override
    public char getValue(int lig, int col) throws SudokuPositionException {
        testPosition(lig, col);
        return this.grille[lig][col].getValue();
    }
    private void testPosition(int lig, int col) throws SudokuPositionException {
        if (!isPositionValid(lig, col)) {
            throw new SudokuPositionException(String.format("la position %s, %s n'est pas valide", lig, col));
        }
    }

    public abstract boolean isPositionValid(int lig, int col);

    @Override
    public void setValue(int lig, int col, char value) throws SudokuPositionException, SudokuValueException, SudokuDoublonException {
        testPosition(lig, col);
        testValue(value);
            this.grille[lig][col].setValue(value);
    }

    private void testValue(char value) throws SudokuValueException {
        if (!isValueValid(value)) {
            throw new SudokuValueException(String.format("la valeur %s n'est pas valide", value));
        }
    }

    public int getMaxSize() {
        return this.grille.length;
    }

    @Override
    public void deleteValue(int lig, int col) throws SudokuPositionException {
        testPosition(lig, col);
        grille[lig][col].clear();
    }

    @Override
    public abstract boolean isValueValid(char value);
}