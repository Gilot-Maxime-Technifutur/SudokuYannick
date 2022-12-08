package be.technifutur.sudoku;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {

    public static void main(String[] args) {
        Cell cell = new Cell();

        Set<Character> zonel = new HashSet<>();
        Set<Character> zonec = new HashSet<>();

        cell.addZone("ligne", zonel);
        cell.addZone("colonne", zonec);

        System.out.println(zonel == cell.getZone("ligne"));
        System.out.println(zonec == cell.getZone("colonne"));
    }
    private char value = SudokuModel.EMPTY;

    private boolean lock;

    private Map<String,Set<Character>> zones = new HashMap<>();

    public void clear() {
        if (value != SudokuModel.EMPTY) {
            value = SudokuModel.EMPTY;
        }
    }

    public boolean isEmpty() {
        return value == SudokuModel.EMPTY;
    }

    public boolean setValue(char value) {
        boolean modif = false;
        if (!isLock() && this.value != value) {
            this.value = value;
            modif = true;
        }
        return modif;
    }

    public char getValue() {
        return value;
    }

    public void lock() {
        if (!isEmpty()){
            this.lock = true;
        }
    }

    public boolean isLock() {
        return lock;
    }

    public void addZone(String name,Set<Character> zone){
        zones.put(name,zone);
    }

    public Set<Character> getZone(String name){
        return zones.get(name);
    }

}
