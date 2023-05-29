package src;

public class InvalidLineStructureException extends Exception {
    private static final long serialVersionUID = 3524484290431035388L; //default
    private static final String ERROR_MESSAGE =
            "---Bad line encountered in file, ignored---";

    public InvalidLineStructureException(String message) {
        super(message);
    }

    public void printMessage() {
        System.out.println(ERROR_MESSAGE);
    }
}
