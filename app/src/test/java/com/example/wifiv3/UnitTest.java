package com.example.wifiv3;

import org.junit.Test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class UnitTest {
    File file = new File("Hello.txt");

    @Test
    public void bruh() {
        try {
            FileWriter myWriter = new FileWriter("deez.txt");
            myWriter.write("Hello world");
            myWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }
}