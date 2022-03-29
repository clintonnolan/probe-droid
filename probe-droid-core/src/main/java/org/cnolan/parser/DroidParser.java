package org.cnolan.parser;

import java.util.ArrayList;
import java.util.List;

import org.cnolan.exception.ValidationException;
import org.cnolan.model.Action;
import org.cnolan.model.Droid;
import org.cnolan.model.DroidForwardInstruction;
import org.cnolan.model.DroidLeftInstruction;
import org.cnolan.model.DroidRightInstruction;
import org.cnolan.util.CompassUtil;

public class DroidParser {
    public Droid parseDroid(List<String> droidLines) {
        List<ValidationIssue> validationIssues = new ArrayList<>();
        Droid droid = new Droid();

        if (droidLines.size() != 2) {
            validationIssues.add(new ValidationIssue("Too many or too few droid lines"));
            throw new ValidationException(validationIssues);
        }
        String stateLine = droidLines.get(0);
        String[] stateEntries = stateLine.trim().split("\\s+");
        if (stateEntries.length != 3) {
            validationIssues.add(new ValidationIssue("Too many or too few entries on droid state line"));
            throw new ValidationException(validationIssues);
        }

        int x = -1;
        int y = -1;
        int direction = -1;

        try {
            x = Integer.parseInt(stateEntries[0]);
        } catch (NumberFormatException e) {
            validationIssues.add(new ValidationIssue("Invalid number for x " + stateEntries[0]));
        }
        try {
            y = Integer.parseInt(stateEntries[1]);
        } catch (NumberFormatException e) {
            validationIssues.add(new ValidationIssue("Invalid number for y " + stateEntries[1]));
        }
        try {
            direction = CompassUtil.convertCompassStringToCompassState(stateEntries[2]);
        } catch (RuntimeException e) {
            validationIssues.add(new ValidationIssue("Invalid direction " + stateEntries[2]));
        }

        if(x < 0){
            validationIssues.add(new ValidationIssue("Negative x"));
        }
        if(y < 0){
            validationIssues.add(new ValidationIssue("Negative y"));
        }

        if (validationIssues.size() > 0) {
            throw new ValidationException(validationIssues);
        }

        droid.setX(x);
        droid.setY(y);
        droid.setDirection(direction);

        List<Action> actions = new ArrayList<>();
        String actionLine = droidLines.get(1).trim();
        for (char c : actionLine.toCharArray()) {
            // TODO: improve this
            switch (c) {
                case 'L':
                    actions.add(new DroidLeftInstruction(droid));
                    break;
                case 'R':
                    actions.add(new DroidRightInstruction(droid));
                    break;
                case 'M':
                    actions.add(new DroidForwardInstruction(droid));
                    break;
                default:
                    validationIssues.add(new ValidationIssue("Invalid character " + c + " in list of actions"));
                    throw new RuntimeException();
            }
        }

        if (validationIssues.size() > 0) {
            throw new ValidationException(validationIssues);
        }

        droid.setActions(actions);

        return droid;
    }
}
