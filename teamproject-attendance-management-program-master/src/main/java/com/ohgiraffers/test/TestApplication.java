package com.ohgiraffers.test;

import com.ohgiraffers.control.MainMenu;
import com.ohgiraffers.model.dao.AdminDAO;
import com.ohgiraffers.model.dao.EmpDAO;
import com.ohgiraffers.model.dao.MgrDAO;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.view.AsciiArtPrinter.startLogo;

public class TestApplication {
    static MainMenu loginEmp = new MainMenu();

    public static void main(String[] args) {

        /* 아스키코드를 활용한 첫 이미지 */
        startLogo(); // 작동 확인 완료

        /* 로그인 화면 (MainMenu 클래스) */
//        loginEmp.login(); // 작동 확인 완료

        /* 관리자 페이지 테스트 (AdminDAO 클래스) */
        AdminDAO adminDAO = new AdminDAO();

        /* 관리자 메뉴 테스트 */

        /* 1. 직원 정보 입력(INSERT) */
//        adminDAO.insertEmpInfo(); // 작동 확인 완료

        /* 2. 직원 정보 수정(UPDATE) */
        /* 2-1. 기본 정보 수정 */
//        adminDAO.changeEmpInfo(); // 작동 확인 완료
        /* 2-2 근태 이력 수정 */
//          adminDAO.changeEmpAttendance();
        /* 공백을 주는 코드를 빼버릴지 고민중.*/

        /* 3. 직원 정보 삭제 */
//        adminDAO.deleteEmpInfo();

        /* 4. 직원 정보 조회 */
        /* 4-1. 직원 ID로 정보 조회 */
//        adminDAO.retrieveEmpInfo(); // 작동 확인 완료
        /* 4-2. 직원 ID와 날짜지정으로 근태 정보 조회 */
//        adminDAO.dailyAttendanceInfo(); // 직원 근태 정보 (특정 ID, 특정날짜)

        /* 4-3. 직원 ID와 특정기간으로 근태 정보 조회*/
//        Connection con = getConnection();
//        List<Map<String, Object>> byPeriodList = adminDAO.byPeriodAttendanceInfo(con);
//        for (Map<String, Object> byPreiod : byPeriodList) {
//            System.out.println(byPreiod);
//        }

        /* 4-4. 날짜입력으로 전 직원 근태 정보 조회 */
//        Connection con = getConnection();
//        List<Map<String, Object>> oneDayAllEmpList = adminDAO.allEmpDailyAttendanceInfo(con);
//        for (Map<String, Object> oneDayAllEmpAttendance : oneDayAllEmpList) {
//            System.out.println(oneDayAllEmpAttendance);
//        }

        /* 5. 포상여부 조회 */
        /* 5-1. 직원 ID로 페널티 조회 */
//        Connection con = getConnection();
//        List<Map<String, Object>> oneEmpPenaltyList = adminDAO.oneEmpPenaltyInfo(con);
//        for (Map<String, Object> oneEmpPenalty : oneEmpPenaltyList) {
//            System.out.println(oneEmpPenalty);
//        }
        /* 5-2 월간 보상 여부 조회(전체보기) */
//        Connection con = getConnection();
//        List<Map<String, Object>> monthPenaltyList = adminDAO.monthAllEmpPenaltyInfo(con);
//        for (Map<String, Object> monthEmpPenalty : monthPenaltyList) {
//            System.out.println(monthEmpPenalty);
//        }

//        /* 전직원 기본정보 조회 */
        Connection con = getConnection();
//        List<Map<String, Object>> allEmpinfoList = adminDAO.allEmpInfo(con);
//        for (Map<String, Object> allEmpInfo : allEmpinfoList) {
//            System.out.println(allEmpInfo);
//        }
//
//        List<Map<String, Object>> oneEmpPenaltyList = adminDAO.oneEmpPenaltyInfo(con);
//        for (Map<String, Object> oneEmpPenalty : oneEmpPenaltyList) {
//            System.out.println(oneEmpPenalty);
//        }

        MgrDAO mgrDAO = new MgrDAO();

        /* 부서 내 직원 기본 정보 조회 */
//        mgrDAO.departmentEmpInfo();

        /* 부서 내 직원 일별 근태기록 조회*/
//        List<Map<String, Object>> departmentEmpDailyAttendanceList = mgrDAO.departmentDailyAttendance(con);
//        for (Map<String, Object> departmentDailyAttendance : departmentEmpDailyAttendanceList) {
//            System.out.println(departmentDailyAttendance);
//        }

        /* 부서 내 직원 기간별 근태기록 조회*/
//        List<Map<String, Object>> departmentEmpPeriodAttendanceList = mgrDAO.departmentPeriodAttendance(con);
//        for (Map<String, Object> departmentPeriodAttendance : departmentEmpPeriodAttendanceList) {
//            System.out.println(departmentPeriodAttendance);
//        }

        /* 부서 내 월간 보상여부 조회 */
//        List<Map<String, Object>> departmentMonthCompensationList = mgrDAO.departmentEmpPenaltyInfo(con);
//        for (Map<String, Object> departmentMonthCompensation : departmentMonthCompensationList) {
//            System.out.println(departmentMonthCompensation);
//        }

        EmpDAO empDAO = new EmpDAO();
        /* 개인 정보 조회(비번으로 확인) */
//        empDAO.retrievePersonalInfo();

        /* 개인 일일근태 조회(비번으로 확인) */
//        List<Map<String, Object>> dailyPersonalAttendanceInfoList = empDAO.dailyPersonalAttendanceInfo(con);
//        for (Map<String, Object> dailyPersonalAttendanceInfo : dailyPersonalAttendanceInfoList) {
//            System.out.println(dailyPersonalAttendanceInfo);
//        }

        /* 개인 기간별 근태 조회(비번으로 확인) */
//        List<Map<String, Object>> periodPersonalAttendanceInfoList = empDAO.periodPersonalAttendanceInfo(con);
//        for (Map<String, Object> periodPersonalAttendanceInfo : periodPersonalAttendanceInfoList) {
//            System.out.println(periodPersonalAttendanceInfo);
//        }

        /* 개인 페널티 조회 (비번으로 확인) */
//        List<Map<String, Object>> personalPenaltyInfoList = empDAO.personalPenaltyInfo(con);
//        for (Map<String, Object> personalPenaltyInfo : personalPenaltyInfoList) {
//            System.out.println(personalPenaltyInfo);
//        }

        /* 개인 한달 페널티 여부 조회 (비번으로 확인) */
//        List<Map<String, Object>> monthPersonalPenaltyInfoList = empDAO.monthPersonalPenaltyInfo(con);
//        for (Map<String, Object> monthPersonalPenaltyInfo : monthPersonalPenaltyInfoList) {
//            System.out.println(monthPersonalPenaltyInfo);
//        }












    }
}

