package be.technifutur.sudoku.modele;

import be.technifutur.sudoku.exception.SudokuDoublonException;
import be.technifutur.sudoku.exception.SudokuValueException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cell {
    /*/
    public static void main(String[] args) {
        Cell cell = new Cell();

        Set<Character> zonel = new HashSet<>();
        Set<Character> zonec = new HashSet<>();

        cell.addZone("ligne", zonel);
        cell.addZone("colonne", zonec);

        System.out.println(zonel == cell.getZone("ligne"));
        System.out.println(zonec == cell.getZone("colonne"));
    }
    //*/

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

    public boolean setValue(char newValue) throws SudokuDoublonException {
        boolean modif = false;

        if(!isLock()) {
            if (newValue != value && newValue != SudokuModel.EMPTY) {
                testInZone(newValue);

                if (!isEmpty()) {
                    removeFromZones(value);
                }
                addToZones(newValue);

                value = newValue;
            }
        }

        return modif;
    }

    public char getValue() {
        return value;
    }

    public void lock() {
        if(!isEmpty()){
            lock = true;
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

    public void removeFromZones(char value){
        for(Set<Character> zone : zones.values()){
            //if(zone != null)
                zone.remove(value);
        }
    }

    public void addToZones(char value){
        for(Set<Character> zone : zones.values()){
            //if(zone != null)
                zone.add(value);
        }
    }

    private void testInZone(char value) throws SudokuDoublonException {
        //System.out.println(zones);
        for(Map.Entry<String, Set<Character>> entry : zones.entrySet()){
            //if(zone != null)
                if(entry.getValue().contains(value))
                    throw new SudokuDoublonException(String.format("la valeur %s est un doublon sur %s", value, entry.getKey()));
        }
    }

    @Override
    public String toString(){
        return String.format("%s isLock %s", value, lock);
    }
}
