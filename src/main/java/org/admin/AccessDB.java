package org.admin;

import java.sql.*;

public class AccessDB {
    private static AccessDB instance = null;
    private Connection connection;

    private AccessDB(){
        connect();
    }

    public static AccessDB getInstance(){
        if(instance == null){
            instance = new AccessDB();
        }
        return instance;
    }

    private void connect(){
        try{
            String url = "jdbc:postgresql://localhost:5432/useradd";
            String user = "test";
            String password = "test";

            connection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void select(SelectQuery selectQuery){
        String query = selectQuery.buildQuery();
        System.out.println(query);
        try{
            ResultSet resultSet = QueryHelper.prepareStatement(connection, query, selectQuery.getConditionsValues());

            QueryHelper.printResultSet(resultSet, selectQuery.getColumns());

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }

    public void update(UpdateQuery updateQuery) {
        String query = updateQuery.buildQuery();
        System.out.println(query);
        try{
            ResultSet resultSet = QueryHelper.prepareStatement(connection, query, updateQuery.getConditionsValues());

        } catch (SQLException e){
            System.err.println(e.getMessage());
        }
    }


}
