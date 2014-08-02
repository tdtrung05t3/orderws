package org.main;

import org.zk.ApplicationListener;
import org.zk.ApplicationState;

public class MockApplicationListener implements ApplicationListener {
	private ApplicationState state;

	@Override
	public void onChange(ApplicationState state) {
		this.state = state;
	}

	public ApplicationState getApplicationState() {
		return state;
	}

}
