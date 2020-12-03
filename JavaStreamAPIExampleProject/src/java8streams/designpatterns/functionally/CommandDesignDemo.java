package java8streams.designpatterns.functionally;

import java8streams.designpatterns.functionally.model.AC;
import java8streams.designpatterns.functionally.model.ACAutomationRemote;

public class CommandDesignDemo {

	public static void main(String[] args) {
		
	AC ac1 = new AC();
	AC ac2 = new AC();
	
	ACAutomationRemote remote = new ACAutomationRemote();
	remote.setCommand(ac2::turnOn);
	remote.buttonPressed();

	remote.setCommand(ac1::decTemp);
	remote.buttonPressed();
		
	}

}
