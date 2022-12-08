package be.technifutur.sudoku.sudo4x4;

import be.technifutur.sudoku.*;

public class SudokuModel4x4 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel4x4() {
        super(createGrille(4));
    }

    private Cell[][] createGrille(int size){
        Cell[][] grille = new Cell[size][size];
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                grille[i][j] = new Cell();
            }
        }

        return grille;
    }

    @Override
    public boolean isValueValid(char value) {
        return value >= '1' && value <= '4';
    }

    @Override
    public boolean isPositionValid(int lig, int col) {
        return lig >= 0 &&
                lig < 4 &&
                col >= 0 &&
                col < 4;
    }

    @Override
    public int getNbvalues() {
        return 4*4;
    }
}
