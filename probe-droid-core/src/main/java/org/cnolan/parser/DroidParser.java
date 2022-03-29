package org.cnolan.parser;

import java.util.ArrayList;
import java.util.List;

import org.cnolan.model.Action;
import org.cnolan.model.Droid;
import org.cnolan.model.DroidForwardInstruction;
import org.cnolan.model.DroidInstruction;
import org.cnolan.model.DroidLeftInstruction;
import org.cnolan.model.DroidRightInstruction;
import org.cnolan.util.CompassUtil;

public class DroidParser {
    public Droid parseDroid(List<String> droidLines){
        Droid droid = new Droid();

        if(droidLines.size() != 2){
            //TODO: improve?
            throw new RuntimeException("Too many or too few droid lines");
        }
        String stateLine = droidLines.get(0);
        String[] stateEntries = stateLine.trim().split("\\s+");
        if(stateEntries.length != 3){
            //TODO: improve?
            throw new RuntimeException("Too many or too few entries on droid state line");
        }
        //TODO: convert or use number format exception
        int x = Integer.parseInt(stateEntries[0]);
        int y = Integer.parseInt(stateEntries[1]);
        //TODO: catch runtime exception and convert it to something more meaningful
        int direction = CompassUtil.convertCompassStringToCompassState(stateEntries[2]);
        
        droid.setX(x);
        droid.setY(y);
        droid.setDirection(direction);

        List<Action> actions = new ArrayList<>();
        String actionLine = droidLines.get(1).trim();
        for(char c : actionLine.toCharArray()){
            Action action = null;
            //TODO: improve this
            switch(c){
                case 'L':
                    action = new DroidLeftInstruction(droid);
                    break;
                case 'R':
                    action = new DroidRightInstruction(droid);
                    break;
                case 'M':
                    action = new DroidForwardInstruction(droid);
                    break;
                default:
                    //TODO: improve this
                    throw new RuntimeException("Invalid character in list of actions");
            }
        }

        droid.setActions(actions);

        return droid;
    }
}
