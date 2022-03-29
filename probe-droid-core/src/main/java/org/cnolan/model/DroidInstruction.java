package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public abstract class DroidInstruction implements Action {

    private Droid droid;

    public DroidInstruction(Droid droid) {
        this.droid = droid;
    }

    @Override
    public void performAction(SimulationState state) {
        performAction(droid, state);
    }

    public abstract void performAction(Droid droid, SimulationState state);

}
