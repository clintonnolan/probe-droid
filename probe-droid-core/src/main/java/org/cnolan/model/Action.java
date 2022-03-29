package org.cnolan.model;

import org.cnolan.simulation.SimulationState;

public interface Action {
    public void performAction(SimulationState state);
}
