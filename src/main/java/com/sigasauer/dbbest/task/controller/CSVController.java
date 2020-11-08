package com.sigasauer.dbbest.task.controller;

import com.sigasauer.dbbest.task.entity.Pipeline;
import com.sigasauer.dbbest.task.entity.Point;
import com.sigasauer.dbbest.task.entity.Route;

import java.io.*;
import java.util.*;

public class CSVController {
    private final String pipelineFilename;
    private final String routeTaskFilename;

    private H2Controller h2Controller;

    public CSVController(String pipelineFilename, String routeTaskFilename) {
        this.pipelineFilename = pipelineFilename;
        this.routeTaskFilename = routeTaskFilename;
    }

    public CSVController(String pipelineFilename, String routeTaskFilename, H2Controller h2Controller) {
        this.pipelineFilename = pipelineFilename;
        this.routeTaskFilename = routeTaskFilename;
        this.h2Controller = h2Controller;
    }

    public List<Pipeline> readPipeline() {
        File file = new File(pipelineFilename);
        List<Pipeline> pipelines = new ArrayList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                if(line.matches("[0-9;]+")) {
                    String[] values = line.split(";");
                    Pipeline p = new Pipeline(Integer.parseInt(values[0]),
                            Integer.parseInt(values[1]), Integer.parseInt(values[2]));
                    pipelines.add(p);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        if(h2Controller != null)  h2Controller.addAllPipelines(pipelines);
        return pipelines;
    }

    public List<Route> readRouteTask() {
        File file = new File(routeTaskFilename);
        List<Route> tasks = new ArrayList<>();
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                if(line.matches("[0-9;]+")) {
                    String[] values = line.split(";");
                    Route rt = new Route(Integer.parseInt(values[0]),
                            Integer.parseInt(values[1]));
                    tasks.add(rt);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void write(List<Integer> distances, List<Point> points) {
        String dir = pipelineFilename.substring(0, pipelineFilename.contains("\\") ?
                pipelineFilename.lastIndexOf("\\") : pipelineFilename.lastIndexOf("/"));
        try {
            File resFile = new File(dir,"result.csv");
            FileWriter fw = new FileWriter(resFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("ROUTE EXISTS;MIN LENGTH\n");

            for (int d : distances) {
                bw.write(d >= 0 ? "TRUE;" + d+"\n" : "FALSE;\n");
            }
            bw.close();

            File pFile = new File(dir, "points.csv");
            fw = new FileWriter(pFile, true);
            bw = new BufferedWriter(fw);
            bw.write("POINT\n");

            for (Point p : points) {
                bw.write(p.getId()+"\n");
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isFilePresent(String path) {
        return new File(path).isFile() && path.endsWith(".csv");
    }

}
