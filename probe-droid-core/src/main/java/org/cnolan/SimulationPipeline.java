package org.cnolan;

import org.cnolan.output.OutputWriter;
import org.cnolan.parser.Parser;
import org.cnolan.simulation.Simulation;
import org.cnolan.simulation.SimulationState;

public class SimulationPipeline<T> {
    private Parser<T> parser;
    private Simulation simulation;
    private OutputWriter outputWriter;


    public SimulationPipeline(Parser<T> parser, Simulation simulation, OutputWriter outputWriter) {
        this.parser = parser;
        this.simulation = simulation;
        this.outputWriter = outputWriter;
    }

    public String run(T input){
        SimulationState state = parser.parseInput(input);
        state = simulation.run(state);
        return outputWriter.generateOutputString(state);
    }
}
