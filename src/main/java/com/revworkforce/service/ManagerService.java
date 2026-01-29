package com.revworkforce.service;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.dao.LeaveDAO;

public class ManagerService {

    private LeaveDAO leaveDAO = new LeaveDAO();
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public void viewAllLeaves() {
        leaveDAO.viewAllLeaves();
    }

    public void approveOrRejectLeave(int leaveId, String status, String comment) {
        leaveDAO.updateLeaveStatus(leaveId, status, comment);
    }

    public void viewTeamMembers(int managerId) {
        employeeDAO.getEmployeesByManager(managerId);
    }
}
