//Project: Anbindung_DB_SCHLOFFER
//Author: Schloffer Lisa
//Date: 22.10.2024

import java.sql.*;

public class Main {

    private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) throws Exception{
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/itl1_2_java","root","");


            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            resultSet = statement
                    .executeQuery("select * from emp");
            writeResultSet(resultSet);

            resultSet = statement.executeQuery("select * from dept");
            // writeMetaData(resultSet);



        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }


    }

    private static void writeMetaData(ResultSet resultSet) throws SQLException {
        //  Now get some metadata from the database
        // Result set get the result of the SQL query

        System.out.println("The columns in the table are: ");

        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
        }
    }

    private static void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            String empno = resultSet.getString("empno");
            String ename = resultSet.getString("ename");
            String job = resultSet.getString("job");
            String mgr = resultSet.getString("mgr");
            String sal = resultSet.getString("sal");
            String deptno = resultSet.getString("deptno");
            String hiredate = resultSet.getString("hiredate");
            System.out.println("EmpNo: " + empno);
            System.out.println("EmpName: " + ename);
            System.out.println("Job: " + job);
            System.out.println("Manager: " + mgr);
            System.out.println("Salary: " + sal);
            System.out.println("Department: " +  deptno);
            System.out.println("Hiredate: " + hiredate);
            System.out.println("------------------");
        }
    }

    // You need to close the resultSet
    private static void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
