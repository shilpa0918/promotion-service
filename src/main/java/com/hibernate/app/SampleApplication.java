package com.hibernate.app;


public class SampleApplication {

	public static void main(String[] args) {
		System.out.println("Main application..");
		String str = "shilpa@gmail.com";
		String isEmail = checkIfEmailOrUsername(str);
		System.out.println(Math.random()*1000000);
		System.out.println("The string "+str+" is an email Id? "+isEmail);
	}

	private static String checkIfEmailOrUsername(String str) {
		String isEmail = "yes";
		if(!str.contains("@")){
			System.out.println("this is username");
			isEmail = "no";
		}
		return isEmail;
	}

	/*public static String getEmailProvider(String emailAddress){
		//String emailProvider = "";
		String emailProvider[] = emailAddress.split("@");
		
		//logic
		return emailProvider;
	}*/

}
