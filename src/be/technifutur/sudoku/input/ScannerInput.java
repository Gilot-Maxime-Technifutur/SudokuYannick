package be.technifutur.sudoku.input;

import be.technifutur.sudoku.input.Input;

import java.util.Scanner;

public class ScannerInput implements Input {

    private Scanner scan = new Scanner(System.in);
    @Override
    public String read(String message) {
        System.out.print(message);
        return scan.nextLine();
    }
}
