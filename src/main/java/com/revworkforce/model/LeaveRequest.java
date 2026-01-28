package com.revworkforce.model;

import java.sql.Date;

public class LeaveRequest {

    private int id;
    private int employeeId;
    private String leaveType;
    private Date startDate;
    private Date endDate;
    private String reason;
    private String status;

    public LeaveRequest(int id, int employeeId, String leaveType, Date startDate, Date endDate, String reason, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.reason = reason;
        this.status = status;
    }

    public int getId() { return id; }
    public int getEmployeeId() { return employeeId; }
    public String getLeaveType() { return leaveType; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public String getReason() { return reason; }
    public String getStatus() { return status; }
}
