package com.ohgiraffers.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

public class JDBCTemplate {

    public static Connection getConnection() {

        Connection con = null;

        Properties prop = new Properties();

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/config/connection-info.properties"));

            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");

            Class.forName(driver);

            con = DriverManager.getConnection(url, prop);

//            con.setAutoCommit(false);   // mySQL 자동으로 커밋 방지

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;

    }

    /* 자원 반납(종료) 메서드 작성(오버로드)*/
    public static void close(Connection con) {
        try {
            if (con != null & !con.isClosed()) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement stmt) {
        try {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rset) {
        try {
            if (rset != null && !rset.isClosed()) {
                rset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /* 입력한 ID가 맞는지 확인하는 메서드 */
    public static boolean checkId(String empId) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean checkId = false;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkId");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();
            checkId = rset.next();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
        return checkId;
    }
    /* 입력한 비밀번호가 맞는지 확인하는 메서드 */
    public static boolean checkPassword(String PersonalPassword) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean checkPassword = false;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkPwd");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, PersonalPassword);

            rset = pstmt.executeQuery();
            checkPassword = rset.next();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
        return checkPassword;
    }

    /* 입력한 날짜코드가 맞는지 확인하는 메서드 */
    public static boolean checkDate(int dateCode) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean checkDate = false;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkDate");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, dateCode);

            rset = pstmt.executeQuery();
            checkDate = rset.next();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
        return checkDate;
    }

    public static boolean checkMonth(int monthCode) {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        boolean checkMonth = false;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkMonth");
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, monthCode);

            rset = pstmt.executeQuery();
            checkMonth = rset.next();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }
        return checkMonth;
    }


}
