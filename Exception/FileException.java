package Exception;

public class FileException extends RuntimeException {

    private String msg;

    public FileException(String msg)
    {
        super();
        this.msg = msg;
    }

    public String toString()
    {
        return this.msg;
    }
}
