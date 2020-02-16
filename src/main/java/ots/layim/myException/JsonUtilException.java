package ots.layim.myException;

public class JsonUtilException extends RuntimeException {

    public JsonUtilException() {
    }

    public JsonUtilException(String message) {
        super(message);
    }

    public JsonUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonUtilException(Throwable cause) {
        super(cause);
    }

    public JsonUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
