package miniplc0java.analyser;

public class FunctionEntry {
    private int funcNo;
    private int nextLocOffset;
    private int nextParamOffset;

    public FunctionEntry(int funcNo) {
        this.funcNo = funcNo;
        this.nextLocOffset = 0;
        this.nextParamOffset = 0;
    }

    public int getFuncNumOffset() {
        return funcNo;
    }


    public int getNextLocOffset() {
        return nextLocOffset++;
    }

    public int getNextParamOffset() {
        return nextParamOffset++;
    }
}
