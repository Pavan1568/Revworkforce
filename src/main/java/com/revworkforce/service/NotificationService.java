package com.revworkforce.service;

import com.revworkforce.dao.NotificationDAO;

public class NotificationService {

    private NotificationDAO notificationDAO = new NotificationDAO();

    public void notifyEmployee(int employeeId, String message) {
        notificationDAO.addNotification(employeeId, message);
    }

    public void viewNotifications(int employeeId) {
        notificationDAO.showNotifications(employeeId);
    }
}
