package be.technifutur.sudoku.sudo9x9;

import be.technifutur.sudoku.AbstractSudokuModel;
import be.technifutur.sudoku.SudokuModel;
import be.technifutur.sudoku.SudokuPositionException;
import be.technifutur.sudoku.SudokuValueException;

public class SudokuModel9x9 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel9x9() {
        super(9);
    }

    @Override
    public boolean isValueValid(char value) {
        return value >= '1' && value <= '9';
    }

    @Override
    public boolean isPositionValid(int lig, int col) {
        return lig >= 0 &&
                lig < 9 &&
                col >= 0 &&
                col < 9;
    }

    @Override
    public int getNbvalues() {
        return 9*9;
    }


}
