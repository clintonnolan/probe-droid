package org.cnolan.model;

import static org.assertj.core.api.Assertions.*;

import org.cnolan.model.helper.DroidInstructionTestHelper;
import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidForwardInstructionTest {
    private RectangleMap exampleMap = new RectangleMap(4,6);

    @Test
    void whenMoveNorthInMiddleOfMapThenMoveNorth(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(4);
        assertThat(droid.getDirection()).isEqualTo(0);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveEastInMiddleOfMapThenMoveEast(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(1);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(4);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(1);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveSouthInMiddleOfMapThenMoveSouth(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(2);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(2);
        assertThat(droid.getDirection()).isEqualTo(2);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveWestInMiddleOfMapThenMoveWest(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setDirection(3);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(2);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(3);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveNorthOffOfMapThenFallOffMap(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setY(6);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(6);
        assertThat(droid.getDirection()).isEqualTo(0);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 3,6 facing N.");
    }

    @Test
    void whenMoveEastOffOfMapThenFallOffMap(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setX(4);
        droid.setDirection(1);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(4);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(1);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 4,3 facing E.");
    }

    @Test
    void whenMoveSouthOffOfMapThenFallOffMap(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setY(0);
        droid.setDirection(2);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(0);
        assertThat(droid.getDirection()).isEqualTo(2);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 3,0 facing S.");
    }

    @Test
    void whenMoveWestOffOfMapThenFallOffMap(){
        Droid droid = DroidInstructionTestHelper.createExampleDroid();
        droid.setX(0);
        droid.setDirection(3);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = DroidInstructionTestHelper.createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(0);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(3);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 0,3 facing W.");
    }
}
