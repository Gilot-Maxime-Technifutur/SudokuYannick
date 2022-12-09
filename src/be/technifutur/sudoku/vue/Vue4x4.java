package be.technifutur.sudoku.vue;

import be.technifutur.sudoku.modele.SudokuModel4x4;
import be.technifutur.sudoku.vue.AbstractVue;
import be.technifutur.sudoku.vue.SudokuVue;

public class Vue4x4 extends AbstractVue implements SudokuVue {
    private static String format = """
                +-----+-----+
                | . . | . . |
                | . . | . . |
                +-----+-----+
                | . . | . . |
                | . . | . . |
                +-----+-----+
                """.replaceAll("\\.","%s");

    public Vue4x4(SudokuModel4x4 model) {
        super(format,model);
    }

     public void afficherGrilleVide() {
        afficherGrille(new SudokuModel4x4());
    }

    }
