package com.ohgiraffers.control;

import com.ohgiraffers.model.dao.EmpDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class EMPMenu {

    EmpDAO empDAO = new EmpDAO();
    Connection con = getConnection();

    /* 일반직원으로 로그인 했을 시 메뉴 화면 */
    public void empMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓             근태관리프로그램(EMP)            ▓▓");
        System.out.println("▓▓             1. 직원정보조회(본인)            ▓▓");
        System.out.println("▓▓             2. 근태기록조회(본인)            ▓▓");
        System.out.println("▓▓             3. 보상여부조회(본인)            ▓▓");
        System.out.println("▓▓             4. 로 그 아 웃                  ▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println();
        System.out.print("번호를 선택해 주세요(1 ~ 4) : ");
        while (!sc.hasNextInt()) {
            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            System.out.print("번호를 선택해 주세요(1 ~ 4) : ");
            sc.next();
        }
        int choiceNumber = sc.nextInt();

        switch (choiceNumber) {

            case 1:
                empDAO.retrievePersonalInfo();
                empMenu();
                break;
            case 2:
                viewPersonalAttendanceMenu();
                break;
            case 3:
                viewPersonalCompensation();
                break;
            case 4:
                emplogOut();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                empMenu();
                break;
        }

    }

    /* 개인 근태 조회 세부 메뉴 메서드 */
    public void viewPersonalAttendanceMenu() {
        Scanner sc = new Scanner(System.in);

        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓             근태관리프로그램(EMP)            ▓▓");
        System.out.println("▓▓              [근태기록조회(본인)]            ▓▓");
        System.out.println("▓▓              1. 일별근태기록 조회            ▓▓");
        System.out.println("▓▓              2. 기간별근태기록 조회          ▓▓");
        System.out.println("▓▓              3. 이 전 메 뉴                ▓▓");
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
                List<Map<String, Object>> dailyPersonalAttendanceInfoList = empDAO.dailyPersonalAttendanceInfo(con);
                for (Map<String, Object> dailyPersonalAttendanceInfo : dailyPersonalAttendanceInfoList) {
                    System.out.println(dailyPersonalAttendanceInfo);
                }
                viewPersonalAttendanceMenu();
                break;
            case 2:
                System.out.println("2. 기간별 근태기록 조회를 선택하셨습니다.");
                List<Map<String, Object>> periodPersonalAttendanceInfoList = empDAO.periodPersonalAttendanceInfo(con);
                for (Map<String, Object> periodPersonalAttendanceInfo : periodPersonalAttendanceInfoList) {
                    System.out.println(periodPersonalAttendanceInfo);
                }
                viewPersonalAttendanceMenu();
                break;
            case 3:
                empMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                viewPersonalAttendanceMenu();
                break;

        }

    }

    /* 개인 페널티 여부 조회 세부메뉴 메서드 */
    public void viewPersonalCompensation() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓");
        System.out.println("▓▓                                           ▓▓");
        System.out.println("▓▓              근태관리프로그램(EMP)           ▓▓");
        System.out.println("▓▓              [보상여부조회(본인)]            ▓▓");
        System.out.println("▓▓               1. 개인페널티 조회             ▓▓");
        System.out.println("▓▓               2. 한달보상여부 조회           ▓▓");
        System.out.println("▓▓               3. 이 전 메 뉴                ▓▓");
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
                System.out.println("1. 개인페널티 조회를 선택하셨습니다.");
                List<Map<String, Object>> personalPenaltyInfoList = empDAO.personalPenaltyInfo(con);
                for (Map<String, Object> personalPenaltyInfo : personalPenaltyInfoList) {
                    System.out.println(personalPenaltyInfo);
                }
                viewPersonalCompensation();
                break;
            case 2:
                System.out.println("2. 한달보상여부 조회를 선택하셨습니다.");
                List<Map<String, Object>> monthPersonalPenaltyInfoList = empDAO.monthPersonalPenaltyInfo(con);
                for (Map<String, Object> monthPersonalPenaltyInfo : monthPersonalPenaltyInfoList) {
                    System.out.println(monthPersonalPenaltyInfo);
                }
                viewPersonalCompensation();
                break;
            case 3:
                empMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                viewPersonalCompensation();
                break;

        }

    }


    /* 일반직원 로그아웃 메서드 */
    public void emplogOut() {

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
                empMenu();
                break;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                emplogOut();
                break;
        }

    }

}
