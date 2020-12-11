package miniplc0java.analyser.o0;

import miniplc0java.instruction.Instruction;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static miniplc0java.util.StringUtils.*;

public class O0 {
    // 魔数
    public byte[] magic = {0x72, 0x30, 0x3b, 0x3e};// u32
    // 版本号，定为 1
    public byte[] version = {0, 0, 0, 1};// u32
    // 全局变量表
    public ArrayList<GlobalDef> globals;
    /// 函数列表
    public ArrayList<FunctionDef> functions;

    public O0() {
        this.globals = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.globals.add(new GlobalDef((byte) 0, "_start"));
        this.functions.add(new FunctionDef(0, 0, 0, 0));
    }


    public void writeFile(PrintStream output) throws IOException {
        output.write(magic); // magic
        output.write(version); // version
        output.write(u32Bytes(globals.size())); // globals.count
        for (GlobalDef globalDef : globals) {
            output.write(globalDef.is_const); // is_const
            output.write(u32Bytes(globalDef.value.size())); // value.count
            for (byte b : globalDef.value) { // value.items
                output.write(b);
            }
        }
        output.write(u32Bytes(this.functions.size())); // functions.count
        for (FunctionDef functionDef : this.functions) {
            output.write(u32Bytes(functionDef.name)); // name
            output.write(u32Bytes(functionDef.ret_slots)); // ret_slots
            output.write(u32Bytes(functionDef.param_slots)); // param_slots
            output.write(u32Bytes(functionDef.loc_slots)); // loc_slots
            output.write(u32Bytes(functionDef.body.size())); // body.count
            for (Instruction instruction : functionDef.body) { // body.items
                output.write(instruction.opt.getCode());
                if (instruction.length != 0)
                    output.write(instruction.getParamByte());
            }
        }
    }


    public String printString() {
        StringBuilder sb = new StringBuilder("// start\n");
        sb.append(bytesHexStr(magic));
        sb.append("// magic\n");
        sb.append(bytesHexStr(version));
        sb.append("// version\n\n");
        sb.append(bytesHexStr(u32Bytes(this.globals.size())));
        sb.append("// globals.count\n\n");
        for (GlobalDef globalDef : this.globals) {
            sb.append(intToHex(2, globalDef.is_const));
            sb.append(" // is_const\n");
            sb.append(bytesHexStr(u32Bytes(globalDef.value.size())));
            sb.append("// value.count\n");
            sb.append(bytesHexStr(globalDef.value));
            sb.append("// value.items\n\n");
        }
        sb.append(bytesHexStr(u32Bytes(this.functions.size())));
        sb.append("// functions.count\n");
        for (FunctionDef functionDef : this.functions) {
            sb.append(bytesHexStr(u32Bytes(functionDef.name)));
            sb.append("// name\n");
            sb.append(bytesHexStr(u32Bytes(functionDef.ret_slots)));
            sb.append("// ret_slots\n");
            sb.append(bytesHexStr(u32Bytes(functionDef.param_slots)));
            sb.append("// param_slots\n");
            sb.append(bytesHexStr(u32Bytes(functionDef.loc_slots)));
            sb.append("// loc_slots\n");
            sb.append(bytesHexStr(u32Bytes(functionDef.body.size())));
            sb.append("// body.count\n").append("\t// body.items\n");
            for (Instruction instruction : functionDef.body) {
                sb.append("\t");
                sb.append(instruction.opt.getCode()).append(" ");
                if (instruction.length != 0) {
                    sb.append(bytesHexStr(instruction.getParamByte()));
                }
                sb.append("// ").append(instruction.toString()).append("\n");
            }
            sb.append("\n");
        }
        sb.append("// finish");
        return sb.toString();
    }

    public void addGlobal(GlobalDef global) {
        this.globals.add(global);
    }

    public void addFunction(FunctionDef function) {
        this.functions.add(function);
    }

    public void initNewFunc(int funcNo, String funcName) {
        this.functions.add(new FunctionDef(funcNo, 0, 0, 0));
    }

    public int addInstruction(int funcNo, Instruction instruction) {
        return this.functions.get(funcNo).addInstruction(instruction);
    }

    public Instruction getInstruction(int funcNo, int no) {
        return this.functions.get(funcNo).getInstruction(no);
    }

    public int getInsNum(int funcNo) {
        return this.functions.get(funcNo).getInsNum();
    }

    public void addFuncParamNum(int funcNo) {
        this.functions.get(funcNo).addParamNum();
    }

    public void setFuncRet(int funcNo) {
        this.functions.get(funcNo).setRet_slots(1);
    }

    public void addFuncLocNum(int funcNo) {
        this.functions.get(funcNo).addLocVarNum();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        var globalList = this.globals;
        int globalSize = this.globals.size();
        for (int i = 0; i < globalSize; i++) {
            sb.append("static:");
            var valueList = globalList.get(i).getValue();
            int valueSize = valueList.size();
            for (int j = 0; j < valueSize; j++) {
                sb.append(" ").append(intToHex(2, valueList.get(j)).toUpperCase());
            }
            sb.append(" ('");
            for (int j = 0; j < valueSize; j++) {
                sb.append((char) (int) valueList.get(j));
            }
            sb.append("')\n");
        }
        var funcList = this.functions;
        int funcSize = funcList.size();
        for (int i = 0; i < funcSize; i++) {
            var func = funcList.get(i);
            sb.append("fn [").append(func.getName());
            sb.append("] L:").append(func.getLoc_slots());
            sb.append(" P:").append(func.getParam_slots());
            sb.append(" -> ").append(func.getReturn_slots()).append(" {\n");
            var insList = func.getBody();
            int insSize = insList.size();
            for (int j = 0; j < insSize; j++) {
                sb.append("\t").append(String.format("%2s", j));
                sb.append(": ").append(insList.get(j)).append("\n");
            }
            sb.append("}\n");
        }
        return sb.toString();
    }

}
