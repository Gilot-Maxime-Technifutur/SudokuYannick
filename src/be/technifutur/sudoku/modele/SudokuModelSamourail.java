package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.modele.AbstractSudokuModel;
import be.technifutur.sudoku.modele.SudokuModel;

import java.util.HashSet;
import java.util.Set;

public class SudokuModelSamourail extends AbstractSudokuModel implements SudokuModel {

    public SudokuModelSamourail() {
        super(createGrille());
    }

    private static Cell[][] createGrille() {
        Cell[][] grille = new Cell[21][21];
        Set<Character>[] lignes = initSet(9 * 5);
        Set<Character>[] colonnes = initSet(9 * 5);
        Set<Character>[] carres = initSet(9 * 5);
        int[][] sTab = {{0, 0}, {0, 12}, {6, 6}, {12, 0}, {12, 12}};
        int[][] eTab = {{}};

        for (int k = 0; k < 5; k++){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (isPValid(i, j)) {
                        int lig = i + sTab[k][0];
                        int col = j + sTab[k][1];

                        if(grille[lig][col] == null){
                            grille[lig][col] = new Cell();
                            grille[lig][col].addZone("ligne", lignes[i + k * 9]);
                            grille[lig][col].addZone("colonne", colonnes[j + k * 9]);
                            grille[lig][col].addZone("carre", carres[((i / 3 ) * 3 + j / 3) + k * 9]);
                        }else{
                            grille[lig][col].addZone("ligneBis", lignes[i + k * 9]);
                            grille[lig][col].addZone("colonneBis", colonnes[j + k * 9]);
                        }
                    }
                }
            }
        }

        return grille;
    }

    private static Set<Character>[] initSet(int taille){
        Set<Character>[] tab = new Set[taille];

        for(int i = 0; i < taille; i++){
            tab[i] = new HashSet<>();
        }

        return tab;
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
