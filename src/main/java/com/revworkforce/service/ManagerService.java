package com.revworkforce.service;

import com.revworkforce.dao.ManagerDAO;

public class ManagerService {

    private ManagerDAO managerDAO = new ManagerDAO();

    public void viewTeamEmployees(int managerId) {
        managerDAO.viewEmployeesUnderManager(managerId);
    }

    public void viewTeamLeaves(int managerId) {
        managerDAO.viewTeamLeaves(managerId);
    }

    public void updateLeaveStatus(int leaveId, String status) {
        managerDAO.updateLeaveStatus(leaveId, status);
    }
}
