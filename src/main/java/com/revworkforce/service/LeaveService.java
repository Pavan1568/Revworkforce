package com.revworkforce.service;

import com.revworkforce.dao.LeaveDAO;
import com.revworkforce.model.LeaveRequest;

public class LeaveService {

    private LeaveDAO leaveDAO = new LeaveDAO();

    public void applyLeave(LeaveRequest leave) {
        leaveDAO.applyLeave(leave);
    }

    public void viewAllLeaves() {
        leaveDAO.viewAllLeaves();
    }

    public void viewMyLeaves(int employeeId) {
        leaveDAO.viewMyLeaves(employeeId);
    }

    public void cancelLeave(int leaveId, int employeeId) {
        leaveDAO.cancelLeave(leaveId, employeeId);
    }

    public void approveOrRejectLeave(int leaveId, String status, String comment) {
        leaveDAO.updateLeaveStatus(leaveId, status, comment);
    }

    public void viewLeaveReport() {
        leaveDAO.leaveReport();
    }
}
