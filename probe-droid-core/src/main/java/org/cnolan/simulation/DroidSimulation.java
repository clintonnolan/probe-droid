package org.cnolan.simulation;

import org.cnolan.model.Action;
import org.cnolan.model.Agent;

public class DroidSimulation implements Simulation{
    public SimulationState run(SimulationState state){
        for(Agent agent : state.getAgents()){
            //TODO: change to debug logging
            System.out.println("Running Agent: "+agent.getId());
            System.out.println("Actions: "+agent.getActions());
            for(Action action : agent.getActions()){
                action.performAction(state);
            }
        }
        return state;
    }
}
