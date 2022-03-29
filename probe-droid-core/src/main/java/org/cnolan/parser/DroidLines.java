package org.cnolan.parser;

import java.util.List;
import java.util.Objects;

public class DroidLines {
    private String mapLine;
    private List<List<String>> droidLines;

    public DroidLines() {
    }

    public DroidLines(String mapLine, List<List<String>> droidLines) {
        this.mapLine = mapLine;
        this.droidLines = droidLines;
    }

    public String getMapLine() {
        return this.mapLine;
    }

    public List<List<String>> getDroidLines() {
        return this.droidLines;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DroidLines)) {
            return false;
        }
        DroidLines droidLines = (DroidLines) o;
        return Objects.equals(mapLine, droidLines.mapLine) && Objects.equals(droidLines, droidLines.droidLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapLine, droidLines);
    }

    @Override
    public String toString() {
        return "{" +
            " mapLine='" + getMapLine() + "'" +
            ", droidLines='" + getDroidLines() + "'" +
            "}";
    }

}
