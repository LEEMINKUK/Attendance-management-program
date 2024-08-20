package com.ohgiraffers.model.dao;

import com.ohgiraffers.model.dto.AttendanceRecordDTO;
import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class AdminDAO {

    /* 관리자 - 1. 직원 정보 삽입 메서드(호출 & 작동 확인) */////////////////
    public void insertEmpInfo() {

        Scanner sc = new Scanner(System.in);
        EmployeeDTO newEmp = new EmployeeDTO();

        System.out.print("등록하실 직원의 ID를 입력하세요 : ");
        String empId = sc.nextLine();
        newEmp.setEmpId(empId);

        System.out.print("사용하실 Password를 입력하세요 : ");
        String empPwd = sc.nextLine();
        newEmp.setEmpPwd2(empPwd);

        System.out.print("등록하실 이름을 입력하세요 : ");
        String empName = sc.nextLine();
        newEmp.setEmpName(empName);

        System.out.print("등록하실 연락처를 입력하세요(하이픈 '-'포함) : ");
        String phone = sc.nextLine();
        newEmp.setPhone(phone);

        System.out.print("등록하실 email을 입력하세요 : ");
        String email = sc.nextLine();
        newEmp.setEmail(email);

        while (true) {
            System.out.println("[DP1 : 경영지원팀] / [DP2 : 영업팀] / [DP3 : 개발팀] / [DP4 : 마케팅팀] / [DP5 : 고객서비스팀]");
            System.out.print("부서코드를 입력하세요 : ");
            String departmentCode = sc.nextLine().toUpperCase();
            if (departmentCode.equals("DP1") || departmentCode.equals("DP2") ||
                    departmentCode.equals("DP3") || departmentCode.equals("DP4") ||
                    departmentCode.equals("DP5")) {

                switch (departmentCode) {
                    case "DP1":
                        newEmp.setDepartmentCode(departmentCode);
                        break;
                    case "DP2":
                        newEmp.setDepartmentCode(departmentCode);
                        break;
                    case "DP3":
                        newEmp.setDepartmentCode(departmentCode);
                        break;
                    case "DP4":
                        newEmp.setDepartmentCode(departmentCode);
                        break;
                    case "DP5":
                        newEmp.setDepartmentCode(departmentCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 부서코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[PS1 : 사장] / [PS2 : 부장] / [PS3 : 과장] / [PS4 : 대리] / [PS5 : 사원]");
            System.out.print("직책코드를 입력하세요 : ");
            String positionCode = sc.nextLine().toUpperCase();

            if (positionCode.equals("PS1") || positionCode.equals("PS2") ||
                    positionCode.equals("PS3") || positionCode.equals("PS4") ||
                    positionCode.equals("PS5")) {

                switch (positionCode) {
                    case "PS1":
                        newEmp.setPositionCode(positionCode);
                        break;
                    case "PS2":
                        newEmp.setPositionCode(positionCode);
                        break;
                    case "PS3":
                        newEmp.setPositionCode(positionCode);
                        break;
                    case "PS4":
                        newEmp.setPositionCode(positionCode);
                        break;
                    case "PS5":
                        newEmp.setPositionCode(positionCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 직책코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[ADMIN : 관리자] / [MGR : 중간관리자] / [EMP : 일반직원]");
            System.out.print("권한 코드를 입력하세요 : ");
            String empIdentification = sc.nextLine().toUpperCase();

            if (empIdentification.equals("ADMIN") || empIdentification.equals("MGR") ||
                    empIdentification.equals("EMP")) {

                switch (empIdentification) {
                    case "ADMIN":
                        newEmp.setEmpIdentification(empIdentification);
                        break;
                    case "MGR":
                        newEmp.setEmpIdentification(empIdentification);
                        break;
                    case "EMP":
                        newEmp.setEmpIdentification(empIdentification);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 권한코드입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.println("[PM1 : 6,000,000] / [PM2 : 5,000,000] / [PM3 : 4,000,000] / [PM4 : 3,200,000] / [PM5 : 2,500,000]");
            System.out.print("급여 코드를 입력하세요 : ");
            String paymentCode = sc.nextLine().toUpperCase();

            if (paymentCode.equals("PM1") || paymentCode.equals("PM2") ||
                    paymentCode.equals("PM3") || paymentCode.equals("PM4") ||
                    paymentCode.equals("PM5")) {

                switch (paymentCode) {
                    case "PM1":
                        newEmp.setPaymentCode(paymentCode);
                        break;
                    case "PM2":
                        newEmp.setPaymentCode(paymentCode);
                        break;
                    case "PM3":
                        newEmp.setPaymentCode(paymentCode);
                        break;
                    case "PM4":
                        newEmp.setPaymentCode(paymentCode);
                        break;
                    case "PM5":
                        newEmp.setPaymentCode(paymentCode);
                        break;
                }
                break;
            } else {
                System.out.println("잘못된 급여코드입니다. 다시 입력해주세요.");
            }
        }

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("insertNewEmp");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, newEmp.getEmpId());
            pstmt.setString(2, newEmp.getEmpPwd2());
            pstmt.setString(3, newEmp.getEmpName());
            pstmt.setString(4, newEmp.getPhone());
            pstmt.setString(5, newEmp.getEmail());
            pstmt.setString(6, newEmp.getDepartmentCode());
            pstmt.setString(7, newEmp.getPositionCode());
            pstmt.setString(8, newEmp.getEmpIdentification());
            pstmt.setString(9, newEmp.getPaymentCode());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("직원 등록 성공");
        } else {
            System.out.println("직원 등록 실패");
        }

    }

    /* 관리자 - 2-1 직원 정보 변경 메서드(호출 & 작동 확인)  /////// */
    public void changeEmpInfo() {
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        EmployeeDTO changeEmp = new EmployeeDTO();
        String empId = "";

        while (true) {
            System.out.print("정보를 변경하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();
            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }
        changeEmp.setEmpId(empId);

        System.out.println("원하시는 항목을 선택해주세요:");
        System.out.println("1. Password 변경");
        System.out.println("2. 이름 변경");
        System.out.println("3. 연락처 변경");
        System.out.println("4. email 변경");
        System.out.println("5. 부서코드 변경");
        System.out.println("6. 직책코드 변경");
        System.out.println("7. 권한 코드 변경");
        System.out.println("8. 급여 코드 변경");
        System.out.println("0. 선택창으로 ");

        System.out.print("선택 : ");
        while (!sc.hasNextInt()) {
            System.out.println("유효하지 않은 입력입니다.");
            System.out.print("0 ~ 9 중에 선택해주세요 : ");
            sc.next();
        }
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice) {
            case 1:
                System.out.print("변경하실 Password를 입력하세요 : ");
                String empPwd = sc.nextLine();
                if (!empPwd.isEmpty()) {
                    changeEmp.setEmpPwd2(empPwd);
                }
                break;
            case 2:
                System.out.print("변경하실 이름을 입력하세요 : ");
                String empName = sc.nextLine();
                if (!empName.isEmpty()) {
                    changeEmp.setEmpName(empName);
                }
                break;
            case 3:
                System.out.print("변경하실 연락처를 입력하세요(하이픈 '-'포함) : ");
                String phone = sc.nextLine();
                if (!phone.isEmpty()) {
                    changeEmp.setPhone(phone);
                }
                break;
            case 4:
                System.out.print("변경하실 email을 입력하세요 : ");
                String email = sc.nextLine();
                if (!email.isEmpty()) {
                    changeEmp.setEmail(email);
                }
                break;
            case 5:
                System.out.println("[DP1 : 경영지원팀] / [DP2 : 영업팀] / [DP3 : 개발팀] / [DP4 : 마케팅팀] / [DP5 : 고객서비스팀]");
                System.out.print("변경하실 부서코드를 입력하세요 : ");
                String departmentCode = sc.nextLine().toUpperCase();
                if (!departmentCode.isEmpty()) {
                    changeEmp.setDepartmentCode(departmentCode);
                }
                break;
            case 6:
                System.out.println("[PS1 : 사장] / [PS2 : 부장] / [PS3 : 과장] / [PS4 : 대리] / [PS5 : 사원]");
                System.out.print("변경하실 직책코드를 입력하세요 : ");
                String positionCode = sc.nextLine().toUpperCase();
                if (!positionCode.isEmpty()) {
                    changeEmp.setPositionCode(positionCode);
                }
                break;
            case 7:
                System.out.println("[ADMIN : 관리자] / [MGR : 중간관리자] / [EMP : 일반직원]");
                System.out.print("변경하실 권한 코드를 입력하세요 : ");
                String empIdentification = sc.nextLine().toUpperCase();
                if (!empIdentification.isEmpty()) {
                    changeEmp.setEmpIdentification(empIdentification);
                }
                break;
            case 8:
                System.out.println("[PM1 : 6,000,000] / [PM2 : 5,000,000] / [PM3 : 4,000,000] / [PM4 : 3,200,000] / [PM5 : 2,500,000]");
                System.out.print("변경하실 급여 코드를 입력하세요 : ");
                String paymentCode = sc.nextLine().toUpperCase();
                if (!paymentCode.isEmpty()) {
                    changeEmp.setPaymentCode(paymentCode);
                }
                break;
            default:
                System.out.println("잘못된 선택입니다.");
                break;
        }

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("changeEmpInfo");

            boolean isFirst = true;

            if (changeEmp.getEmpPwd2() != null) {
                query += (isFirst ? "" : ", ") + "emp_password = ?";
                isFirst = false;
            }
            if (changeEmp.getEmpName() != null) {
                query += (isFirst ? "" : ", ") + "emp_name = ?";
                isFirst = false;
            }
            if (changeEmp.getPhone() != null) {
                query += (isFirst ? "" : ", ") + "phone = ?";
                isFirst = false;
            }
            if (changeEmp.getEmail() != null) {
                query += (isFirst ? "" : ", ") + "email = ?";
                isFirst = false;
            }
            if (changeEmp.getDepartmentCode() != null) {
                query += (isFirst ? "" : ", ") + "department_code = ?";
                isFirst = false;
            }
            if (changeEmp.getPositionCode() != null) {
                query += (isFirst ? "" : ", ") + "position_code = ?";
                isFirst = false;
            }
            if (changeEmp.getEmpIdentification() != null) {
                query += (isFirst ? "" : ", ") + "emp_identification = ?";
                isFirst = false;
            }
            if (changeEmp.getPaymentCode() != null) {
                query += (isFirst ? "" : ", ") + "payment_code = ?";
                isFirst = false;
            }

            query += " WHERE emp_id = ?";

            pstmt = con.prepareStatement(query);


            int index = 1;
            if (changeEmp.getEmpPwd2() != null) {
                pstmt.setString(index++, changeEmp.getEmpPwd2());
            }
            if (changeEmp.getEmpName() != null) {
                pstmt.setString(index++, changeEmp.getEmpName());
            }
            if (changeEmp.getPhone() != null) {
                pstmt.setString(index++, changeEmp.getPhone());
            }
            if (changeEmp.getEmail() != null) {
                pstmt.setString(index++, changeEmp.getEmail());
            }
            if (changeEmp.getDepartmentCode() != null) {
                pstmt.setString(index++, changeEmp.getDepartmentCode());
            }
            if (changeEmp.getPositionCode() != null) {
                pstmt.setString(index++, changeEmp.getPositionCode());
            }
            if (changeEmp.getPaymentCode() != null) {
                pstmt.setString(index++, changeEmp.getPaymentCode());
            }

            pstmt.setString(index, changeEmp.getEmpId());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidPropertiesFormatException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("직원 변경 성공");
        } else {
            System.out.println("직원 변경 실패");
        }
    }

    /* 관리자 - 2-2 직원 근태 변경 메서드(호출 & 작동 확인) *//////////////////////
    public void changeEmpAttendance() {

        Scanner sc = new Scanner(System.in);
        AttendanceRecordDTO changeEmp = new AttendanceRecordDTO();
        Properties prop = new Properties();
        String empId = "";
        int selectDate;


        while (true) {

            System.out.print("변경하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }
        changeEmp.setEmpId(empId);

        while (true) {
            System.out.print("변경하실 날짜를 입력해 주세요. ex)20240131 : ");
            String inputDate = sc.nextLine();

            try {
                selectDate = Integer.parseInt(inputDate);
                if (checkDate(selectDate)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 날짜입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식의 날짜를 입력해주세요.");
            }
        }
        changeEmp.setDateCode(selectDate);

        while (true) {
            System.out.print("출근 여부 변경(Y / N) : ");
            String arrivalAtWork = sc.next().toUpperCase();
            if (arrivalAtWork.equals("Y") || arrivalAtWork.equals("N")) {
                changeEmp.setArrivalAtWork(arrivalAtWork);
            } else {
                System.out.println("Y / N 만 입력하세요.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("퇴근 여부 변경(Y / N) : ");
            String leaveWork = sc.next().toUpperCase();
            if (leaveWork.equals("Y") || leaveWork.equals("N")) {
                changeEmp.setLeaveWork(leaveWork);
            } else {
                System.out.println("Y / N 만 입력하세요.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("지각 여부 변경(Y / N) : ");
            String lateWork = sc.next().toUpperCase();
            if (lateWork.equals("Y") || lateWork.equals("N")) {
                changeEmp.setLateWork(lateWork);
            } else {
                System.out.println("Y / N 만 입력하세요.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("휴가 여부 변경(Y / N) : ");
            String vacation = sc.next().toUpperCase();
            if (vacation.equals("Y") || vacation.equals("N")) {
                changeEmp.setVacation(vacation);
            } else {
                System.out.println("Y / N 만 입력하세요.");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("결근 여부 변경(Y / N) : ");
            String dayOff = sc.next().toUpperCase();
            if (dayOff.equals("Y") || dayOff.equals("N")) {
                changeEmp.setDayOff(dayOff);
            } else {
                System.out.println("Y / N 만 입력하세요.");
                continue;
            }
            break;
        }

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("updateEmpAttendance");

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, changeEmp.getArrivalAtWork());
            pstmt.setString(2, changeEmp.getLeaveWork());
            pstmt.setString(3, changeEmp.getLateWork());
            pstmt.setString(4, changeEmp.getVacation());
            pstmt.setString(5, changeEmp.getDayOff());
            pstmt.setInt(6, changeEmp.getDateCode());
            pstmt.setString(7, changeEmp.getEmpId());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        if (result > 0) {
            System.out.println("근태 정보 변경 성공");
        } else {
            System.out.println("근태 정보 변경 실패");
        }


    }

    /* 관리자 - 3. 직원 정보 삭제 메서드(호출 & 작동 확인) *///////////
    public void deleteEmpInfo() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);
        String empId = "";

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("deleteEmp");

            while (true) {

                System.out.print("삭제하실 직원의 ID를 입력하세요 : ");
                empId = sc.nextLine();

                if (checkId(empId)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
                }
            }

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }

        if (result > 0) {
            System.out.println("계정 삭제 성공");
        } else {
            System.out.println("계정 삭제 실패");
        }

    }

    /* 관리자 - 4-1. 직원 정보 조회 메서드 (호출 & 작동 확인 완료) *////////////
    public void retrieveEmpInfo() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        EmployeeDTO retriveEmp = new EmployeeDTO();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String empId = null;


        while (true) {

            System.out.print("조회하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("retrieveByEmpId");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
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

    /* 관리자 - 4-1-1 직원 전체 정보 조회 메서드 *///////////
    public List<Map<String, Object>> allEmpInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> allEmpInfoList = null;

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));

            String query = prop.getProperty("allEmpInfo");

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();
            allEmpInfoList = new ArrayList<>();
            while (rset.next()) {
                Map<String, Object> allEmpInfo = new LinkedHashMap<>();

                allEmpInfo.put("ID ", rset.getString("emp_id"));
                allEmpInfo.put("PWD ", rset.getString("emp_password"));
                allEmpInfo.put("이름 ", rset.getString("emp_name"));
                allEmpInfo.put("연락처 ", rset.getString("phone"));
                allEmpInfo.put("이메일 ", rset.getString("email"));
                allEmpInfo.put("부서코드 ", rset.getString("department_code"));
                allEmpInfo.put("직책코드 ", rset.getString("position_code"));
                allEmpInfo.put("권한코드 ", rset.getString("emp_identification"));
                allEmpInfo.put("급여코드 ", rset.getString("payment_code"));

                allEmpInfoList.add(allEmpInfo);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return allEmpInfoList;
    }

    /* 관리자 - 4-2. 근태(개인) 조회 *////////////
    public void dailyAttendanceInfo() {

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        AttendanceRecordDTO retriveEmp = new AttendanceRecordDTO();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String empId = null;
        int selectDate;


        while (true) {

            System.out.print("조회하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }

        while (true) {
            System.out.print("조회하실 날짜를 입력하세요 ex)20240131 : ");
            String inputDate = sc.nextLine();

            try {
                selectDate = Integer.parseInt(inputDate);
                if (checkDate(selectDate)) {
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
            String query = prop.getProperty("dailyAttendance");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setInt(2, selectDate);

            rset = pstmt.executeQuery();

            if (rset.next()) {
                retriveEmp.setAttendanceCode(rset.getString("attendance_code"));
                retriveEmp.setDateCode(rset.getInt("date_code"));
                retriveEmp.setEmpId(rset.getString("emp_id"));
                retriveEmp.setArrivalAtWork(rset.getString("arrival_at_work"));
                retriveEmp.setLeaveWork(rset.getString("leave_work"));
                retriveEmp.setLateWork(rset.getString("late_work"));
                retriveEmp.setVacation(rset.getString("vacation"));
                retriveEmp.setDayOff(rset.getString("day_off"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        System.out.println(retriveEmp);

    }

    /* 관리자 - 4-3 해당 직원 기간별 근태 조회 *///////////
    public List<Map<String, Object>> byPeriodAttendanceInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> byPeriodInfoList = null;

        AttendanceRecordDTO retriveEmp = new AttendanceRecordDTO();

        Scanner sc = new Scanner(System.in);
        Properties prop = new Properties();
        String empId = null;
        int selectFirstDate;
        int selectSecondDate;

        while (true) {

            System.out.print("조회하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
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
            String query = prop.getProperty("byPeriodAttendance");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setInt(2, selectFirstDate);
            pstmt.setInt(3, selectSecondDate);

            rset = pstmt.executeQuery();
            byPeriodInfoList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> byPeriod = new LinkedHashMap<>();

                byPeriod.put("날짜 ", rset.getString("date_code"));
                byPeriod.put("ID ", rset.getString("emp_id"));
                byPeriod.put("이름 ", rset.getString("emp_name"));
                byPeriod.put("근태코드 ", rset.getString("attendance_code"));
                byPeriod.put("출근 ", rset.getString("arrival_at_work").charAt(0));
                byPeriod.put("퇴근 ", rset.getString("leave_work").charAt(0));
                byPeriod.put("지각 ", rset.getString("late_work").charAt(0));
                byPeriod.put("휴가 ", rset.getString("vacation").charAt(0));
                byPeriod.put("결근 ", rset.getString("day_off").charAt(0));

                byPeriodInfoList.add(byPeriod);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }


        return byPeriodInfoList;


    }

    /* 관리자 - 4-4 전체 직원 일별 근태 조회 *///////////////
    public List<Map<String, Object>> allEmpDailyAttendanceInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> allEmpAttendanceInfoList = null;

        Scanner sc = new Scanner(System.in);

        int selectDate;

        while (true) {
            System.out.print("조회하실 날짜를 입력하세요 ex)20240131 : ");
            String inputDate = sc.nextLine();
            try {
                selectDate = Integer.parseInt(inputDate);
                if (checkDate(selectDate)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 날짜입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식의 날짜를 입력해주세요.");
            }
        }

        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("oneDayAllEmpAttendance");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, selectDate);

            rset = pstmt.executeQuery();


            allEmpAttendanceInfoList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> oneDayAllEmp = new LinkedHashMap<>();

                oneDayAllEmp.put("날짜 ", rset.getInt("date_code"));
                oneDayAllEmp.put("ID ", rset.getString("emp_id"));
                oneDayAllEmp.put("이름 ", rset.getString("emp_name"));
                oneDayAllEmp.put("출근 ", rset.getString("arrival_at_work"));
                oneDayAllEmp.put("퇴근 ", rset.getString("leave_work"));
                oneDayAllEmp.put("지각 ", rset.getString("late_work"));
                oneDayAllEmp.put("휴가 ", rset.getString("vacation"));
                oneDayAllEmp.put("결근 ", rset.getString("day_off"));

                allEmpAttendanceInfoList.add(oneDayAllEmp);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }

        return allEmpAttendanceInfoList;


    }

    /* 관리자 - 5-1 해당 직원 페널티 조회 *//////////////
    public List<Map<String, Object>> oneEmpPenaltyInfo(Connection con) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> oneEmpPenaltyList = null;

        Scanner sc = new Scanner(System.in);
        String empId = "";

        while (true) {

            System.out.print("조회하실 직원의 ID를 입력하세요 : ");
            empId = sc.nextLine();

            if (checkId(empId)) {
                break;
            } else {
                System.out.println("유효하지 않은 ID 입니다. 다시 입력해주세요.");
            }
        }

        try {
            Properties prop = new Properties();
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("oneEmpPenalty");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);

            rset = pstmt.executeQuery();

            oneEmpPenaltyList = new ArrayList<>();


            while (rset.next()) {
                Map<String, Object> oneEmpPenalty = new LinkedHashMap<>();

                oneEmpPenalty.put("날짜 ", rset.getString("date_code"));
                oneEmpPenalty.put("ID ", rset.getString("emp_id"));
                oneEmpPenalty.put("이름 ", rset.getString("emp_name"));
                oneEmpPenalty.put("페널티요소 ", rset.getString("penalty_factor"));
                oneEmpPenalty.put("페널티점수 ", rset.getInt("penalty_score"));

                oneEmpPenaltyList.add(oneEmpPenalty);
            }
            if (oneEmpPenaltyList.isEmpty()) {
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

        return oneEmpPenaltyList;


    }

    /* 관리자 - 5-2 월(month_code)로 검색해서 해당 월의 전체 직원 페널티 여부 조회 *///////////////
    public List<Map<String, Object>> monthAllEmpPenaltyInfo(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        List<Map<String, Object>> monthAllEmpPenaltyList = null;

        Scanner sc = new Scanner(System.in);

        int selectMonth;
        while (true) {
            System.out.print("조회하실 월을 입력하세요 (1 ~ 12) : ");
            String inputDate = sc.nextLine();
            try {
                selectMonth = Integer.parseInt(inputDate);
                if (checkMonth(selectMonth)) {
                    break;
                } else {
                    System.out.println("유효하지 않은 날짜입니다. 다시 입력해주세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("숫자 형식의 날짜를 입력해주세요.");
            }
        }


        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
            String query = prop.getProperty("monthAllEmpPenalty");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, selectMonth);

            rset = pstmt.executeQuery();

            monthAllEmpPenaltyList = new ArrayList<>();

            while (rset.next()) {
                Map<String, Object> monthAllEmp = new LinkedHashMap<>();

                monthAllEmp.put("Month ", rset.getString("month_name"));
                monthAllEmp.put("ID ", rset.getString("emp_id"));
                monthAllEmp.put("이름 ", rset.getString("emp_name"));
                monthAllEmp.put("보상여부 ", rset.getString("compensation_status"));
                monthAllEmp.put("페널티여부 ", rset.getString("penalty_status"));
                monthAllEmp.put("페널티합계 ", rset.getString("penalty_score_sum"));

                monthAllEmpPenaltyList.add(monthAllEmp);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(rset);
        }
        return monthAllEmpPenaltyList;

    }

}


