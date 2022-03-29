package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public class DroidLeftInstruction extends DroidInstruction {

    public DroidLeftInstruction(Droid droid) {
        super(droid);
    }

    @Override
    public void performAction(Droid droid, SimulationState state) {
        if(!droid.isOnMap()){
            return;
        }
        int currentDirection = droid.getDirection();
        currentDirection -= 1;
        if(currentDirection < 0){
            currentDirection = 3;
        }
        droid.setDirection(currentDirection);
    }

    @Override
    public String getDisplayString(){
        return "left";
    }

    @Override
    public String toString(){
        return "L";
    }
}
