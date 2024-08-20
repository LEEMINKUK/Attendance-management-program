package com.ohgiraffers.control;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class MainMenu {

    private Properties prop = new Properties();
    private Scanner sc = new Scanner(System.in);


    public MainMenu() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/attendance-management-query.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 프로그램 시작 */
    public void start() {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println();
            System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
            System.out.println("▓▓                                           ▓▓");
            System.out.println("▓▓           Kim's Family With Lee           ▓▓");
            System.out.println("▓▓                                           ▓▓");
            System.out.println("▓▓            Attendance Management          ▓▓");
            System.out.println("▓▓                                           ▓▓");
            System.out.println("▓▓                  Program                  ▓▓");
            System.out.println("▓▓                                           ▓▓");
            System.out.println("▓▓                 1. 로그인                  ▓▓");
            System.out.println("▓▓               2. 프로그램종료               ▓▓");
            System.out.println("▓▓                                           ▓▓");
            System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
            System.out.println();
            System.out.print("번호를 입력해주세요. (1. 로그인 | 2. 프로그램종료) : ");

            while (!sc.hasNextInt()) {
                System.out.println("잘못된 입력입니다. 다시 선택해주세요. (1. 로그인 | 2. 프로그램종료)");
                sc.next();
            }
            int inputNumber = sc.nextInt();

            switch(inputNumber) {
                case 1 :
                    login();
                    break;
                case 2 :
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                    break;
            }

        }

    }


    /* 로그인 했을 때 관리자 권한으로 메뉴 들어가기 */
    public void login() {
        Connection con = getConnection();

        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓               근태관리 프로그램              ▓▓");
        System.out.println("▓▓                 ID :                      ▓▓");
        System.out.println("▓▓                 PW :                      ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("아이디를 입력하세요 : ");
        String empId = sc.nextLine();
        System.out.print("비밀번호를 입력하세요 : ");
        String empPwd = sc.nextLine();
        System.out.println();

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        EmployeeDTO loginUser = null;
        String query = prop.getProperty("loginEMP");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empPwd);
            rset = pstmt.executeQuery();

            if (rset.next()) {
                loginUser = new EmployeeDTO();
                loginUser.setEmpId(rset.getString("emp_id"));
                loginUser.setEmpPwd(rset.getString("emp_password"));
                loginUser.setEmpIdentification(rset.getString("emp_identification"));

                switch ((loginUser.getEmpIdentification())) {
                    case "ADMIN":
                        System.out.println("              ✨" + rset.getString("emp_name") + "님✨ 환영합니다.");
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.adminMenu();
                        break;
                    case "MGR":
                        System.out.println("              ✨" + rset.getString("emp_name") + "님✨ 환영합니다.");
                        MGRMenu mgrMenu = new MGRMenu();
                        mgrMenu.mgrMenu();
                        break;
                    case "EMP":
                        System.out.println("              ✨" + rset.getString("emp_name") + "님✨ 환영합니다.");
                        EMPMenu empMenu = new EMPMenu();
                        empMenu.empMenu();
                        break;
                    default:
                        break;
                }
            } else {
                System.out.println("로그인에 실패했습니다. ID 또는 PW를 확인해주세요.");
                login();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
    }

}
