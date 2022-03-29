package org.cnolan;

import org.cnolan.output.DroidPositionWriter;
import org.cnolan.output.OutputWriter;
import org.cnolan.parser.DroidParser;
import org.cnolan.parser.DroidStringParser;
import org.cnolan.parser.Parser;
import org.cnolan.parser.ParserReader;
import org.cnolan.parser.RectangleMapParser;
import org.cnolan.simulation.DroidSimulation;
import org.cnolan.simulation.Simulation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IntegrationTest {
    private static SimulationPipeline<String> sim = null;

    @BeforeAll
    static void prepareSimulationPipeline() {
        ParserReader parserReader = new ParserReader();
        RectangleMapParser mapParser = new RectangleMapParser();
        DroidParser droidParser = new DroidParser();
        Parser<String> parser = new DroidStringParser(droidParser, mapParser, parserReader);
        Simulation simulation = new DroidSimulation();
        OutputWriter outputWriter = new DroidPositionWriter();
        sim = new SimulationPipeline<>(parser, simulation, outputWriter);
    }

    @Test
    void testExampleFromSpec() {
        String inputString = "5 5\n"
                + "1 2 N\n"
                + "LMLMLMLMM\n"
                + "3 3 E\n"
                + "MMRMMRMRRM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "1 3 N\n"
        +"5 1 E\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void noDroidsTest() {
        String inputString = "5 5";

        String outputReport = sim.run(inputString);

        String expectedOutput = "";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidFallsOffEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 N\n"
                + "MMMMMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 5 N\n"
        +"Agent Droid 0 fell off map at 3,5 facing N.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidStopsMovingAfterFallingOffEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 N\n"
                + "MMMMRMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 5 N\n"
        +"Agent Droid 0 fell off map at 3,5 facing N.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }
}
