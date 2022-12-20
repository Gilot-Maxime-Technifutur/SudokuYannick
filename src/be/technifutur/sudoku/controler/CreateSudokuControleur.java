package be.technifutur.sudoku.controler;

import be.technifutur.sudoku.exception.SudokuDoublonException;
import be.technifutur.sudoku.input.Input;
import be.technifutur.sudoku.input.ScannerInput;
import be.technifutur.sudoku.exception.SudokuException;
import be.technifutur.sudoku.exception.SudokuPositionException;
import be.technifutur.sudoku.exception.SudokuValueException;
import be.technifutur.sudoku.modele.SudokuModel;
import be.technifutur.sudoku.vue.SudokuVue;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateSudokuControleur implements SudokuControleur {
    private final SudokuModel sudoku;
    private final SudokuVue vue;
    private Stack<BigInteger> historique = new Stack<>();

    private final Input input;

    private Pattern pattern = Pattern.compile("([1-9][0-9]*)\\.([1-9][0-9]*)(\\..)?");

    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue, Input input) {
        this.sudoku = sudoku;
        this.vue = vue;
        this.input = input;
    }

    public CreateSudokuControleur(SudokuModel sudoku, SudokuVue vue) {
        this(sudoku, vue, new ScannerInput());
    }

    @Override
    public void sart() {
        vue.afficherGrille();
        String request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        while (!request.equalsIgnoreCase("q")) {
            Matcher matcher = pattern.matcher(request);
            if (matcher.matches()) {
                int lig = Integer.parseInt(matcher.group(1)) - 1;
                int col = Integer.parseInt(matcher.group(2)) - 1;
                String value = matcher.group(3);
                try {
                    if (value != null) {
                        char val = value.charAt(1);
                        sudoku.setValue(lig, col, val);
                    } else {
                        sudoku.deleteValue(lig, col);
                    }
                } catch (SudokuException e) {
                    vue.setMessage(e.getMessage());
                }

            } else {
                vue.setMessage("entrée non valide");
            }
            vue.afficherGrille();
            request = input.read("Modififier (lig.col.valeur), supprimer (lig.col), quitter (q) :");
        }
    }

    public void init(String s) {

        try {
            File file = new File(s);
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNextLine()) {
                String[] nb = scanner.nextLine().split(",");
                for (int j = 0; j < nb.length; j++){
                    char c = nb[j].charAt(0);
                    int len = nb.length;
                    try {
                        if (c != '0') {
                            if (sudoku.isPositionValid(i,j)){
                                sudoku.setValue(i, j, c);
                            }else{
                                j++;
                            }
                        }
                    } catch (SudokuException e) {
                        vue.setMessage(e.getMessage());
                    }
                }
                i++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save(Object o){
        Optional<BigInteger> memento = (Optional<BigInteger>) o;
        if(memento.isPresent()) {
            historique.push(memento);
        }
    }

    public void load(){
        BigInteger memento = historique.firstElement();
        sudoku.setMemento(memento);
    }
}
