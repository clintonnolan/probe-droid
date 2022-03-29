package org.cnolan.model;

import static org.assertj.core.api.Assertions.*;

import org.cnolan.model.helper.DroidInstructionTestHelper;
import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidLeftInstructionTest {

    private RectangleMap exampleMap = new RectangleMap(4,6);

    @Test
    void whenTurnLeftAtWestThenEndAtSouth(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(3);
        DroidLeftInstruction instruction = new DroidLeftInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);

        instruction.performAction(state);

        assertThat(droid.getDirection()).isEqualTo(2);
    }

    @Test
    void whenTurnLeftAtNorthThenEndAtWest(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(0);
        DroidLeftInstruction instruction = new DroidLeftInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);

        instruction.performAction(state);

        assertThat(droid.getDirection()).isEqualTo(3);
    }
}
