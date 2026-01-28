package com.revworkforce.service;

import com.revworkforce.dao.LeaveDAO;
import com.revworkforce.model.LeaveRequest;

public class LeaveService {

    private LeaveDAO leaveDAO = new LeaveDAO();

    public void applyLeave(LeaveRequest leave) {
        leaveDAO.applyLeave(leave);
    }
}
