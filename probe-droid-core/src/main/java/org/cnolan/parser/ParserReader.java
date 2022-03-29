package org.cnolan.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.cnolan.exception.ValidationException;

public class ParserReader {
    public DroidLines readDroidLines(BufferedReader reader) {
        List<ValidationIssue> validationIssues = new ArrayList<>();
        String mapLine = null;
        try {
            mapLine = reader.readLine();
        } catch (IOException e) {
            validationIssues.add(new ValidationIssue("Couldn't read line"));
            throw new ValidationException(validationIssues);
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
                validationIssues.add(new ValidationIssue("Missing line at end"));
                throw new ValidationException(validationIssues);
            }

        } catch (IOException e) {
            validationIssues.add(new ValidationIssue("Couldn't read line"));
            throw new ValidationException(validationIssues);
        }

        DroidLines droidLinesOutput = new DroidLines(mapLine, droidLines);
        return droidLinesOutput;
    }
}
