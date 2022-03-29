package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public class DroidForwardInstruction extends DroidInstruction {

    public DroidForwardInstruction(Droid droid) {
        super(droid);
    }

    @Override
    public void performAction(Droid droid, SimulationState state) {
        //TODO: improve this?
        int x = droid.getX();
        int y = droid.getY();
        switch(droid.getDirection()){
            case 0:
                y += 1;
                break;
            case 1:
                x += 1;
                break;
            case 2:
                y -= 1;
                break;
            case 3:
                x -= 1;
                break;
            default:
                throw new RuntimeException("Invalid direction");
        }

        if(state.getMap().isCoordinateOnMap(x, y)){
            droid.setX(x);
            droid.setY(y);
        } else {
            droid.setOnMap(false);
            state.getEvents().add(new FallEvent(droid.getX(), droid.getY(), droid.getDirection(), droid.getId()));
        }
    }
    
}
