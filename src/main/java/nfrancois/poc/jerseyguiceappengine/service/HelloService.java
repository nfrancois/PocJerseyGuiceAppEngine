package nfrancois.poc.jerseyguiceappengine.service;

import com.google.inject.Singleton;

@Singleton
public class HelloService {
	
	public String saysHelloToSomeone(String who){
		return "Hello "+who;
	}

}
