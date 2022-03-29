package org.cnolan.model;


import org.cnolan.simulation.SimulationState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DroidInstruction implements Action {
    private static Logger LOGGER = LoggerFactory.getLogger(DroidInstruction.class);

    private Droid droid;

    public DroidInstruction(Droid droid) {
        this.droid = droid;
    }

    @Override
    public void performAction(SimulationState state) {
        LOGGER.debug("Instruction: {}",getDisplayString());
        LOGGER.debug("Before: {}",droid);
        performAction(droid, state);
        LOGGER.debug("After: ",droid);
    }

    public abstract void performAction(Droid droid, SimulationState state);

    public abstract String getDisplayString();

}
