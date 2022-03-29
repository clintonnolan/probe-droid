package org.cnolan.model;

import java.util.Objects;

public class RectangleMap implements Map{
    private int width;
    private int height;

    public RectangleMap() {
    }

    public RectangleMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof RectangleMap)) {
            return false;
        }
        RectangleMap rectangleMap = (RectangleMap) o;
        return width == rectangleMap.width && height == rectangleMap.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "{" +
            " width='" + getWidth() + "'" +
            ", height='" + getHeight() + "'" +
            "}";
    }

    @Override
    public boolean isCoordinateOnMap(int x, int y) {
        if(x < 0 || x >= width){
            return false;
        }
        if(y < 0 || y >= height){
            return false;
        }
        return true;
    }

}