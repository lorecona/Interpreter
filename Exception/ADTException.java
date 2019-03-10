package Exception;

public class ADTException extends RuntimeException {

    private String msg;

    public ADTException(String msg)
    {
        super();
        this.msg = msg;
    }

    public String toString()
    {
        return this.msg;
    }
}
