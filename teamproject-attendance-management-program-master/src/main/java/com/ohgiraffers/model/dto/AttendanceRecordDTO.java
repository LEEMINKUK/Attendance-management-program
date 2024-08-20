package com.ohgiraffers.model.dto;

public class AttendanceRecordDTO {

    private String attendanceCode;
    private int dateCode;
    private String empId;
    private String arrivalAtWork;
    private String leaveWork;
    private String lateWork;
    private String vacation;
    private String dayOff;

    public AttendanceRecordDTO() {

    }

    public String getAttendanceCode() {
        return attendanceCode;
    }

    public void setAttendanceCode(String attendanceCode) {
        this.attendanceCode = attendanceCode;
    }

    public int getDateCode() {
        return dateCode;
    }

    public void setDateCode(int dateCode) {
        this.dateCode = dateCode;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getArrivalAtWork() {
        return arrivalAtWork;
    }

    public void setArrivalAtWork(String arrivalAtWork) {
        this.arrivalAtWork = arrivalAtWork;
    }

    public String getLeaveWork() {
        return leaveWork;
    }

    public void setLeaveWork(String leaveWork) {
        this.leaveWork = leaveWork;
    }

    public String getLateWork() {
        return lateWork;
    }

    public void setLateWork(String lateWork) {
        this.lateWork = lateWork;
    }

    public String getVacation() {
        return vacation;
    }

    public void setVacation(String vacation) {
        this.vacation = vacation;
    }

    public String getDayOff() {
        return dayOff;
    }

    public void setDayOff(String dayOff) {
        this.dayOff = dayOff;
    }

    @Override
    public String toString() {
        return  empId +"님의 선택날짜 : "+ dateCode + "\n근태정보 [" +
                " 출근 = " + arrivalAtWork +
                ", 퇴근 = " + leaveWork +
                ", 지각 = " + lateWork +
                ", 휴가 = " + vacation +
                ", 결근 = " + dayOff +
                ']';
    }
}
