package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.dao.LeaveDAO;

public class ManagerService {

    private final LeaveDAO leaveDAO = new LeaveDAO();
    private final EmployeeDAO employeeDAO = new EmployeeDAO();

    /* ================= VIEW ALL LEAVE REQUESTS ================= */
    public void viewAllLeaves() {
        leaveDAO.viewAllLeaves();
    }

    /* ================= APPROVE / REJECT LEAVE ================= */
    public void approveOrRejectLeave(int leaveId, String status, String comment) {
        leaveDAO.updateLeaveStatus(leaveId, status, comment);
    }

    /* ================= VIEW TEAM MEMBERS ================= */
    public void viewTeamMembers(int managerId) {
        employeeDAO.getEmployeesByManager(managerId);
    }
}
