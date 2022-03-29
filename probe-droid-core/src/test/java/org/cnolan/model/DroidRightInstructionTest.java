package org.cnolan.model;

import static org.assertj.core.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.cnolan.model.helper.DroidInstructionTestHelper;
import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidRightInstructionTest {

    private RectangleMap exampleMap = new RectangleMap(4,6);

    @Test
    void whenTurnRightAtWestThenEndAtNorth(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(3);
        DroidRightInstruction instruction = new DroidRightInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);

        instruction.performAction(state);

        assertThat(droid.getDirection()).isEqualTo(0);

    }

    @Test
    void whenTurnRightAtNorthThenEndAtEast(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(0);
        DroidRightInstruction instruction = new DroidRightInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);

        instruction.performAction(state);

        assertThat(droid.getDirection()).isEqualTo(1);
    }
}
