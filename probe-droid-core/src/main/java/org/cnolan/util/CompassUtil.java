package org.cnolan.util;

public class CompassUtil {
    // TODO: use a bi-map?

    public static String convertCompassStateToCompassString(int compassState){
        switch(compassState){
            case 0:
                return "N";
            case 1:
                return "E";
            case 2:
                return "S";
            case 3:
                return "W";
            default:
                throw new RuntimeException("Invalid compass state");
        }
    }

    public static int convertCompassStringToCompassState(String compassString){
        switch(compassString){
            case "N":
                return 0;
            case "E":
                return 1;
            case "S":
                return 2;
            case "W":
                return 3;
            default:
                throw new RuntimeException("Invalid compass string");
        }
    }
}
