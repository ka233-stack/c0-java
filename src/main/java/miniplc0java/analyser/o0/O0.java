package miniplc0java.analyser.o0;

import miniplc0java.instruction.Instruction;

import java.util.ArrayList;

import static miniplc0java.util.StringUtils.intToBin;
import static miniplc0java.util.StringUtils.intToHex;

public class O0 {
    // 魔数
    public int magic = 0x72303b3e;// u32
    // 版本号，定为 1
    public int version = 0x00000001;// u32
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
        this.functions.get(funcNo).setReturn_slots(1);
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

    public String toBinCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(intToBin(32, this.magic));
        sb.append(intToBin(32, this.version));
        var globalList = this.globals;
        int globalSize = this.globals.size();
        for (int i = 0; i < globalSize; i++) {
            var globalDef = globalList.get(i);
            sb.append(intToBin(32, globalDef.getIs_const()));
            var valueList = globalList.get(i).getValue();
            int valueSize = valueList.size();
            sb.append(intToBin(32, valueSize));
            for (int j = 0; j < valueSize; j++) {
                sb.append(intToBin(2, valueList.get(j)));
            }
        }

        int funcSize = this.functions.size();
        sb.append(intToBin(32, funcSize));
        var funcList = this.functions;
        for (int i = 0; i < funcSize; i++) {
            var func = funcList.get(i);
            sb.append(intToBin(32, func.getName()));
            sb.append(intToBin(32, func.getReturn_slots()));
            sb.append(intToBin(32, func.getParam_slots()));
            sb.append(intToBin(32, func.getLoc_slots()));
            var insList = func.getBody();
            int insSize = insList.size();
            sb.append(intToBin(32, insSize));
            for (int j = 0; j < insSize; j++) {
                var instruction = insList.get(j);
                sb.append(instruction.generateBinCode());
            }
        }
        return sb.toString();
    }

    public String toHexCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(intToHex(8, this.magic));
        sb.append(intToHex(8, this.version));
        var globalList = this.globals;
        int globalSize = globalList.size();
        for (int i = 0; i < globalSize; i++) {
            var globalDef = globalList.get(i);
            sb.append(intToHex(8, globalDef.getIs_const()));
            var valueList = globalList.get(i).getValue();
            int valueSize = valueList.size();
            sb.append(intToHex(8, valueSize));
            for (int j = 0; j < valueSize; j++) {
                sb.append(intToHex(2, valueList.get(j)));
            }
        }
        int funcSize = this.functions.size();
        sb.append(intToHex(8, funcSize));
        var funcList = this.functions;
        for (int i = 0; i < funcSize; i++) {
            var func = funcList.get(i);
            sb.append(intToHex(8, func.getName()));
            sb.append(intToHex(8, func.getReturn_slots()));
            sb.append(intToHex(8, func.getParam_slots()));
            sb.append(intToHex(8, func.getLoc_slots()));
            var insList = func.getBody();
            int insSize = insList.size();
            sb.append(intToHex(8, insSize));
            for (int j = 0; j < insSize; j++) {
                var instruction = insList.get(j);
                sb.append(instruction.generateHexCode());
            }
        }
        return sb.toString();
    }

    public String toHexCodeFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(intToHex(8, this.magic));
        sb.append(" // magic\n");
        sb.append(intToHex(8, this.version));
        sb.append(" // version\n\n");
        var globalList = this.globals;
        int globalSize = this.globals.size();
        for (int i = 0; i < globalSize; i++) {
            sb.append("// globals[").append(i).append("]\n");
            var globalDef = globalList.get(i);
            sb.append(intToHex(8, globalDef.getIs_const())).append(" // globals[").append(i).append("].is_const\n");
            var valueList = globalList.get(i).getValue();
            int valueSize = valueList.size();
            sb.append(intToHex(8, valueSize)).append(" // globals[").append(i).append("].value.count\n");
            for (int j = 0; j < valueSize; j++) {
                if (j != 0)
                    sb.append(" ");
                sb.append(intToHex(2, valueList.get(j)));
            }
            sb.append(" // globals[").append(i).append("].value.items\n\n");
        }

        int funcSize = this.functions.size();
        sb.append(intToHex(8, funcSize)).append(" // functions.count\n\n");
        var funcList = this.functions;
        for (int i = 0; i < funcSize; i++) {
            sb.append("// functions[").append(i).append("]\n");
            var func = funcList.get(i);
            sb.append(intToHex(8, func.getName())).append(" // functions[").append(i).append("].name\n");
            sb.append(intToHex(8, func.getReturn_slots())).append(" // functions[").append(i).append("].ret_slots\n");
            sb.append(intToHex(8, func.getParam_slots())).append(" // functions[").append(i).append("].param_slots\n");
            sb.append(intToHex(8, func.getLoc_slots())).append(" // functions[").append(i).append("].loc_slots\n");
            var insList = func.getBody();
            int insSize = insList.size();
            sb.append(intToHex(8, insSize)).append(" // functions[").append(i).append("].body_count\n");
            sb.append("\t// functions[").append(i).append("].body.items\n");
            for (int j = 0; j < insSize; j++) {
                var instruction = insList.get(j);
                sb.append("\t").append(instruction.generateHexCode()).append(" // ").append(instruction).append("\n");
            }
            sb.append("\n");
        }
        sb.append("// finish");
        return sb.toString();
    }
}
