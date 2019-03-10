package Exception;

public class ExpressionException extends RuntimeException {

    private String msg;

    public ExpressionException(String msg)
    {
        super();
        this.msg = msg;
    }

    public String toString()
    {
        return this.msg;
    }

}

