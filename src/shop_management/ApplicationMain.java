package shop_management;

import java.util.Scanner;
import java.io.IOException;

import product_management.ProductManagement;
import user_management.UserManagement;
import db_operations.DBUtils;
public class ApplicationMain {
	public static void main(String args[]) throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean canikeeprunningprogram = true;
		System.out.println("##WELLCOME TO SHOP MANAGEMENT##");
		System.out.println("\n");
		System.out.println("Login name:");
		String loginname = scanner.nextLine();
		System.out.println("Passwords:");
		String passwords = scanner.nextLine();
		
		if(!UserManagement.validateUserandPasswords(loginname,passwords)) {
			System.out.println("Login failed... closing the application...");
			return;
		}
		while(canikeeprunningprogram == true) {
			System.out.println("###WELLCOME TO SHOP MANAGEMENT##");
			System.out.println("\n");
			System.out.println("What would you like to do?:");
			System.out.println("1. User Management");
			System.out.println("2 Product Management");
			System.out.println("5. Quit");
			
			int optionselectedbyuser = scanner.nextInt();
			if(optionselectedbyuser == 1) {
				UserManagement.userManagement();
			}
			else if(optionselectedbyuser == 2) {
				ProductManagement.productManagement();
			}
			else if(optionselectedbyuser ==5) {
				break;
		
		}
		}
	}

}
