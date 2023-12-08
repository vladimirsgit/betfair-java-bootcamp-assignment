package org.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryHelper {
    public static void addParameterMarkerForConditions(String[][] conditions){

        for (String[] condition: conditions) {
            for(int i = 0; i < condition.length; i++){
                condition[i] = condition[i] + "=?";
            }
        }
    }
    public static void addParameterMarkerForColumns(String[] columns){
        for(int i = 0; i < columns.length; i++){
            columns[i] = columns[i] + "=?";
        }
    }
    public static String createWhereClause(String[][] conditions){
        String[] joinedArray = new String[conditions.length];
        for(int i = 0; i < conditions.length; i++){
            joinedArray[i] = "(" + String.join(" AND ", conditions[i]) + ")";
        }

        return " WHERE " + String.join(" OR ", joinedArray);
    }

    public static ResultSet prepareStatement(Connection connection, String query, String[] conditionsValues) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        if(conditionsValues != null){
            int index = 1;
            for(int i = 0; i < conditionsValues.length; i++){
                preparedStatement.setString(i+1, conditionsValues[i]);
            }
        }
        return preparedStatement.executeQuery();
    }

    public static void printResultSet(ResultSet resultSet, String[] columns) throws SQLException {
        int index = 0;
        while(resultSet.next()){
            for(String column: columns){
                System.out.println(resultSet.getString(column));
            }
        }
    }
}
