package miniplc0java.instruction;

import miniplc0java.error.ErrorCode;
import miniplc0java.error.InstructionError;

import static miniplc0java.util.StringUtils.u32Bytes;
import static miniplc0java.util.StringUtils.u64Bytes;

public class Instruction {
    public Operation opt;
    public int length; // 参数长度 u32 = 32
    public long param;

    private Instruction(Operation opt) {
        this.opt = opt;
        this.length = 0;
        this.param = 0;
    }

    private Instruction(Operation opt, int length, long param) {
        this.opt = opt;
        this.length = length;
        this.param = param;
    }

    public static Instruction createInstruction(Operation opt) throws InstructionError {
        return switch (opt) {
            case NOP, POP, DUP, LOAD_8, LOAD_16, LOAD_32, LOAD_64, STORE_8, STORE_16, STORE_32, STORE_64, ALLOC, FREE, ADD_I, SUB_I, MUL_I, DIV_I, ADD_F, SUB_F, MUL_F, DIV_F, DIV_U, SHL, SHR, AND, OR, XOR, NOT, CMP_I, CMP_U, CMP_F, NEG_I, NEG_F, ITOF, FTOI, SHRL, SET_LT, SET_GT, RET, SCAN_I, SCAN_C, SCAN_F, PRINT_I, PRINT_C, PRINT_F, PRINT_S, PRINTLN -> new Instruction(opt);
            default -> throw new InstructionError(ErrorCode.UnsupportedInstructionType);
        };
    }

    public byte[] getParamByte() {
        byte[] bytes;
        if (this.length == 32) {
            bytes = u32Bytes((int) this.param);
        } else {
            bytes = u64Bytes(this.param);
        }
        return bytes;
    }

    public static Instruction createInstruction(Operation opt, long param) throws InstructionError {
        switch (opt) {
            case POPN:
            case LOCA:
            case ARGA:
            case GLOBA:
            case STACKALLOC:
            case CALL:
            case CALLNAME:
                if (param < 0)
                    throw new InstructionError(ErrorCode.IllegalInstruction);
                return new Instruction(opt, 32, param);
            case BR:
            case BR_FALSE:
            case BR_TURE:
                return new Instruction(opt, 32, param);
            case PUSH:
                if (param < 0)
                    throw new InstructionError(ErrorCode.IllegalInstruction);
                return new Instruction(opt, 64, param);
            default:
                throw new InstructionError(ErrorCode.UnsupportedInstructionType);
        }
    }

    public Operation getOpt() {
        return opt;
    }

    public void setParam(long param) {
        this.param = param;
    }

    public String toString() {
        if (this.length < 1)
            return this.opt.toString();
        else
            return this.opt.toString() + "(" + this.param + ")";
    }

    public String generateBinCode() {
        if (this.length < 1) {
            return String.format("%8s", Integer.toBinaryString(this.opt.getCode())).replaceAll(" ", "0");
        } else
            return String.format("%8s%" + this.length + "s", Integer.toBinaryString(this.opt.getCode()),
                    Long.toBinaryString(this.param)).replaceAll(" ", "0");
    }

}