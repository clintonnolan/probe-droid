package org.cnolan.parser;

import org.cnolan.simulation.SimulationState;

public interface Parser<T> {
    public SimulationState parseInput(T input);
}
