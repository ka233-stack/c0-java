package miniplc0java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import miniplc0java.tokenizer.StringIter;
import miniplc0java.tokenizer.Token;
import miniplc0java.tokenizer.TokenType;
import miniplc0java.tokenizer.Tokenizer;
import miniplc0java.util.Pos;
import org.junit.Test;


public class TokenizerTest {

    @Test
    public void toStringTest() {
        Token t = new Token(TokenType.ASSIGN, '=', new Pos(12, 122), new Pos(145, 21));
        System.out.println("toString:" + t.toString());
        System.out.println("toStringAlt:" + t.toStringAlt());
    }

    @Test
    public void tokenizerTest() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("test1.txt"));
        } catch (FileNotFoundException ignored) {
        }
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);

        // tokenize
        var tokens = new ArrayList<Token>();
        try {
            while (true) {
                var token = tokenizer.nextToken();
                // 注释不返回token
                if (token == null)
                    continue;
                if (token.getTokenType().equals(TokenType.EOF))
                    break;
                tokens.add(token);
                System.out.println(token.toString());
            }
        } catch (Exception e) {
            // 遇到错误不输出，直接退出
            System.err.println(e);
            System.exit(0);
            return;
        }
        for (Token token : tokens) {
            System.out.println(token.toString());
        }
    }
}
