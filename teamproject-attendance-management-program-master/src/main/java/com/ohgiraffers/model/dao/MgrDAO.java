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
import static com.ohgiraffers.model.dto.EmployeeDTO.empPwd;

public class MgrDAO {


    /* 1. 직원정보조회(부서인원전체) *//////////////////////////////////////
    public void departmentEmpInfo() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        String personalPassword = "";
        Scanner sc = new Scanner(System.in);

        List<EmployeeDTO> departmentEmpList = new ArrayList<>();

        try {

            while (true) {

                System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
                personalPassword = sc.nextLine();

                if (personalPassword.equals(empPwd)) {
                    break;
                } else {
                    System.out.println("유효하지 않은  비밀번호 입니다. 다시 입력해주세요.");
                }
            }
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));

            /* 위에서 입력한 Password 로 중간관리자 권한 코드 확인*/
            String query = prop.getProperty("checkLoginMgr");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                String mgrIdentification = rset.getString("emp_identification");
                if (!mgrIdentification.equals("MGR")) {
                    System.out.println("MGR 계정이 아닙니다.");
                    departmentEmpInfo();
                    return;
                }
            }

            String checkMgrDepartment = prop.getProperty("checkMgrDepartment");
            pstmt = con.prepareStatement(checkMgrDepartment);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                String mgrDepartmentCode = rset.getString("department_code");

                query = prop.getProperty("departmentAllEmp");
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, mgrDepartmentCode);
                rset = pstmt.executeQuery();


                while (rset.next()) {

                    EmployeeDTO departAllEmp = new EmployeeDTO();

                    departAllEmp.setEmpId(rset.getString("emp_id"));
                    departAllEmp.setEmpPwd2(rset.getString("emp_password"));
                    departAllEmp.setEmpName(rset.getString("emp_name"));
                    departAllEmp.setPhone(rset.getString("phone"));
                    departAllEmp.setEmail(rset.getString("email"));
                    departAllEmp.setDepartmentCode(rset.getString("department_code"));
                    departAllEmp.setPositionCode(rset.getString("position_code"));
                    departAllEmp.setEmpIdentification(rset.getString("emp_identification"));
                    departAllEmp.setPaymentCode(rset.getString("payment_code"));

                    departmentEmpList.add(departAllEmp);
                }
            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        for (EmployeeDTO departmentAllEmp : departmentEmpList) {
            System.out.println(departmentAllEmp);
        }

    }

    /* 2-1 일별근태기록 조회(부서인원만)///////////////////////////
           (특정 날짜 조회 시 부서 전체 근태기록 조회) */
    public List<Map<String, Object>> departmentDailyAttendance(Connection con) {


        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        String personalPassword = "";
        int selectDate;
        List<Map<String, Object>> departmentEmpAttendanceList = null;
        Scanner sc = new Scanner(System.in);


        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은  비밀번호 입니다. 다시 입력해주세요.");
            }
        }


        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));

            /* 위에서 입력한 ID로 중간관리자 권한 코드 확인*/
            String query = prop.getProperty("checkLoginMgr");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                String mgrIdentification = rset.getString("emp_identification");
                if (!mgrIdentification.equals("MGR")) {
                    System.out.println("MGR 계정이 아닙니다.");
                    return departmentDailyAttendance(con);
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


            String checkMgrDepartment = prop.getProperty("checkMgrDepartment");
            pstmt = con.prepareStatement(checkMgrDepartment);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                String mgrDepartmentCode = rset.getString("department_code");

                query = prop.getProperty("departmentAllEmpDailyAttendance");
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, mgrDepartmentCode);
                pstmt.setInt(2, selectDate);
                rset = pstmt.executeQuery();

                departmentEmpAttendanceList = new ArrayList<>();

                while (rset.next()) {

                    Map<String, Object> depaDailyAtt = new LinkedHashMap<>();

                    depaDailyAtt.put("날짜 ", rset.getInt("date_code"));
                    depaDailyAtt.put("ID ", rset.getString("emp_id"));
                    depaDailyAtt.put("이름 ", rset.getString("emp_name"));
                    depaDailyAtt.put("부서코드 ", rset.getString("department_code"));
                    depaDailyAtt.put("출근여부 ", rset.getString("arrival_at_work"));
                    depaDailyAtt.put("퇴근여부 ", rset.getString("leave_work"));
                    depaDailyAtt.put("지각여부 ", rset.getString("late_work"));
                    depaDailyAtt.put("휴가여부 ", rset.getString("vacation"));
                    depaDailyAtt.put("결근여부 ", rset.getString("day_off"));

                    departmentEmpAttendanceList.add(depaDailyAtt);

                }
            } else {

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return departmentEmpAttendanceList;

    }

    /* 2-2 기간별근태기록 조회(부서인원만)
     *      (시작일, 마지막일 조회 시 부서 전체 근태기록 조회) *///////////////////////////
    public List<Map<String, Object>> departmentPeriodAttendance(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        String personalPassword = "";
        int selectFirstDate;
        int selectSecondDate;

        List<Map<String, Object>> dpaList = null;

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은  비밀번호 입니다. 다시 입력해주세요.");
            }
        }

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkLoginMgr");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();


            if (rset.next()) {
                String mgrIdentification = rset.getString("emp_identification");
                if (!mgrIdentification.equals("MGR")) {
                    System.out.println("MGR 계정이 아닙니다.");
                    return departmentPeriodAttendance(con);
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

            String checkMgrDepartment = prop.getProperty("checkMgrDepartment");
            pstmt = con.prepareStatement(checkMgrDepartment);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                String mgrDepartmentCode = rset.getString("department_code");

                String departmentPeriodAttendance = prop.getProperty("departmentAllEmpPeriodAttendance");

                pstmt = con.prepareStatement(departmentPeriodAttendance);

                pstmt.setString(1, mgrDepartmentCode);
                pstmt.setInt(2, selectFirstDate);
                pstmt.setInt(3, selectSecondDate);
                rset = pstmt.executeQuery();

                dpaList = new ArrayList<>();

                while (rset.next()) {
                    Map<String, Object> depaPeriodAtt = new LinkedHashMap<>();

                    depaPeriodAtt.put("날짜 ", rset.getInt("date_code"));
                    depaPeriodAtt.put("ID ", rset.getString("emp_id"));
                    depaPeriodAtt.put("이름 ", rset.getString("emp_name"));
                    depaPeriodAtt.put("부서코드 ", rset.getString("department_code"));
                    depaPeriodAtt.put("출근여부 ", rset.getString("arrival_at_work"));
                    depaPeriodAtt.put("퇴근여부 ", rset.getString("leave_work"));
                    depaPeriodAtt.put("지각여부 ", rset.getString("late_work"));
                    depaPeriodAtt.put("휴가여부 ", rset.getString("vacation"));
                    depaPeriodAtt.put("결근여부 ", rset.getString("day_off"));

                    dpaList.add(depaPeriodAtt);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(rset);
        }
        return dpaList;
    }

    /* 3. 월간 보상여부 조회(부서인원만)*////////////////////////
    public List<Map<String, Object>> departmentEmpPenaltyInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        String personalPassword = "";
        int selectMonth;

        List<Map<String, Object>> departmentEmpPenaltyList = null;

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("본인 확인을 위해 비밀번호를 입력하세요 : ");
            personalPassword = sc.nextLine();

            if (personalPassword.equals(empPwd)) {
                break;
            } else {
                System.out.println("유효하지 않은  비밀번호 입니다. 다시 입력해주세요.");
            }
        }

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("checkLoginMgr");
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                String mgrIdentification = rset.getString("emp_identification");
                if(!mgrIdentification.equals("MGR")) {
                    System.out.println("MGR 계정이 아닙니다.");
                    return departmentEmpPenaltyInfo(con);
                }
            }

            while (true) {
                System.out.print("조회하실 월을 입력하세요(1 ~ 12) : ");
                selectMonth = sc.nextInt();
                if(checkMonth(selectMonth)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 월 입니다. 다시 입력해주세요.");
                }
            }

            String checkMgrDepartment = prop.getProperty("checkMgrDepartment");
            pstmt = con.prepareStatement(checkMgrDepartment);
            pstmt.setString(1, personalPassword);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                String mgrDepartmentCode = rset.getString("department_code");
                String departmentEmpPenalty = prop.getProperty("departmentEmpMonthPenalty");

                pstmt = con.prepareStatement(departmentEmpPenalty);

                pstmt.setString(1, mgrDepartmentCode);
                pstmt.setInt(2,selectMonth);
                rset = pstmt.executeQuery();

                departmentEmpPenaltyList = new ArrayList<>();

                while (rset.next()) {
                    Map<String, Object> depaMonthPenalty = new LinkedHashMap<>();

                    depaMonthPenalty.put("Month ", rset.getInt("month_code") + "월");
                    depaMonthPenalty.put("ID ", rset.getString("emp_id"));
                    depaMonthPenalty.put("이름 ", rset.getString("emp_name"));
                    depaMonthPenalty.put("부서코드 ", rset.getString("department_code"));
                    depaMonthPenalty.put("보상여부 ", rset.getString("compensation_status"));
                    depaMonthPenalty.put("페널티여부 ", rset.getString("penalty_status"));
                    depaMonthPenalty.put("페널티합계 ", rset.getInt("penalty_score_sum"));

                    departmentEmpPenaltyList.add(depaMonthPenalty);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(rset);
        }

        return departmentEmpPenaltyList;

    }

}
