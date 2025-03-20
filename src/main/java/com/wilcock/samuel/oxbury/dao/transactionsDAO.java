package com.wilcock.samuel.oxbury.dao;

import com.wilcock.samuel.oxbury.model.dataModel;
import com.wilcock.samuel.oxbury.model.totalQuantModel;

import java.sql.*;
import java.util.ArrayList;

public class transactionsDAO {
    dataModel oneTransacaction = null;
    totalQuantModel oneQuant = null;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement prepstmt = null;
    String user = "sa";
    String password = "password";
    String url = "jdbc:h2:mem:dcbapp";

    private static final String SELECT_ALL_TRANSACTIONS = "select * from transactions";
    private static final String SELECT_BY_TRANSACTIONS = "select product_code, sum(transaction_quantity) AS total_quantity"
            +" FROM transactions WHERE manufacturer=? AND retailer_id=? AND product_code IN (?,?,?,?) AND transaction_date > ? AND transaction_date < ? "
            +"GROUP BY product_code";

    private static final String INSERT_TRANSACTION = "INSERT INTO transactions"
        + "(manufacturer, retailer_id, product_code, transaction_id, transaction_date, transaction_quantity, transaction_value)"
            +"VALUES (?,?,?,?,?,?,?)";

    private static final String DELETE_TRANSACTION = "DELETE FROM transactions WHERE "
            + "manufacturer=? AND retailer_id=? AND product_code=? AND transaction_id=?";

    public transactionsDAO() {
    }

    private void openConnection() {
        // loading jdbc driver for mysql
        try {
            Class.forName("org.h2.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        // connecting to database
        try {
            // connection string for demos database, username demos, password demos
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conn is good");
            stmt = conn.createStatement();
        } catch (SQLException se) {
            System.out.println(se);
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    private void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private dataModel getNextSweep(ResultSet rs) {
        dataModel thisTransaction = null;
        try {
            thisTransaction = new dataModel(rs.getString("manufacturer"),
                    rs.getString("retailer_id"),
                    rs.getString("product_code"),
                    rs.getString("transaction_id"),
                    rs.getDate("transaction_date"),
                    rs.getInt("transaction_quantity"),
                    rs.getInt("transaction_value"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thisTransaction;
    }

    private totalQuantModel getProductCodeAndQuant(ResultSet rs) {
        totalQuantModel thisTransaction = null;
        try {
            thisTransaction = new totalQuantModel(rs.getString("product_code"),
                    rs.getInt("total_quantity"));
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return thisTransaction;
    }

    public ArrayList<dataModel> getAllTransactions() {

        ArrayList<dataModel> allTransactions = new ArrayList<dataModel>();
        openConnection();
        oneTransacaction = null;
        // Create select statement and execute it
        try {
            prepstmt = conn.prepareStatement(SELECT_ALL_TRANSACTIONS);
            ResultSet rs1 = prepstmt.executeQuery();
            // Retrieve the results
            while (rs1.next()) {
                oneTransacaction = getNextSweep(rs1);
                allTransactions.add(oneTransacaction);
            }

            stmt.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return allTransactions;
    }

    public int insertTransactionDetails(dataModel insertTransaction) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSACTION)) {
            preparedStatement.setString(1, insertTransaction.getManufacturer());
            preparedStatement.setString(2, insertTransaction.getRetailer());
            preparedStatement.setString(3, insertTransaction.getProductCode());
            preparedStatement.setString(4, insertTransaction.getTransactionID());
            preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.setFloat(6, insertTransaction.getQuantity());
            preparedStatement.setFloat(7, insertTransaction.getValue());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int deleteTransactionDetails(dataModel insertTransaction) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRANSACTION)) {
            preparedStatement.setString(1, insertTransaction.getManufacturer());
            preparedStatement.setString(2, insertTransaction.getRetailer());
            preparedStatement.setString(3, insertTransaction.getProductCode());
            preparedStatement.setString(4, insertTransaction.getTransactionID());
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<totalQuantModel> getByDetails(String manufacturer, String retailer, String[] productCode, String fromDate, String toDate) {
        ArrayList<totalQuantModel> allTransactions = new ArrayList<totalQuantModel>();
        openConnection();
        oneQuant = null;
        // Create select statement and execute it
        try {
            prepstmt = conn.prepareStatement(SELECT_BY_TRANSACTIONS);
            prepstmt.setString(1, manufacturer);
            prepstmt.setString(2, retailer);
            prepstmt.setString(3, productCode[0]);
            prepstmt.setString(4, productCode[1]);
            prepstmt.setString(5, productCode[2]);
            prepstmt.setString(6, productCode[3]);
            prepstmt.setString(7, fromDate);
            prepstmt.setString(8, toDate);

            ResultSet rs1 = prepstmt.executeQuery();
            // Retrieve the results
            while (rs1.next()) {
                oneQuant = getProductCodeAndQuant(rs1);
                allTransactions.add(oneQuant);
            }

            stmt.close();
            closeConnection();
        } catch (SQLException se) {
            System.out.println(se);
        }

        return allTransactions;
    }
}
