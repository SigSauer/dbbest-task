package com.sigasauer.dbbest.task.entity;

import java.util.*;

public class Point {
    private int id;
    private Map<Integer, Integer> relations;

    public Point(int id) {
        this.id = id;
        this.relations = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Integer> getRelations() {
        return relations;
    }

    public void setRelations(Map<Integer, Integer> relations) {
        this.relations = relations;
    }

    public void addRelation(Integer pointId, Integer distance) {
        if(relations.containsKey(pointId)) {
            if(distance < relations.get(pointId)) relations.replace(pointId, distance);
        } else
            relations.put(pointId,distance);
    }

    public int getDistanceTo(int pointId) {
        return relations.getOrDefault(pointId, -1);
    }

}
