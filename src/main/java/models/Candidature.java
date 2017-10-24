package models;

import java.io.Serializable;

public class Candidature implements Serializable {
    private String name;
    private double mark;
    private boolean complete;

    public Candidature(String name, double mark, boolean complete) {
        this.name = name;
        this.mark = mark;
        this.complete = complete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
