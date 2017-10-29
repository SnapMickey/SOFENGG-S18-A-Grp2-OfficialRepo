package test;

import services.SystemService;

public class test {

	public static void main(String args[]) {
		System.out.println(SystemService.getUser(11012344).getPosition());
	}
}
