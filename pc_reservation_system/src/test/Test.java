package test;

import beans.*;
import services.SystemService;

public class Test {
	
	public static void main(String[] args){
		
		
		for(Lab l: SystemService.getAllLabs())
			System.out.println(l.toString());
		
	}
}
