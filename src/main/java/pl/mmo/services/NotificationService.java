package pl.mmo.services;

import java.util.List;

import pl.mmo.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    static List<NotificationMessage> getNotificationMessages() {
		// TODO Auto-generated method stub
		return null;
	}
}