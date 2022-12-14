package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public class DroidRightInstruction extends DroidInstruction {

    public DroidRightInstruction(Droid droid) {
        super(droid);
    }

    @Override
    public void performAction(Droid droid, SimulationState state) {
        if(!droid.isOnMap()){
            return;
        }
        int currentDirection = droid.getDirection();
        currentDirection = (currentDirection + 1) % 4;
        droid.setDirection(currentDirection);
    }

    @Override
    public String getDisplayString(){
        return "right";
    }
    
    @Override
    public String toString(){
        return "R";
    }
}
