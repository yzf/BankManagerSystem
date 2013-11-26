package info.yzf.service;

import info.yzf.database.SerialDatabase;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		SerialDatabase.getInstance().save();
	} 

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		BankTask.getInstance().runTasks();
	}

}
