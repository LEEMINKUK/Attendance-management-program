package com.ohgiraffers.control;

import com.ohgiraffers.model.dao.AdminDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class AdminMenu {

    AdminDAO adminDAO = new AdminDAO();

    /* 관리자로 로그인 했을 시 메뉴 화면 */
    public void adminMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓            근태관리프로그램(ADMIN)           ▓▓");
        System.out.println("▓▓              1. 직원정보 입력               ▓▓");
        System.out.println("▓▓              2. 직원정보 조회               ▓▓");
        System.out.println("▓▓              3. 직원정보 수정               ▓▓");
        System.out.println("▓▓              4. 직원정보 삭제               ▓▓");
        System.out.println("▓▓              5. 보상여부 조회               ▓▓");
        System.out.println("▓▓              6. 로 그 아 웃                ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("번호를 선택해 주세요 (1 ~ 6) : ");

        while (!sc.hasNextInt()) {
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            System.out.print("번호를 선택해 주세요 (1 ~ 6) : ");
            sc.next();
        }
        int choiceNumber = sc.nextInt();

        switch (choiceNumber) {

            case 1:
                System.out.println("1. 직원정보 입력을 선택하셨습니다.");
                adminDAO.insertEmpInfo(); // 직원 정보 입력
                adminMenu();
                break;
            case 2:
                System.out.println("직원정보 조회를 선택하셨습니다.");
                readInfo();
                break;
            case 3:
                System.out.println("2. 직원정보 수정을 선택하셨습니다.");
                updateInfo();
                break;
            case 4:
                System.out.println("직원정보 삭제를 선택하셨습니다.");
                adminDAO.deleteEmpInfo();
                adminMenu();
                break;
            case 5:
                System.out.println("포상여부 조회를 선택하셨습니다.");
                compensation();
                break;
            case 6:
                adminlogOut();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                adminMenu();
                break;
        }

    }

    /* 관리자 로그아웃 메서드 */
    public void adminlogOut() {

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
                AdminMenu goToAdminMenu = new AdminMenu();
                goToAdminMenu.adminMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                adminlogOut();
                break;
        }

    }

    /* 정보수정 메서드 분할 */
    public void updateInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓             근태관리프로그램(ADMIN)          ▓▓");
        System.out.println("▓▓                [직원정보 수정]              ▓▓");
        System.out.println("▓▓                1. 일반정보 수정             ▓▓");
        System.out.println("▓▓                2. 근태정보 수정             ▓▓");
        System.out.println("▓▓                3. 이 전 메 뉴              ▓▓");
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
                System.out.println("1. 일반정보 수정을 선택하셨습니다.");
                adminDAO.changeEmpInfo(); // 직원 정보 변경(단점, 변경할라면 다 바꿔야함)
                updateInfo();
                break;
            case 2:
                System.out.println("2. 근태정보 수정을 선택하셨습니다.");
                adminDAO.changeEmpAttendance();
                updateInfo();
                break;
            case 3:
                adminMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                updateInfo();
                break;

        }

    }

    /* 정보조회 메서드 분할 */
    public void readInfo() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓            근태관리프로그램(ADMIN)           ▓▓");
        System.out.println("▓▓                [직원정보 조회]              ▓▓");
        System.out.println("▓▓             1. 직원(개인) 조회              ▓▓");
        System.out.println("▓▓             2. 직원(전체) 조회              ▓▓");
        System.out.println("▓▓             3. 근태(개인) 조회              ▓▓");
        System.out.println("▓▓             4. 전직원근태(지정일) 조회       ▓▓");
        System.out.println("▓▓             5. 직원1인(기간) 조회           ▓▓");
        System.out.println("▓▓             6. 이 전 메 뉴                 ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("번호를 입력해주세요(1 ~ 6) : ");
        while (!sc.hasNextInt()) {
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            System.out.print("번호를 입력해주세요(1 ~ 6) : ");
            sc.next();
        }
        int choiceNumber = sc.nextInt();
        Connection con = getConnection();


        switch (choiceNumber) {

            case 1:
                System.out.println("1. 직원(개인) 조회를 선택하셨습니다.");
                adminDAO.retrieveEmpInfo();
                readInfo();
                break;
            case 2:
                System.out.println("2. 직원(전체) 조회를 선택하셨습니다.");
                List<Map<String, Object>> allEmpinfoList = adminDAO.allEmpInfo(con);
                for (Map<String, Object> allEmpInfo : allEmpinfoList) {
                    System.out.println(allEmpInfo);
                }
                readInfo();
                break;
            case 3:
                System.out.println("3. 근태(개인) 조회를 선택하셨습니다.");
                adminDAO.dailyAttendanceInfo();
                readInfo();
                break;
            case 4:
                System.out.println("4. 근태(지정일) 조회를 선택하셨습니다.");
                List<Map<String, Object>> oneDayAllEmpAttendanceList = adminDAO.allEmpDailyAttendanceInfo(con);
                for (Map<String, Object> oneDayAllEmpAttendance : oneDayAllEmpAttendanceList) {
                    System.out.println(oneDayAllEmpAttendance);
                }
                readInfo();
                break;
            case 5:
                System.out.println("5. 근태(기간) 조회를 선택하셨습니다.");
                List<Map<String, Object>> byPeriodList = adminDAO.byPeriodAttendanceInfo(con);
                for (Map<String, Object> byPreiod : byPeriodList) {
                    System.out.println(byPreiod);
                }
                readInfo();
                break;
            case 6:
                System.out.println("이전메뉴로 돌아갑니다.");
                adminMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                readInfo();
                break;

        }

    }

    /* 포상여부조회 메서드 분할 */
    public void compensation() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓             근태관리프로그램(ADMIN)          ▓▓");
        System.out.println("▓▓                 [보상여부 조회]             ▓▓");
        System.out.println("▓▓             1. 직원페널티(개인) 조회         ▓▓");
        System.out.println("▓▓             2. 월간 보상여부 조회            ▓▓");
        System.out.println("▓▓             3. 이 전 메 뉴                  ▓▓");
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
        Connection con = getConnection();

        switch (choiceNumber) {
            case 1:
                System.out.println("1. 직원페널티(개인) 조회를 선택하셨습니다.");
                List<Map<String, Object>> oneEmpPenaltyList = adminDAO.oneEmpPenaltyInfo(con);
                for (Map<String, Object> oneEmpPenalty : oneEmpPenaltyList) {
                    System.out.println(oneEmpPenalty);
                }
                compensation();
                break;
            case 2:
                System.out.println("2. 월간 보상여부 조회를 선택하셨습니다.");
                List<Map<String, Object>> monthPenaltyList = adminDAO.monthAllEmpPenaltyInfo(con);
                for (Map<String, Object> monthEmpPenalty : monthPenaltyList) {
                    System.out.println(monthEmpPenalty);
                }
                compensation();
                break;
            case 3:
                System.out.println("이전 메뉴로 돌아갑니다. ");
                adminMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                compensation();
                break;

        }


    }

}

