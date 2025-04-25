package dev.thejurmik.utils.system;

import java.io.OutputStream;
import java.io.PrintStream;

public class SecureExit {
    public static void exit() {
        silenceConsole();

        Runtime.getRuntime().halt(-1);
    }

    private static void silenceConsole() {
        System.setOut(new PrintStream(new NullOutputStream()));
        System.setErr(new PrintStream(new NullOutputStream()));
    }

    private static class NullOutputStream extends OutputStream {
        @Override
        public void write(int b) {
        }
    }
}