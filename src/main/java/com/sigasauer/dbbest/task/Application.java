package com.sigasauer.dbbest.task;

import com.sigasauer.dbbest.task.controller.CSVController;
import com.sigasauer.dbbest.task.controller.H2Controller;
import com.sigasauer.dbbest.task.controller.RouteController;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        System.out.println("Hi, this is Water Pipeline CLI Application!");
        cli();
    }

    private static void cli() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter absolute path of pipelines csv file");
        String pipelinePath = sc.nextLine();
        System.out.println("Enter absolute path of route task csv file");
        String routetaskPath = sc.nextLine();
        if (!CSVController.isFilePresent(pipelinePath) || !CSVController.isFilePresent(routetaskPath)) {
            System.out.println("Wrong path, try again");
            cli();
        }

        CSVController csvc = new CSVController(pipelinePath, routetaskPath, new H2Controller());
        RouteController rc = new RouteController(csvc.readPipeline());
        csvc.write(rc.calculateRoutes(csvc.readRouteTask()),rc.getPoints());

        System.out.println("Calculation result you can see in file \"result.csv\" " +
                "that located in task files directory\nBye!");


    }
}
