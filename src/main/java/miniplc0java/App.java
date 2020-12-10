package miniplc0java;

import miniplc0java.analyser.Analyser;
import miniplc0java.analyser.o0.O0;
import miniplc0java.tokenizer.StringIter;
import miniplc0java.tokenizer.Tokenizer;

import java.io.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = null;
        PrintStream output = null;
        try {
            scanner = new Scanner(new File(args[0]));
            output = new PrintStream(new FileOutputStream(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        StringIter stringIter = new StringIter(scanner);
        Tokenizer tokenizer = new Tokenizer(stringIter);
        Analyser analyser = new Analyser(tokenizer);
        O0 binCodeFile = null;
        try {
            binCodeFile = analyser.analyse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str;
        if (binCodeFile != null) {
            str = binCodeFile.toHexCode();
            int size = str.length();
            for (int i = 0; i < size; i += 2) {
                int x = Integer.parseInt("" + str.charAt(i) + str.charAt(i + 1), 16);
                output.write(x);
            }
        }
        if (output != null)
            output.close();

    }
}
