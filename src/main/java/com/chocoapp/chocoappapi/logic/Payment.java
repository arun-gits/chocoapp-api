package com.chocoapp.chocoappapi.logic;

import java.util.Scanner;

public class Payment {
	static Scanner sc=new Scanner(System.in);
	public static String PayMode() throws Exception {
	System.out.println("Enter the payment mode");
	System.out.println("1. UPI id");
	System.out.println("2. Debit/Credit card");
	System.out.println("3. Cash on Delivery");
	System.out.println("4. Back");
	System.out.println("5. Exit\n");
	String paymentMode=null;
	int choice=sc.nextInt();
	if(choice==1) {
		paymentMode=PayOn(choice);
	}
	else if(choice==2) {
		paymentMode=PayOn(choice);
	}
	else if(choice==3){
		paymentMode=PayOn(choice);
	}
	else if(choice==4) {
		
	}
	else if(choice==5) {
		System.out.println("Thanks for shopping ! :)");
		System.exit(0);
	}

	else {
		System.out.println("Enter valid input");
		PayMode();
	}

	return paymentMode;

}

	public static String PayOn(int a) throws Exception {
		
		OverallValidation captcha=new OverallValidation();
		String paymentMode=null;
	switch(a) {
		case 1:
				System.out.println("Enter your UPI id:");
				String upi=sc.next();
					if (!upi.contains("@") || !upi.contains(".")) {
						System.out.println("Invalid UPI\n");
						PayOn(1);
					}
					else {
						
						int capt=captcha.captchaVerification();
						if(capt==0) {
						System.out.println("Returning you to main page");
						paymentMode="UPI: "+upi;
					}
					}
					break;
		case 2:
				System.out.println("Enter Debit/Credit Card number");
				String deb=sc.next();
				sc.next();
				if(deb.length()==16) {
					int capt=captcha.captchaVerification();
					if(capt==0) {
						
						System.out.println("Returning you to main page");
						paymentMode="Debit Card ends with"+deb.charAt(13)+deb.charAt(14)+deb.charAt(15);
					}
				}
				else {
					System.out.println("Invalid input\n");
					PayOn(2);
				}
				break;
		case 3:
			int capt=captcha.captchaVerification();
			if(capt==0) {
				System.out.println("Returning you to main page");
				paymentMode="Cash On Delivery";
			}
}

	return paymentMode;
}

}
