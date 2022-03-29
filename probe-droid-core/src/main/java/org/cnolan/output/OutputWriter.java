package org.cnolan.output;

import org.cnolan.simulation.SimulationState;

public interface OutputWriter {
    public String generateOutputString(SimulationState state);
}
