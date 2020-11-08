package com.sigasauer.dbbest.task.controller;

import com.sigasauer.dbbest.task.entity.Pipeline;
import com.sigasauer.dbbest.task.entity.Point;
import com.sigasauer.dbbest.task.entity.Route;

import java.util.*;


public class RouteController {
    private Map<Integer, Point> points;

    public RouteController(List<Pipeline> pipelines){
        this.points = new HashMap<>();

        for (Pipeline pl : pipelines) {
            if(!points.containsKey(pl.getX())) points.put(pl.getX(), new Point(pl.getX()));
            if(!points.containsKey(pl.getY())) points.put(pl.getY(), new Point(pl.getY()));
        }

        for (Pipeline pl: pipelines) {
            points.get(pl.getX()).addRelation(points.get(pl.getY()).getId(), pl.getDistance());
            //points.get(pl.getY()).addRelation(points.get(pl.getX()), pl.getDistance());
        }

        points.values().forEach(this::deepCalculation);
    }

    private void deepCalculation(Point point) {
        if(!point.getRelations().isEmpty()) for (Integer relPointId : point.getRelations().keySet()) {
            Point relPoint = points.get(relPointId);
            if(!relPoint.getRelations().isEmpty()) for (Integer deepPointId: relPoint.getRelations().keySet()) {
                int deepDistance = relPoint.getRelations().get(deepPointId);
                int relDistance = point.getRelations().get(relPointId);
                int nDistance =  deepDistance + relDistance;
                point.addRelation(deepPointId, nDistance);
            }
        }
    }

    public List<Integer> calculateRoutes(List<Route> routes) {
        List<Integer> res = new ArrayList<>();
        for (Route rt : routes) {
            int a = rt.getPointA();
            int b = rt.getPointB();
            if(points.containsKey(a) && points.containsKey(b)) {
                res.add(points.get(a).getDistanceTo(b));
            }
        }
        return res;
    }

    public List<Point> getPoints() {
        return List.copyOf(points.values());
    }



}
