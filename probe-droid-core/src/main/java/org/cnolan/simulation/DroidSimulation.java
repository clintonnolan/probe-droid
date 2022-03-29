package org.cnolan.simulation;

import org.cnolan.model.Action;
import org.cnolan.model.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroidSimulation implements Simulation{
    private static Logger LOGGER = LoggerFactory.getLogger(DroidSimulation.class);
    public SimulationState run(SimulationState state){
        for(Agent agent : state.getAgents()){
            LOGGER.debug("Running Agent: "+agent.getId());
            LOGGER.debug("Actions: "+agent.getActions());
            for(Action action : agent.getActions()){
                action.performAction(state);
            }
        }
        return state;
    }
}
