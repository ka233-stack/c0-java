package miniplc0java.error;

public class InstructionError extends CompileError {

    private static final long serialVersionUID = 1L;
    ErrorCode code;

    @Override
    public ErrorCode getErr() {
        return code;
    }

    /**
     * @param code ErrorCode
     */
    public InstructionError(ErrorCode code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Instruction Error: " + code;
    }
}
