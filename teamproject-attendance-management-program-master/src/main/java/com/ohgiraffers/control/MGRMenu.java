package com.ohgiraffers.control;

import com.ohgiraffers.model.dao.MgrDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class MGRMenu {

    MgrDAO mgrDAO = new MgrDAO();
    Connection con = getConnection();

    /* 중간관리자로 로그인 했을 시 메뉴 화면 */
    public void mgrMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓             근태관리프로그램(MGR)            ▓▓");
        System.out.println("▓▓            1. 직원정보조회(부서내)            ▓▓");
        System.out.println("▓▓            2. 근태기록조회(부서내)            ▓▓");
        System.out.println("▓▓            3. 보상여부조회(부서내)            ▓▓");
        System.out.println("▓▓            4. 로 그 아 웃                   ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("번호를 입력해주세요(1 ~ 4) : ");
        while (!sc.hasNextInt()) {
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            System.out.print("번호를 입력해주세요(1 ~ 4) : ");
            sc.next();
        }
        int choiceNumber = sc.nextInt();

        switch (choiceNumber) {

            case 1:
                mgrDAO.departmentEmpInfo();
                mgrMenu();
                break;
            case 2:
                viewDepartmentAttendanceMenu();
                break;
            case 3:
                List<Map<String, Object>> departmentMonthCompensationList = mgrDAO.departmentEmpPenaltyInfo(con);
                for (Map<String, Object> departmentMonthCompensation : departmentMonthCompensationList) {
                    System.out.println(departmentMonthCompensation);
                }
                mgrMenu();
                break;
            case 4:
                mgrlogOut();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                mgrMenu();
                break;
        }

    }

    /* 근태기록조회 세부화 */
    public void viewDepartmentAttendanceMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓              근태관리프로그램(MGR)           ▓▓");
        System.out.println("▓▓             [근태기록조회(부서내)]           ▓▓");
        System.out.println("▓▓              1. 일별근태기록 조회            ▓▓");
        System.out.println("▓▓              2. 기간별근태기록 조회           ▓▓");
        System.out.println("▓▓              3. 이 전 메 뉴                 ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("번호를 입력해주세요(1 ~ 3) : ");
        while (!sc.hasNextInt()) {
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            System.out.print("번호를 입력해주세요(1 ~ 3) : ");

            sc.next();
        }
        int choiceNumber = sc.nextInt();


        switch (choiceNumber) {
            case 1:
                System.out.println("1. 일별 근태기록 조회를 선택하셨습니다.");
                List<Map<String, Object>> departmentEmpDailyAttendanceList = mgrDAO.departmentDailyAttendance(con);
                for (Map<String, Object> departmentDailyAttendance : departmentEmpDailyAttendanceList) {
                    System.out.println(departmentDailyAttendance);
                }
                viewDepartmentAttendanceMenu();
                break;
            case 2:
                System.out.println("2. 기간별 근태기록 조회를 선택하셨습니다.");
                List<Map<String, Object>> departmentEmpPeriodAttendanceList = mgrDAO.departmentPeriodAttendance(con);
                for (Map<String, Object> departmentPeriodAttendance : departmentEmpPeriodAttendanceList) {
                    System.out.println(departmentPeriodAttendance);
                }
                viewDepartmentAttendanceMenu();
                break;
            case 3:
                mgrMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                viewDepartmentAttendanceMenu();
                break;

        }

    }


    /* 중간관리자 로그아웃 메서드 */
    public void mgrlogOut() {

        Scanner sc = new Scanner(System.in);

        System.out.print("로그아웃 하시겠습니까? (Y/N) : ");
        String typing = sc.nextLine().toUpperCase();

        switch (typing) {

            case "Y":
                System.out.println("로그아웃 되었습니다.");
                MainMenu goToMainMenu = new MainMenu();
                goToMainMenu.start();
                break;
            case "N":
                System.out.println("메뉴 선택창으로 돌아갑니다.");
                mgrMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                mgrlogOut();
                break;
        }

    }

}
