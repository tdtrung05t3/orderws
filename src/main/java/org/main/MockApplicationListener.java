package org.main;

import org.zk.Application;
import org.zk.ApplicationListener;
import org.zk.ApplicationState;

public class MockApplicationListener implements ApplicationListener {
	private ApplicationState state;
	private Application app; 
	private String zooKeeperAddr;
	private String master;
	
	public MockApplicationListener() {
		app = new Application(zooKeeperAddr, master);
		app.registListener(this);
		app.start();
	}
	
	public void stop() {
		app.stop();
	}
	
	@Override
	public void onChange(ApplicationState state) {
		this.state = state;
		if (state.equals(ApplicationState.SLAVE.toString())) {
		}
	}
	
	public boolean isMaster() {
		if (state.equals(ApplicationState.MASTER.toString())) {
			return true;
		} 
		return false;
	}

	public void setZooKeeperAddr(String zooKeeperAddr) {
		this.zooKeeperAddr = zooKeeperAddr;
	}

	public void setMaster(String master) {
		this.master = master;
	}
}
