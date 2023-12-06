package co.wedevx.digitalbank.automation.ui.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Connection;

import static co.wedevx.digitalbank.automation.ui.utils.ConfigReader.getPropertiesValue;

public class DBUtils {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    // method to establish connection with the db
    public static void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    getPropertiesValue("digitalbank.db.url"),
                    getPropertiesValue("digitalbank.db.username"),
                    getPropertiesValue("digitalbank.db.password"));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    // a method that can dynamically send select statements
    // and returns a list of map of all columns
    public static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery) {
        List<Map<String, Object>> dbResultList = new ArrayList<>();
        try {
            if (connection == null || connection.isClosed()) {
                establishConnection();
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            // getMetaData method returns info about your info.
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            while (resultSet.next()) {
                Map<String, Object> rowMap = new HashMap<>();
                for (int col = 1; col <= columnCount; col++) {
                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
                }
                dbResultList.add(rowMap);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dbResultList;
    }
    // create a method that inserts and updates into db
    // return the number of rows updated or 0 when action is not taken
    // update query
    public static int runSQLUpdateQuery(String sqlQuery) {
        int rowsAffected = 0;
        try {
            if (connection == null || connection.isClosed()) {
                establishConnection();
            }
            statement = connection.createStatement();
            rowsAffected = statement.executeUpdate(sqlQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowsAffected;
    }
    // close connection
    public static void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }







//   private  static  Connection connection = null;
//   private  static  Statement statement = null;
//   private  static  ResultSet resultSet = null;
//
//
//    //establish connection
//    public static void establishConnection() {
//
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(getPropertiesValue("digitalbank.db.url"),
//                                        getPropertiesValue("digitalbank.db.username"),
//                                         getPropertiesValue("digitalbank.db.password"));
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //method that can dynamically sed select statements
//    public  static List<Map<String, Object>> runSQLSelectQuery(String sqlQuery){
//
//        List<Map<String, Object>> dbresultList = new ArrayList<>();
//
//        try {
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(sqlQuery);
//
//            ResultSetMetaData resultSetMetaData = resultSet.getMetaData(); // return info
//            int columnCount = resultSetMetaData.getColumnCount();
//
//            while(resultSet.next()){
//                Map<String, Object> rowMap = new HashMap<>();
//                for(int col = 1; col < columnCount; col++){
//                    rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));
//                }
//                dbresultList.add(rowMap);
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return dbresultList;
//    }
//
//    //update method
//    public static  void runSQLUpdateQuery(String sqlQuery) {
//
//        int rowsUpdated = 0;
//        try {
//            statement  = connection.createStatement();
//            rowsUpdated = statement.executeUpdate(sqlQuery);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    //close connection
//
//    public static  void  closeConnection() {
//
//        try{
//            if(resultSet != null) {
//                resultSet.close();
//            }
//            if(statement != null) {
//                statement.close();
//            }
//            if(connection != null) {
//                connection.close();
//            }
//         } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
