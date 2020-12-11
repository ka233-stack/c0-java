package miniplc0java;

import miniplc0java.analyser.Analyser;
import miniplc0java.analyser.o0.O0;
import miniplc0java.tokenizer.StringIter;
import miniplc0java.tokenizer.TokenType;
import miniplc0java.tokenizer.Tokenizer;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

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
//         System.out.println(binCodeFile.toString());
//        System.out.println(binCodeFile.printString());
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }

    @Test
    public void generateTest() {
        Scanner scanner = null;
        PrintStream output = null;
        try {
            scanner = new Scanner(new File("analyseTest.txt"));
            output = new PrintStream(new FileOutputStream("anlyseTest.o0"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
//         System.out.println(binCodeFile.toString());
//        System.out.println(binCodeFile.printString());
        try {
            binCodeFile.writeFile(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }
}




