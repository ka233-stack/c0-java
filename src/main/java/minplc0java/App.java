package minplc0java;

import minplc0java.analyser.Analyser;
import minplc0java.analyser.o0.O0;
import minplc0java.tokenizer.StringIter;
import minplc0java.tokenizer.Tokenizer;

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
                System.out.println(x);
                output.write(x);
            }
        }
        if (output != null)
            output.close();

    }
}
