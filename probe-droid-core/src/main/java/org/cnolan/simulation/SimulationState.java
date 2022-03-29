package org.cnolan.simulation;

import java.util.List;
import java.util.Objects;

import org.cnolan.model.Agent;
import org.cnolan.model.Event;
import org.cnolan.model.Map;

public class SimulationState {
    private Map map;
    private List<Agent> agents;
    private List<Event> events;


    public SimulationState() {
    }

    public SimulationState(Map map, List<Agent> agents, List<Event> events) {
        this.map = map;
        this.agents = agents;
        this.events = events;
    }

    public Map getMap() {
        return this.map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Agent> getAgents() {
        return this.agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SimulationState)) {
            return false;
        }
        SimulationState simulationState = (SimulationState) o;
        return Objects.equals(map, simulationState.map) && Objects.equals(agents, simulationState.agents) && Objects.equals(events, simulationState.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map, agents, events);
    }

    @Override
    public String toString() {
        return "{" +
            " map='" + getMap() + "'" +
            ", agents='" + getAgents() + "'" +
            ", events='" + getEvents() + "'" +
            "}";
    }

}
