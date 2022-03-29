package org.cnolan.parser;

import org.cnolan.model.RectangleMap;

public class RectangleMapParser implements MapParser {
    public RectangleMap parseMap(String mapLine){
        String[] splitLine = mapLine.trim().split("\\s+");
        if(splitLine.length != 2){
            //TODO: improve?
            throw new RuntimeException("Invalid Map Line, too short or too long");
        }
        //TODO: convert or use number format exception
        int width = Integer.parseInt(splitLine[0]);
        int height = Integer.parseInt(splitLine[1]);
        return new RectangleMap(width, height);
    }
}
