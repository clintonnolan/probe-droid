package org.cnolan.model;

public class Droid extends Agent {
    public String getLocationString(){
        //TODO: improve this?
        //TODO: Direction should be the direction symbol
        return ""+getX()+" "+getY()+" "+getDirection();
    }
}
