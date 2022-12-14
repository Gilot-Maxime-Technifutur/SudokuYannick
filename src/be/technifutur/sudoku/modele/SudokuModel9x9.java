package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.modele.AbstractSudokuModel;
import be.technifutur.sudoku.modele.SudokuModel;

import java.util.HashSet;
import java.util.Set;

public class SudokuModel9x9 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel9x9() {
        super(createGrille());
    }

    private static Cell[][] createGrille(){
        Cell[][] grille = new Cell[9][9];
        Set<Character>[] lignes = new Set[9];
        Set<Character>[] colonnes = new Set[9];
        Set<Character>[] carres = new Set[9];

        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                grille[i][j] = new Cell();
                grille[i][j].addZone("ligne", lignes[i]);
                grille[i][j].addZone("colonne", colonnes[j]);
                grille[i][j].addZone("carre", carres[(i/3)*3+j/3]);
            }
        }

        return grille;
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
