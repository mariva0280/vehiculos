package Exception;

public class EinvalidPropertyException extends Exception{
    String message;

    public EinvalidPropertyException(String message) {
        super(message);
    }
}
