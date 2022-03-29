package org.cnolan.model;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.cnolan.simulation.SimulationState;
import org.junit.jupiter.api.Test;

public class DroidForwardInstructionTest {
    private RectangleMap exampleMap = new RectangleMap(5,7);

    @Test
    void whenMoveNorthInMiddleOfMapThenMoveNorth(){
        Droid droid = createExampleDroid();
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(4);
        assertThat(droid.getDirection()).isEqualTo(0);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveEastInMiddleOfMapThenMoveEast(){
        Droid droid = createExampleDroid();
        droid.setDirection(1);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(4);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(1);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveSouthInMiddleOfMapThenMoveSouth(){
        Droid droid = createExampleDroid();
        droid.setDirection(2);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(2);
        assertThat(droid.getDirection()).isEqualTo(2);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveWestInMiddleOfMapThenMoveWest(){
        Droid droid = createExampleDroid();
        droid.setDirection(3);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.getX()).isEqualTo(2);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(3);
        assertThat(droid.isOnMap()).isTrue();
    }

    @Test
    void whenMoveNorthOffOfMapThenFallOffMap(){
        Droid droid = createExampleDroid();
        droid.setY(6);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();

        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(6);
        assertThat(droid.getDirection()).isEqualTo(0);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 3,6 facing 0.");
    }

    @Test
    void whenMoveEastOffOfMapThenFallOffMap(){
        Droid droid = createExampleDroid();
        droid.setX(4);
        droid.setDirection(1);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(4);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(1);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 4,3 facing 1.");
    }

    @Test
    void whenMoveSouthOffOfMapThenFallOffMap(){
        Droid droid = createExampleDroid();
        droid.setY(0);
        droid.setDirection(2);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(3);
        assertThat(droid.getY()).isEqualTo(0);
        assertThat(droid.getDirection()).isEqualTo(2);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 3,0 facing 2.");
    }

    @Test
    void whenMoveWestOffOfMapThenFallOffMap(){
        Droid droid = createExampleDroid();
        droid.setX(0);
        droid.setDirection(3);
        DroidForwardInstruction instruction = new DroidForwardInstruction(droid);
        SimulationState state = createExampleState(exampleMap, droid);
        
        instruction.performAction(state);

        assertThat(droid.isOnMap()).isFalse();
        
        assertThat(droid.getX()).isEqualTo(0);
        assertThat(droid.getY()).isEqualTo(3);
        assertThat(droid.getDirection()).isEqualTo(3);
        assertThat(state.getEvents()).hasSize(1);
        assertThat(state.getEvents().get(0).getDisplayString()).isEqualTo("Agent Droid fell off map at 0,3 facing 3.");
    }

    private Droid createExampleDroid(){
        Droid droid = new Droid();
        droid.setX(3);
        droid.setY(3);
        droid.setDirection(0);
        droid.setActions(new ArrayList<>());
        droid.setId("Droid");
        droid.setOnMap(true);
        return droid;
    }

    private SimulationState createExampleState(Map map, Droid droid){
        SimulationState state = new SimulationState();
        state.setMap(map);
        state.setEvents(new ArrayList<>());
        List<Agent> agents = new ArrayList<>();
        agents.add(droid);
        state.setAgents(agents);
        return state;
    }
}
