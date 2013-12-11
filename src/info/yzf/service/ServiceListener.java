package info.yzf.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServiceListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	} 

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		BankTask.getInstance().runTasks();
	}

}
