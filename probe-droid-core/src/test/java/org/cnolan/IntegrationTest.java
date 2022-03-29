package org.cnolan;

import org.cnolan.exception.ValidationException;
import org.cnolan.output.DroidPositionWriter;
import org.cnolan.output.OutputWriter;
import org.cnolan.parser.DroidParser;
import org.cnolan.parser.PlainDroidFormatStringParser;
import org.cnolan.parser.Parser;
import org.cnolan.parser.ParserReader;
import org.cnolan.parser.RectangleMapParser;
import org.cnolan.simulation.DroidSimulation;
import org.cnolan.simulation.Simulation;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Fail;

public class IntegrationTest {
    private static SimulationPipeline<String> sim = null;

    @BeforeAll
    static void prepareSimulationPipeline() {
        ParserReader parserReader = new ParserReader();
        RectangleMapParser mapParser = new RectangleMapParser();
        DroidParser droidParser = new DroidParser();
        Parser<String> parser = new PlainDroidFormatStringParser(droidParser, mapParser, parserReader);
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
    void droidFallsOffNorthEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 N\n"
                + "MMMMMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 5 N\n"
        +"Agent Droid 0 fell off map at 3,5 facing N.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidFallsOffEastEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 E\n"
                + "MMMMMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "5 3 E\n"
        +"Agent Droid 0 fell off map at 5,3 facing E.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidFallsOffSouthEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 S\n"
                + "MMMMMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 0 S\n"
        +"Agent Droid 0 fell off map at 3,0 facing S.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidFallsOffWestEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 W\n"
                + "MMMMMM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "0 3 W\n"
        +"Agent Droid 0 fell off map at 0,3 facing W.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void droidStopsMovingAfterFallingOffEdgeTest() {
        String inputString = "5 5\n"
                + "3 3 N\n"
                + "MMMMRMMLLM";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 5 N\n"
        +"Agent Droid 0 fell off map at 3,5 facing N.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void twoDroidsFallOffEdgeButOneDoesNotTest() {
        String inputString = "5 5\n"
                + "3 3 N\n"
                + "MMMMMM\n"
                + "1 1 N\n"
                + "MMMRMMM\n"
                + "3 3 S\n"
                + "MMMMMM\n";

        String outputReport = sim.run(inputString);

        String expectedOutput = "3 5 N\n"
        +"4 4 E\n"
        +"3 0 S\n"
        +"Agent Droid 0 fell off map at 3,5 facing N.\n"
        +"Agent Droid 2 fell off map at 3,0 facing S.\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    @Disabled("Not currently working, but this is a bonus objective")
    void emptyLinesIgnored() {
        String inputString = "\n5 5\n"
                + "\n\n\n1 2 N\n\n"
                + "LMLMLMLMM\n\n"
                + "\n3 3 E\n\n"
                + "\nMMRMMRMRRM\n\n";

        String outputReport = sim.run(inputString);

        String expectedOutput = "1 3 N\n"
        +"5 1 E\n";
        
        assertThat(outputReport).isEqualTo(expectedOutput);
    }

    @Test
    void notEnoughMapDigitsThrowsValidationException() {
        String inputString = "5\n";
        validationTestWithOneExpectedMessage(inputString, "Too many or too few entries on map line");
    }

    @Test
    void tooManyMapDigitsThrowsValidationException() {
        String inputString = "5 5 5\n";
        validationTestWithOneExpectedMessage(inputString, "Too many or too few entries on map line");
    }

    @Test
    void invalidWidthThrowsValidationException() {
        String inputString = "X 5\n";
        validationTestWithOneExpectedMessage(inputString, "Invalid width: X");
    }

    @Test
    void invalidHeightThrowsValidationException() {
        String inputString = "5 X\n";
        validationTestWithOneExpectedMessage(inputString, "Invalid height: X");
    }

    @Test
    void bothMapLinesInvalidThrowsValidationExceptionWithMultipleExceptions() {
        String inputString = "X X";
        try{
            sim.run(inputString);
            Fail.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException e){
            assertThat(e.getIssues()).hasSize(2);
            assertThat(e.getIssues().get(0).getMessage()).isEqualTo("Invalid width: X");
            assertThat(e.getIssues().get(1).getMessage()).isEqualTo("Invalid height: X");
        }
    }

    @Test
    void negativeWidthThrowsValidationException() {
        String inputString = "-1 5\n";
        validationTestWithOneExpectedMessage(inputString, "Negative width");
    }

    @Test
    void negativeHeightThrowsValidationException() {
        String inputString = "5 -1\n";
        validationTestWithOneExpectedMessage(inputString, "Negative height");
    }

    @Test
    void notEnoughDroidLinesThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2 N";
        validationTestWithOneExpectedMessage(inputString, "Missing line at end");
    }

    @Test
    void notEnoughDroidStateInputsThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Too many or too few entries on droid state line");
    }

    @Test
    void tooManyDroidStateInputsThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2 N N\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Too many or too few entries on droid state line");
    }

    @Test
    void invalidDroidXThrowsValidationException() {
        String inputString = "5 5\n"
        +"X 2 N\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Invalid number for x X");
    }

    @Test
    void invalidDroidYThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 X N\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Invalid number for y X");
    }

    @Test
    void negativeXDroidXThrowsValidationException() {
        String inputString = "5 5\n"
        +"-1 2 N\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Negative x");
    }

    @Test
    void negativeYThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 -2 N\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Negative y");
    }

    @Test
    void invalidDroidDirectionThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2 X\n"
        +"MMLRM";
        validationTestWithOneExpectedMessage(inputString, "Invalid direction X");
    }

    @Test
    void invalidDroidInstructionThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2 N\n"
        +"MXLRM";
        validationTestWithOneExpectedMessage(inputString, "Invalid character X in list of actions");
    }

    @Test
    void spaceInDroidInstructionsThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 2 N\n"
        +"MM LRM";
        validationTestWithOneExpectedMessage(inputString, "Invalid character   in list of actions");
    }

    @Test
    void droidOffMapInXDirectionThrowsValidationException() {
        String inputString = "5 5\n"
        +"20 2 N\n"
        +"MMMLRM";
        validationTestWithOneExpectedMessage(inputString, "Initial agent coordinates for agent 0 are not on the map");
    }

    @Test
    void droidOffMapInYDirectionThrowsValidationException() {
        String inputString = "5 5\n"
        +"1 20 N\n"
        +"MMMLRM";
        validationTestWithOneExpectedMessage(inputString, "Initial agent coordinates for agent 0 are not on the map");
    }

    private void validationTestWithOneExpectedMessage(String inputString, String expectedMessage){
        try{
            sim.run(inputString);
            Fail.failBecauseExceptionWasNotThrown(ValidationException.class);
        } catch (ValidationException e){
            assertThat(e.getIssues()).hasSize(1);
            assertThat(e.getIssues().get(0).getMessage()).isEqualTo(expectedMessage);
        }
    }
}
