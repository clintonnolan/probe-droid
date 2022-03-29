package org.cnolan.model;

import java.util.Objects;

import org.cnolan.util.CompassUtil;

public class FallEvent implements Event {
    private int x;
    private int y;
    private int direction;
    private String agentId;

    public FallEvent() {
    }

    public FallEvent(int x, int y, int direction, String agentId) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.agentId = agentId;
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

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getAgentId() {
        return this.agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof FallEvent)) {
            return false;
        }
        FallEvent fallEvent = (FallEvent) o;
        return x == fallEvent.x && y == fallEvent.y && direction == fallEvent.direction && Objects.equals(agentId, fallEvent.agentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction, agentId);
    }

    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            ", direction='" + getDirection() + "'" +
            ", agentId='" + getAgentId() + "'" +
            "}";
    }    

    @Override
    public String getDisplayString() {
        //TODO: improve this?
        return "Agent " + agentId + " fell off map at " + x + "," + y + " facing " + CompassUtil.convertCompassStateToCompassString(direction)
                + ".";
    }

}
