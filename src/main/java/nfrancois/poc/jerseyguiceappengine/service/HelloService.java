package nfrancois.poc.jerseyguiceappengine.service;

import javax.inject.Singleton;

@Singleton
public class HelloService {
	
	public String saysHelloToSomeone(String who){
		return "Hello "+who;
	}

}
