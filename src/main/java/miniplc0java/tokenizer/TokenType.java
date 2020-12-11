package miniplc0java.tokenizer;

public enum TokenType {

    // 标识符
    IDENT,

    // 字面量
    UINT_LITERAL, // 无符号整型字面值
    DOUBLE_LITERAL, // 浮点数字面值
    STRING_LITERAL, // 字符串，'"' (string_regular_char | escape_sequence)* '"'
    CHAR_LITERAL, // 字符 '\'' (char_regular_char | escape_sequence) '\''

    // 类型
    TY, // 类型'ty'

    // 关键字
    FN, // 'fn'
    LET, // 'let'
    CONST, // 'const'
    AS, // 'as'
    WHILE, // 'while'
    IF, // 'if'
    ELSE, // 'else'
    RETURN, // 'return'
    BREAK, // 'break'
    CONTINUE, // 'continue'

    // 符号
    PLUS, // '+'
    MINUS, // '-'
    MUL, // '*'
    DIV, // '/'

    ASSIGN, // '='

    EQ, // '=='
    NEQ, // '!='
    LT, // '<'
    GT, // '>'
    LE, // '<='
    GE, // '>='

    L_PAREN, // '('
    R_PAREN, // ')'
    L_BRACE, // '{'
    R_BRACE, // '}'
    ARROW, // '->'
    COMMA, // ','
    COLON, // ':'
    SEMICOLON, // ';'

    // 注释
    COMMENT, // '//' regex(.*) '\n'

    // 其他
    None, // 空
    SHARP, // # 仅用于OPG
    EOF; // 文件尾

    @Override
    public String toString() {
        return switch (this) {
            case IDENT -> "标识符";
            case UINT_LITERAL -> "无符号整数";
            case DOUBLE_LITERAL -> "浮点数";
            case STRING_LITERAL -> "字符串";
            case CHAR_LITERAL -> "字符";
            case TY -> "类型";
            case FN -> "fn";
            case LET -> "let";
            case CONST -> "const";
            case AS -> "as";
            case WHILE -> "while";
            case IF -> "if";
            case ELSE -> "else";
            case RETURN -> "return";
            case BREAK -> "break";
            case CONTINUE -> "continue";
            case PLUS -> "+";
            case MINUS -> "-";
            case MUL -> "*";
            case DIV -> "/";
            case ASSIGN -> "=";
            case EQ -> "==";
            case NEQ -> "!=";
            case LT -> "<";
            case GT -> ">";
            case LE -> "<=";
            case GE -> ">=";
            case L_PAREN -> "(";
            case R_PAREN -> ")";
            case L_BRACE -> "{";
            case R_BRACE -> "}";
            case ARROW -> "->";
            case COMMA -> ",";
            case COLON -> ":";
            case SEMICOLON -> ";";
            case COMMENT -> "注释"; // 实际中不应该出现
            case None -> "空";
            case SHARP -> "#";
            case EOF -> "文件结尾";
        };
    }
}
