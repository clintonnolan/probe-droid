package org.cnolan.simulation;

import org.cnolan.model.Action;
import org.cnolan.model.Agent;

public class DroidSimulation implements Simulation{
    public SimulationState run(SimulationState state){
        for(Agent agent : state.getAgents()){
            for(Action action : agent.getActions()){
                action.performAction(state);
            }
        }
        return state;
    }
}
