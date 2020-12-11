package miniplc0java;

import miniplc0java.analyser.Analyser;
import miniplc0java.analyser.o0.O0;
import miniplc0java.error.CompileError;
import miniplc0java.tokenizer.StringIter;
import miniplc0java.tokenizer.TokenType;
import miniplc0java.tokenizer.Tokenizer;
import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class AnalyserTest {

    @Test
    public void analyserTest() throws FileNotFoundException, CompileError {
        Scanner scanner = new Scanner(new File("input.txt"));
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);
        // analyze
        var analyzer = new Analyser(tokenizer);
        O0 binCodeFile = analyzer.analyse();

        System.out.println("\n");
        System.out.println(binCodeFile.toString());
        System.out.println("##########################################\n");
        System.out.println(binCodeFile.printString());
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }

    @Test
    public void generateTest() throws FileNotFoundException, CompileError {
        Scanner scanner = null;
        PrintStream output = null;
        scanner = new Scanner(new File("input.txt"));
        output = new PrintStream(new FileOutputStream("output.o0"));
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);

        // analyze
        var analyzer = new Analyser(tokenizer);
        O0 binCodeFile = null;
        binCodeFile = analyzer.analyse();
        System.out.println("\n");
        System.out.println(binCodeFile.toString());
        System.out.println("##########################################\n");
        System.out.println(binCodeFile.printString());
        try {
            binCodeFile.writeFile(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("##########################################\n");
        System.out.println(analyzer.getSymbolTable());
    }
}




