package org.cnolan.parser;

import java.util.ArrayList;
import java.util.List;

import org.cnolan.exception.ValidationException;
import org.cnolan.model.RectangleMap;

public class RectangleMapParser {
    public RectangleMap parseMap(String mapLine){
        List<ValidationIssue> validationIssues = new ArrayList<>();
        String[] splitLine = mapLine.trim().split("\\s+");
        if(splitLine.length != 2){
            validationIssues.add(new ValidationIssue("Too many or too few entries on map line"));
            throw new ValidationException(validationIssues);
        }
        
        int width = -1;
        int height = -1;

        try {
            width = Integer.parseInt(splitLine[0]);
        } catch (NumberFormatException e) {
            validationIssues.add(new ValidationIssue("Invalid width: " + splitLine[0]));
        }
        try {
            height = Integer.parseInt(splitLine[1]);
        } catch (NumberFormatException e) {
            validationIssues.add(new ValidationIssue("Invalid height: " + splitLine[1]));
        }

        if (validationIssues.size() > 0) {
            throw new ValidationException(validationIssues);
        }

        if(width < 0){
            validationIssues.add(new ValidationIssue("Negative width"));
        }
        if(height < 0){
            validationIssues.add(new ValidationIssue("Negative height"));
        }

        if (validationIssues.size() > 0) {
            throw new ValidationException(validationIssues);
        }

        return new RectangleMap(width, height);
    }
}
