package org.cnolan;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.cnolan.output.DroidPositionWriter;
import org.cnolan.output.OutputWriter;
import org.cnolan.parser.DroidParser;
import org.cnolan.parser.Parser;
import org.cnolan.parser.ParserReader;
import org.cnolan.parser.PlainDroidFormatStringParser;
import org.cnolan.parser.RectangleMapParser;
import org.cnolan.simulation.DroidSimulation;
import org.cnolan.simulation.Simulation;

public class Main{

    public static void main(String[] args) throws IOException {
        ParserReader parserReader = new ParserReader();
        RectangleMapParser mapParser = new RectangleMapParser();
        DroidParser droidParser = new DroidParser();
        Parser<String> parser = new PlainDroidFormatStringParser(droidParser, mapParser, parserReader);
        Simulation simulation = new DroidSimulation();
        OutputWriter outputWriter = new DroidPositionWriter();
        SimulationPipeline<String> sim = new SimulationPipeline<>(parser, simulation, outputWriter);

        String input = new String(System.in.readAllBytes(), StandardCharsets.UTF_8);

        sim.run(input);
    }
}