package exceptions;

public class MySqlSessionException extends RuntimeException{
    public MySqlSessionException() {
        super("MySQL session Exception");
    }
}
