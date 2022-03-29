package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public abstract class DroidInstruction implements Action {

    private Droid droid;

    public DroidInstruction(Droid droid) {
        this.droid = droid;
    }

    @Override
    public void performAction(SimulationState state) {
        //TODO: turn this into debug logging
        System.out.println("Instruction: "+getDisplayString());
        System.out.println("Before: "+droid);
        performAction(droid, state);
        System.out.println("After: "+droid);
    }

    public abstract void performAction(Droid droid, SimulationState state);

    public abstract String getDisplayString();

}
