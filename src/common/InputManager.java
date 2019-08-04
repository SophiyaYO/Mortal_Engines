package common;

import common.interfaces.InputReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputManager implements InputReader {
    private BufferedReader reader;

    public InputManager() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        String result = null;

        try {
            result = this.reader.readLine().trim();
        } catch (IOException e) {
        }

        return result;
    }
}
