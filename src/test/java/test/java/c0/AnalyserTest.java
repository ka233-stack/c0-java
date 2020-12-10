package test.java.c0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import c0.analyser.Analyser;
import c0.analyser.o0.O0;
import c0.tokenizer.StringIter;
import c0.tokenizer.TokenType;
import c0.tokenizer.Tokenizer;

public class AnalyserTest {

    @Test
    public void analyserTest() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("analyseTest.txt"));
        } catch (FileNotFoundException e) {
        }
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);

        // analyze
        var analyzer = new Analyser(tokenizer);
        O0 binCodeFile = null;
        try {
            binCodeFile = analyzer.analyse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("\n");
        System.out.println(binCodeFile.toString());
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }

    @Test
    public void toBinCodeTest() {
        Scanner scanner = null;
        PrintStream output = null;
        try {
            output = new PrintStream(new FileOutputStream("output.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            scanner = new Scanner(new File("analyseTest.txt"));
        } catch (FileNotFoundException e) {
        }
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);

        // analyze
        var analyzer = new Analyser(tokenizer);
        O0 binCodeFile = null;
        try {
            binCodeFile = analyzer.analyse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = binCodeFile.toHexCode();
        int size = str.length();
        for (int i = 0; i < size; i += 2) {
            int x = Integer.parseInt("" + str.charAt(i) + str.charAt(i + 1), 16);
            System.out.println(x);
            output.write(x);
        }
        output.close();
    }

    @Test
    public void toHexCodeTest() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("analyseTest.txt"));
        } catch (FileNotFoundException e) {
        }
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);

        // analyze
        var analyzer = new Analyser(tokenizer);
        O0 binCodeFile = null;
        try {
            binCodeFile = analyzer.analyse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(binCodeFile.toHexCodeFormat());
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }

    @Test
    public void tokenTypeToStringTest() {
        System.out.println(TokenType.SEMICOLON);
    }

}
