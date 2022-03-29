package org.cnolan.model.helper;

import java.util.ArrayList;
import java.util.List;

import org.cnolan.model.Agent;
import org.cnolan.model.Droid;
import org.cnolan.model.Map;
import org.cnolan.simulation.SimulationState;

public class DroidInstructionTestHelper {
    public static Droid createExampleDroid(){
        Droid droid = new Droid();
        droid.setX(3);
        droid.setY(3);
        droid.setDirection(0);
        droid.setActions(new ArrayList<>());
        droid.setId("Droid");
        droid.setOnMap(true);
        return droid;
    }

    public static SimulationState createExampleState(Map map, Droid droid){
        SimulationState state = new SimulationState();
        state.setMap(map);
        state.setEvents(new ArrayList<>());
        List<Agent> agents = new ArrayList<>();
        agents.add(droid);
        state.setAgents(agents);
        return state;
    }
}
