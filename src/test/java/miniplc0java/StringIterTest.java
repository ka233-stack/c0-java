package miniplc0java;

import miniplc0java.tokenizer.StringIter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StringIterTest {
    Scanner scanner;


    @Test
    public void nextPosTest() {
        try {
            scanner = new Scanner(new File("test1.txt"));
        } catch (FileNotFoundException e) {
        }
        StringIter stringIter = new StringIter(scanner);
        stringIter.readAll();
        System.out.println(stringIter.nextPos());
        System.out.println(stringIter.nextChar());
    }
}
