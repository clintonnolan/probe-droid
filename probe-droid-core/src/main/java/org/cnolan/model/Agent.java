package org.cnolan.model;

import java.util.List;
import java.util.Objects;

import org.cnolan.util.CompassUtil;

public abstract class Agent {
    private String id;
    private int x;
    private int y;
    private boolean onMap;
    private int direction;
    private List<Action> actions;


    public Agent() {
    }

    public Agent(String id, int x, int y, boolean onMap, int direction, List<Action> actions) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.onMap = onMap;
        this.direction = direction;
        this.actions = actions;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isOnMap() {
        return this.onMap;
    }

    public boolean getOnMap() {
        return this.onMap;
    }

    public void setOnMap(boolean onMap) {
        this.onMap = onMap;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Agent)) {
            return false;
        }
        Agent agent = (Agent) o;
        return Objects.equals(id, agent.id) && x == agent.x && y == agent.y && onMap == agent.onMap && direction == agent.direction && Objects.equals(actions, agent.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, onMap, direction, actions);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", onMap='" + isOnMap() + "'" +
            ", direction='" + CompassUtil.convertCompassStateToCompassString(getDirection()) + "'" +
            ", actions='" + getActions() + "'" +
            "}";
    }    

    public abstract String getLocationString();
}
