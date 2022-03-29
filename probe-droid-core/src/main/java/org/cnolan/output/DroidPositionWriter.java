package org.cnolan.output;

import org.cnolan.model.Agent;
import org.cnolan.model.Event;
import org.cnolan.simulation.SimulationState;

public class DroidPositionWriter implements OutputWriter {
    public String generateOutputString(SimulationState state){
        StringBuffer s = new StringBuffer();
        for(Agent agent : state.getAgents()){
            s.append(agent.getLocationString());
            s.append("\n");
        }
        for(Event event : state.getEvents()){
            s.append(event.getDisplayString());
            s.append("\n");
        }
        return s.toString();
    }
}
