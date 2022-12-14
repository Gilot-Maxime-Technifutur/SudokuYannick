package be.technifutur.sudoku;

public class MainTest {
    public static void main(String[] args) {
        for(int i = 0; i < 21; i++){
            for(int j = 0; j < 21; j++){
                System.out.print( ((i/3)*7+j/3) + "\t" );
            }
            System.out.println();
        }
    }

}
