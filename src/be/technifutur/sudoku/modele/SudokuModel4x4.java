package be.technifutur.sudoku.modele;

import java.util.HashSet;
import java.util.Set;

public class SudokuModel4x4 extends AbstractSudokuModel implements SudokuModel {

    public SudokuModel4x4() {
        super(createGrille());
    }

    private static Cell[][] createGrille(){
        Cell[][] grille = new Cell[4][4];
        Set<Character>[] lignes = initSet(4); //new Set[4];
        Set<Character>[] colonnes = initSet(4); //new Set[4];
        Set<Character>[] carres = initSet(4); //new Set[4];

        for (int i=0;i<4;i++){
            for (int j=0;j<4;j++){
                int k = (i / 2) * 2 + j / 2;
//                lignes[i] = lignes[i] == null? new HashSet<>() : lignes[i];
//                colonnes[j] = colonnes[j] == null? new HashSet<>() : colonnes[j];
//                carres[k] = carres[k] == null? new HashSet<>() : carres[k];

                grille[i][j] = new Cell();
                //grille[i][j].addZone("ligne", ligne);
                grille[i][j].addZone("ligne", lignes[i]);
                grille[i][j].addZone("colonne", colonnes[j]);
                grille[i][j].addZone("carre", carres[k]);
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
