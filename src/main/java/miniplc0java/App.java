package miniplc0java;

import miniplc0java.analyser.Analyser;
import miniplc0java.analyser.o0.O0;
import miniplc0java.error.CompileError;
import miniplc0java.tokenizer.StringIter;
import miniplc0java.tokenizer.Tokenizer;

import java.io.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws IOException, CompileError {
        Scanner scanner = null;
        PrintStream output = null;
        scanner = new Scanner(new File(args[0]));
        output = new PrintStream(new FileOutputStream(args[1]));

        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);
        Analyser analyser = new Analyser(tokenizer);
        O0 binCodeFile = null;

        binCodeFile = analyser.analyse();

        binCodeFile.writeFile(output);
        output.close();

    }
}
