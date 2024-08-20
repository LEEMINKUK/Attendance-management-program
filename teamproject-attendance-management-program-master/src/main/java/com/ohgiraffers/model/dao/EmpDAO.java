package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.AttendanceRecordDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;
import static com.ohgiraffers.common.JDBCTemplate.close;

public class EmpDAO {

    /* 본인 정보 조회(비밀번호로 본인확인) */////////////////////////
    public void retrievePersonalInfo() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO retriveEmp = new EmployeeDTO();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String personalPassword = null;


        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(EmployeeDTO.empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은 비밀번호 입니다. 다시 입력해주세요.");
            }
        }
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("retrieveByEmpPassword");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                retriveEmp.setEmpId(rset.getString("emp_id"));
                retriveEmp.setEmpPwd2(rset.getString("emp_password"));
                retriveEmp.setEmpName(rset.getString("emp_name"));
                retriveEmp.setPhone(rset.getString("phone"));
                retriveEmp.setEmail(rset.getString("email"));
                retriveEmp.setDepartmentCode(rset.getString("department_code"));
                retriveEmp.setPositionCode(rset.getString("position_code"));
                retriveEmp.setEmpIdentification(rset.getString("emp_identification"));
                retriveEmp.setPaymentCode(rset.getString("payment_code"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
            close(rset);
        }

        System.out.println(retriveEmp);


    }

    /* 본인 일별 근태조회(비밀번호로 본인 확인) *//////////////////////
    public List<Map<String, Object>> dailyPersonalAttendanceInfo(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String,Object>> dailyPersonalAttendanceInfoList = null;


        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String personalPassword = null;
        int selectDate;

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(EmployeeDTO.empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은 비밀번호 입니다. 다시 입력해주세요.");
            }
        }
        while (true) {
            System.out.print("조회하실 날짜를 입력하세요 ex)20240131 : ");
            String inputDate = sc.nextLine();

            try {
                selectDate = Integer.parseInt(inputDate);
                if(checkDate(selectDate)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 날짜입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식의 날짜를 입력해주세요.");
            }
        }

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("personalDailyAttendance");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            pstmt.setInt(2,selectDate);

            rset = pstmt.executeQuery();

            dailyPersonalAttendanceInfoList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> dailyPersonalAttendance = new LinkedHashMap<>();

                dailyPersonalAttendance.put("날짜 ", rset.getString("date_code"));
                dailyPersonalAttendance.put("ID ", rset.getString("emp_id"));
                dailyPersonalAttendance.put("이름 ", rset.getString("emp_name"));
                dailyPersonalAttendance.put("출근 ", rset.getString("arrival_at_work").charAt(0));
                dailyPersonalAttendance.put("퇴근 ", rset.getString("leave_work").charAt(0));
                dailyPersonalAttendance.put("지각 ", rset.getString("late_work").charAt(0));
                dailyPersonalAttendance.put("휴가 ", rset.getString("vacation").charAt(0));
                dailyPersonalAttendance.put("결근 ", rset.getString("day_off").charAt(0));

                dailyPersonalAttendanceInfoList.add(dailyPersonalAttendance);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
        return dailyPersonalAttendanceInfoList;

    }

    /* 본인 기간별 근태조회(비밀번호로 본인 확인) *////////////////////////////
    public List<Map<String, Object>> periodPersonalAttendanceInfo(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String,Object>> periodPersonalAttendanceInfoList = null;


        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String personalPassword = null;
        int selectFirstDate;
        int selectSecondDate;

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();
            if (personalPassword.equals(EmployeeDTO.empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은 비밀번호 입니다. 다시 입력해주세요.");
            }
        }

        System.out.print("조회를 시작할 날짜를 입력하세요 ex) 20240101 : ");
        while (!sc.hasNextInt()) {
            System.out.println("유효하지 않은 입력입니다.");
            System.out.print("숫자 형식의 날짜를 입력하세요 ex) 20240101 : ");
            sc.next();
        }
        selectFirstDate = sc.nextInt();

        System.out.print("마지막 날짜를 입력하세요 ex) 20240101 : ");
        while (!sc.hasNextInt()) {
            System.out.println("유효하지 않은 입력입니다.");
            System.out.print("숫자 형식의 날짜를 입력하세요 ex) 20240131 : ");
            sc.next();
        }
        selectSecondDate = sc.nextInt();


        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("personalPeriodAttendance");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            pstmt.setInt(2,selectFirstDate);
            pstmt.setInt(3,selectSecondDate);

            rset = pstmt.executeQuery();

            periodPersonalAttendanceInfoList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> periodPersonalAttendance = new LinkedHashMap<>();

                periodPersonalAttendance.put("날짜 ", rset.getString("date_code"));
                periodPersonalAttendance.put("ID ", rset.getString("emp_id"));
                periodPersonalAttendance.put("이름 ", rset.getString("emp_name"));
                periodPersonalAttendance.put("출근 ", rset.getString("arrival_at_work").charAt(0));
                periodPersonalAttendance.put("퇴근 ", rset.getString("leave_work").charAt(0));
                periodPersonalAttendance.put("지각 ", rset.getString("late_work").charAt(0));
                periodPersonalAttendance.put("휴가 ", rset.getString("vacation").charAt(0));
                periodPersonalAttendance.put("결근 ", rset.getString("day_off").charAt(0));

                periodPersonalAttendanceInfoList.add(periodPersonalAttendance);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }
        return periodPersonalAttendanceInfoList;

    }

    /* 본인 페널티 내역 조회 *///////////////////////////////////////
    public List<Map<String, Object>> personalPenaltyInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> personalPenaltyList = null;

        Scanner sc = new Scanner(System.in);
        String personalPassword = "";

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(EmployeeDTO.empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은  비밀번호 입니다. 다시 입력해주세요.");
            }
        }

        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("personalPenalty");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);

            rset = pstmt.executeQuery();

            personalPenaltyList = new ArrayList<>();


            while (rset.next()) {
                Map<String, Object> personalPenalty = new LinkedHashMap<>();

                personalPenalty.put("ID ", rset.getString("emp_id"));
                personalPenalty.put("이름 ", rset.getString("emp_name"));
                personalPenalty.put("날짜 ", rset.getString("date_code"));
                personalPenalty.put("페널티요소 ", rset.getString("penalty_factor"));
                personalPenalty.put("페널티점수 ", rset.getInt("penalty_score"));

                personalPenaltyList.add(personalPenalty);
            }
            if (personalPenaltyList.isEmpty()) {
                System.out.println("페널티 내역이 없습니다.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return personalPenaltyList;


    }

    /* 본인 한달 페널티 내역 조회 *////////////////////////////
    public List<Map<String, Object>> monthPersonalPenaltyInfo(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> monthPersonalPenaltyList = null;

        Scanner sc = new Scanner(System.in);
        String personalPassword = "";
        int selectMonth;

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(EmployeeDTO.empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은 비밀번호 입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.print("조회하실 월을 입력하세요 (1 ~ 12) : ");
            selectMonth = sc.nextInt();
            if (checkMonth(selectMonth)) {
                break;
            } else {
                System.out.println("유효하지 않은 월 입니다. 다시 입력해주세요.");
            }
        }

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("personalMonthPenalty");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, selectMonth);
            pstmt.setString(2,personalPassword);

            rset = pstmt.executeQuery();

            monthPersonalPenaltyList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> monthPersonal = new LinkedHashMap<>();

                monthPersonal.put("ID ", rset.getString("emp_id"));
                monthPersonal.put("이름 ", rset.getString("emp_name"));
                monthPersonal.put("월 ", rset.getString("month_name"));
                monthPersonal.put("보상여부 ", rset.getString("compensation_status"));
                monthPersonal.put("페널티여부 ", rset.getString("penalty_status"));
                monthPersonal.put("페널티합계 ", rset.getString("penalty_score_sum"));

                monthPersonalPenaltyList.add(monthPersonal);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return monthPersonalPenaltyList;

    }


}




