package enca.eli.springbootmongodb.exception;

public class TodoCollectionException extends Exception {

    private static final long serialVersionUID = 1L;

    public TodoCollectionException(String message) {
        super(message);
    }

    public static String NotFoundException(String id) {
        return "Todo with id: " + id + " not found!";
    }

    public static String TodoAlreadyExists() {
        return "Todo with the given name already exists!";
    }
}
