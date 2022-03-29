package org.cnolan.model;

import org.cnolan.util.CompassUtil;

public class Droid extends Agent {
    public String getLocationString(){
        //TODO: improve this?
        return ""+getX()+" "+getY()+" "+CompassUtil.convertCompassStateToCompassString(getDirection());
    }
}
