package org.cnolan.parser;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.cnolan.exception.ValidationException;
import org.cnolan.model.Agent;
import org.cnolan.model.Map;
import org.cnolan.simulation.SimulationState;

public class PlainDroidFormatStringParser implements Parser<String>{
    private DroidParser droidParser;
    private RectangleMapParser mapParser;
    private ParserReader parserReader;

    public PlainDroidFormatStringParser(DroidParser droidParser, RectangleMapParser mapParser, ParserReader parserReader) {
        this.droidParser = droidParser;
        this.mapParser = mapParser;
        this.parserReader = parserReader;
    }
    
    @Override
    public SimulationState parseInput(String input) {
        List<ValidationIssue> validationIssues = new ArrayList<>();

        SimulationState state = new SimulationState();
        state.setEvents(new ArrayList<>());
        
        BufferedReader reader = new BufferedReader(new StringReader(input));
        DroidLines lines = parserReader.readDroidLines(reader);
        Map map = mapParser.parseMap(lines.getMapLine());
        state.setMap(map);
        List<Agent> agents = new ArrayList<>();
        int agentCounter = 0;
        for (List<String> lineList : lines.getDroidLines()){
            Agent agent = droidParser.parseDroid(lineList);
            agent.setId("Droid "+Integer.toString(agentCounter));
            //TODO: improve?
            if(!map.isCoordinateOnMap(agent.getX(), agent.getY())){
                validationIssues.add(new ValidationIssue("Initial agent coordinates for agent "+agentCounter+" are not on the map"));
            }
            agent.setOnMap(true);
            agentCounter++;
            agents.add(agent);
        }
        state.setAgents(agents);

        if (validationIssues.size() > 0) {
            throw new ValidationException(validationIssues);
        }

        return state;
    }
    
}
