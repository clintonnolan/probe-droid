package org.cnolan.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParserReader {
    public DroidLines readDroidLines(BufferedReader reader) {
        String mapLine = null;
        try {
            mapLine = reader.readLine();
        } catch (IOException e) {
            // TODO: improve this?
            throw new RuntimeException("Couldn't read line");
        }

        List<List<String>> droidLines = new ArrayList<>();
        try {
            List<String> currentLines = new ArrayList<>();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String trimmedLine = line.trim();
                if (!trimmedLine.isEmpty()) {
                    currentLines.add(trimmedLine);
                    if (currentLines.size() == 2) {
                        droidLines.add(currentLines);
                        currentLines = new ArrayList<>();
                    }
                }
            }

            if (currentLines.size() > 0) {
                // TODO: improve this?
                throw new RuntimeException("Missing line at end");
            }

        } catch (IOException e) {
            // TODO: improve this?
            throw new RuntimeException("Couldn't read line");
        }

        DroidLines droidLinesOutput = new DroidLines(mapLine, droidLines);
        return droidLinesOutput;
    }
}
