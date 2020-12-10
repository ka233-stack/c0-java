package c0.error;

import c0.util.Pos;

public class AnalyzeError extends CompileError {

    private static final long serialVersionUID = 1L;
    ErrorCode code;
    Pos pos;

    @Override
    public ErrorCode getErr() {
        return code;
    }

    public Pos getPos() {
        return pos;
    }

    /**
     * @param code ErrorCode
     * @param pos  Pos
     */
    public AnalyzeError(ErrorCode code, Pos pos) {
        this.code = code;
        this.pos = pos;
    }

    @Override
    public String toString() {
        return "Analyze Error: " + code + ", at: " + pos;
    }
}
