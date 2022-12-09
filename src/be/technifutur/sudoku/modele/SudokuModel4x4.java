package be.technifutur.sudoku.modele;

public class SudokuModel4x4 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel4x4() {
        super(createGrille());
    }

    private static Cell[][] createGrille(){
        Cell[][] grille = new Cell[4][4];
        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
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
