package minplc0java.error;

public abstract class CompileError extends Exception {

    private static final long serialVersionUID = 1L;

    public abstract ErrorCode getErr();
}
