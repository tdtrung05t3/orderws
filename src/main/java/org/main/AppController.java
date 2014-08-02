package org.main;

import org.zk.Application;
import org.zk.ApplicationListener;
import org.main.MockApplicationListener;

public class AppController {
	public AppController() {
		MockApplicationListener mockApplicationListener = new MockApplicationListener();
		Application app = new Application();
		app.registListener(mockApplicationListener);
		app.start();
		// app la master;
		System.out.println(mockApplicationListener.getApplicationState());
		
	}
	public static void main(String[] args) {
		new AppController();
	}
}
