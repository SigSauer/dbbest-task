package com.sigasauer.dbbest.task.entity;

public class Route {
    private int pointA;
    private int pointB;

    public Route(int pointA, int pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public int getPointA() {
        return pointA;
    }

    public void setPointA(int pointA) {
        this.pointA = pointA;
    }

    public int getPointB() {
        return pointB;
    }

    public void setPointB(int pointB) {
        this.pointB = pointB;
    }
}
