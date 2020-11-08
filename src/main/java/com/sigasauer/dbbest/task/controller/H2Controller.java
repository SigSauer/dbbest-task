package com.sigasauer.dbbest.task.controller;

import com.sigasauer.dbbest.task.entity.Pipeline;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class H2Controller {
    private  final String URL = "jdbc:h2:mem:task";
    private  final String CREATE_PIPELINES = "CREATE TABLE PIPELINES (ID INTEGER PRIMARY KEY, " +
            "X INTEGER, Y INTEGER, LENGTH INTEGER)";
    private  final String ADD_PIPELINE = "INSERT INTO PIPELINES VALUES(?,?,?,?)";
    private  final String GET_PIPELINE = "SELECT * FROM PIPELINES";

    private  short last = 1;
    private  Connection connection;

    public H2Controller(){
        try {
            connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement();
            statement.execute(CREATE_PIPELINES);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addAllPipelines(List<Pipeline> pls) {
        pls.forEach(this::addPipeline);
    }

    public void addPipeline(Pipeline p) {
        try {
            PreparedStatement statement = connection.prepareStatement(ADD_PIPELINE);
            statement.setInt(1,last);

            statement.setInt(2,p.getX());
            statement.setInt(3,p.getY());
            statement.setInt(4,p.getDistance());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        last++;
    }

    public List<Pipeline> getPipelines() {
        List<Pipeline> pipelines = new ArrayList<>();
        try (Connection db = DriverManager.getConnection(URL)) {
            try (PreparedStatement query =
                         db.prepareStatement(GET_PIPELINE)) {
                ResultSet rs = query.executeQuery();
                while (rs.next()) {
                    Pipeline p = new Pipeline(rs.getInt(2),
                            rs.getInt(3),
                            rs.getInt(4));
                    pipelines.add(p);
                }
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pipelines;
    }
}

