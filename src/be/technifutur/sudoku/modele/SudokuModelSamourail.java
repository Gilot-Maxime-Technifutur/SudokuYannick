package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.modele.AbstractSudokuModel;
import be.technifutur.sudoku.modele.SudokuModel;

import java.util.Set;

public class SudokuModelSamourail extends AbstractSudokuModel implements SudokuModel {

    public SudokuModelSamourail() {
        super(createGrille());
    }

    private static Cell[][] createGrille(){
        Cell[][] grille = new Cell[21][21];
        Set<Character>[] lignes = new Set[45];
        Set<Character>[] colonnes = new Set[45];
        Set<Character>[] carres = new Set[41];

        for (int i=0;i<21;i++){
            for (int j=0;j<21;j++){
                if(isPValid(i, j)) {
                    int sudo = 0;
                    int carre = (i/3)*3+j/3;
                    grille[i][j] = new Cell();
                    grille[i][j].addZone("ligne", lignes[i * sudo]);
                    grille[i][j].addZone("colonne", colonnes[j * sudo]);
                    grille[i][j].addZone("carre", carres[(0) * sudo]);
                }
            }
        }

        return grille;
    }

    @Override
    public boolean isPositionValid(int lig, int col) {
        return isPValid(lig, col);
    }

    @Override
    public boolean isValueValid(char value) {
        return value >= '1' && value <= '9';
    }

    public static boolean isPValid(int lig, int col) {
        return isPositionInSudoku(lig, col, 0, 0) ||
                isPositionInSudoku(lig, col, 0, 9 + 3) ||
                isPositionInSudoku(lig, col, 6, 6) ||
                isPositionInSudoku(lig, col, 9 + 3, 0) ||
                isPositionInSudoku(lig, col, 9 + 3, 9 + 3);
    }

    @Override
    public int getNbvalues() {
        return 21*21 - 4* 3 * 6;
    }

    private static boolean isPositionInSudoku(int lig, int col, int lig0, int col0) {
        return lig >= lig0 &&
                lig < 9 + lig0 &&
                col >= col0 &&
                col < 9 + col0;
    }
}
