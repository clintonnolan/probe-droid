package org.cnolan.parser;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.cnolan.model.Agent;
import org.cnolan.model.Map;
import org.cnolan.simulation.SimulationState;

public class DroidStringParser implements Parser<String>{
    private DroidParser droidParser;
    private RectangleMapParser mapParser;
    private ParserReader parserReader;

    public DroidStringParser(DroidParser droidParser, RectangleMapParser mapParser, ParserReader parserReader) {
        this.droidParser = droidParser;
        this.mapParser = mapParser;
        this.parserReader = parserReader;
    }
    
    @Override
    public SimulationState parseInput(String input) {
        SimulationState state = new SimulationState();
        state.setEvents(new ArrayList<>());
        
        BufferedReader reader = new BufferedReader(new StringReader(input));
        DroidLines lines = parserReader.readDroidLines(reader);
        Map map = mapParser.parseMap(lines.getMapLine());
        state.setMap(map);
        List<Agent> agents = new ArrayList<>();
        for (List<String> lineList : lines.getDroidLines()){
            Agent agent = droidParser.parseDroid(lineList);
            agents.add(agent);
        }
        state.setAgents(agents);
        return state;
    }
    
}
