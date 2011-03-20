package nfrancois.poc.jerseyguiceappengine.service;

import javax.inject.Singleton;


@Singleton
public class HelloService {
	
	public String saysHelloToSomeone(String name){
		return "Hello "+name;
	}

	public String sendHello(String name) {
		return "Hello "+name;
	}

}
