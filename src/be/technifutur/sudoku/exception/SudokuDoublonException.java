package be.technifutur.sudoku.exception;

public class SudokuDoublonException extends SudokuException {
    public SudokuDoublonException() {
    }

    public SudokuDoublonException(String message) {
        super(message);
    }

    public SudokuDoublonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuDoublonException(Throwable cause) {
        super(cause);
    }

    public SudokuDoublonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
