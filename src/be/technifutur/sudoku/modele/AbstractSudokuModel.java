package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.exception.SudokuDoublonException;
import be.technifutur.sudoku.exception.SudokuException;
import be.technifutur.sudoku.exception.SudokuPositionException;
import be.technifutur.sudoku.exception.SudokuValueException;

import java.math.BigInteger;
import java.util.*;

public abstract class AbstractSudokuModel implements SudokuModel {

    private Stack<BigInteger> historique = new Stack<>();
    private Cell[][] grille;
    public AbstractSudokuModel(Cell[][] grille){
        this.grille = grille;
//        grille = new Cell[size][size];
//        for (int i=0;i<size;i++){
//            for (int j=0;j<size;j++){
//                this.grille[i][j] = new Cell();
//            }
//        }
    }

    @Override
    public char getValue(int lig, int col) throws SudokuPositionException {
        testPosition(lig, col);
        return this.grille[lig][col].getValue();
    }
    private void testPosition(int lig, int col) throws SudokuPositionException {
        if (!isPositionValid(lig, col)) {
            throw new SudokuPositionException(String.format("la position %s, %s n'est pas valide", lig, col));
        }
    }

    public abstract boolean isPositionValid(int lig, int col);

    @Override
    public void setValue(int lig, int col, char value) throws SudokuPositionException, SudokuValueException, SudokuDoublonException {
        testPosition(lig, col);
        testValue(value);
        this.grille[lig][col].setValue(value);
    }

    private void testValue(char value) throws SudokuValueException {
        if (!isValueValid(value)) {
            throw new SudokuValueException(String.format("la valeur %s n'est pas valide", value));
        }
    }

    public int getMaxSize() {
        return this.grille.length;
    }

    @Override
    public void deleteValue(int lig, int col) throws SudokuPositionException {
        testPosition(lig, col);
        grille[lig][col].clear();
    }

    @Override
    public abstract boolean isValueValid(char value);

    public void lock(){
        for(Cell[] l : grille){
            for(Cell cell : l){
                if(cell != null && !cell.isEmpty())
                    cell.lock();
            }
        }
    }

    public Object getMemento(){
        return Arrays.stream(grille)
                .flatMap(l -> Arrays.stream(l))
                .filter(c -> c != null && !c.isLock())
                .map(c -> c.isEmpty()? new BigInteger("0") : new BigInteger(String.valueOf(c.getValue())))
                .reduce((anc, nouv) -> anc.shiftLeft(4).add(nouv));
    }
    
    public void setMemento(BigInteger o) throws SudokuException{
        BigInteger mask = new BigInteger("0" + 0b1111);
        int start = grille.length - 1;
        
        for(int i = start; i >= 0; i--){
            for(int j = start; j >= 0; j--){
                if(grille[i][j] != null && !grille[i][j].isLock()) {
                    char loadValue = o.and(mask).toString().charAt(0);
                    grille[i][j].setValue(loadValue);
                    o.shiftRight(4);
                }
            }
        }
    }

    public static void main(String[] args) throws SudokuException {
        SudokuModel4x4 model = new SudokuModel4x4();
        model.setValue(0, 0, '1');
        model.setValue(1, 1, '2');
        model.lock();
        model.setValue(2, 2, '3');
        model.setValue(3, 3, '4');



    }
}