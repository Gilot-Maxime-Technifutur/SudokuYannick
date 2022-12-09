package be.technifutur.sudoku.vue;

import be.technifutur.sudoku.modele.SudokuModel9x9;
import be.technifutur.sudoku.vue.AbstractVue;
import be.technifutur.sudoku.vue.SudokuVue;

public class Vue9x9 extends AbstractVue implements SudokuVue {
    private static String format = """
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                | . . . | . . . | . . .|
                +-------+-------+------+
                """.replaceAll("\\.","%s");

    public Vue9x9(SudokuModel9x9 model) {
        super(format,model);
    }
    public void afficherGrilleVide() {
        afficherGrille(new SudokuModel9x9());
    }
}
