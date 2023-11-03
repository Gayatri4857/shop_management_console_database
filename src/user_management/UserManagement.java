package user_management;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import db_operations.DBUtils;

public class UserManagement {

	public static void userManagement() throws IOException{
		
		Scanner scanner = new Scanner(System.in);
		boolean canikeeprunningprogram = true;
		while(canikeeprunningprogram == true) {
			System.out.println("##WELLCOME TO USER MANAGEMENT##");
			System.out.println("\n");
			System.out.println("1. Add User:");
			System.out.println("2. Edit User:");
			System.out.println("3. Delete User:");
			System.out.println("4. Search User:");
			System.out.println("5. Quit User:");
			
			int optionselectedbyuser = scanner.nextInt();
			if(optionselectedbyuser == userOptions.QUIT_USER) {
			
				System.out.println("!!!Program closed..");
				System.out.println("\n");
				canikeeprunningprogram = false;
			}
			else if(optionselectedbyuser == userOptions.ADD_USER) {
				addUser();
				System.out.println("\n");
			}
			else if(optionselectedbyuser == userOptions.SEARCH_USER) {
				System.out.println("Enter the Username to Search...");
				scanner.nextLine();
				String searchusername = scanner.nextLine();
				SearchUser(searchusername);
				System.out.println("\n");
			}
			else if(optionselectedbyuser == userOptions.DELETE_USER) {
				System.out.println("Enter the Username to Delete..");
				scanner.nextLine();
				String deleteusername = scanner.nextLine();
				DeleteUser(deleteusername);
				System.out.println("\n");
			}
			else if(optionselectedbyuser == userOptions.EDIT_USER) {
				System.out.println("Enter the username to Edit...");
				scanner.nextLine();
				String editusername = scanner.nextLine();
				EditUser(editusername);
				System.out.println("\n");
			}
	}
		
	}
	public static void addUser() {
		Scanner scanner = new Scanner(System.in);
		User userObject = new User();
		System.out.println("User name :");
		userObject.username = scanner.nextLine();
		System.out.println("Login name :");
		userObject.loginname = scanner.nextLine();
		System.out.println("Passwords is:");
		userObject.passwords = scanner.nextLine();
		System.out.println("Confirm password is:");
		userObject.confirmpassword = scanner.nextLine();
		System.out.println("User Role is:");
		userObject.userrole = scanner.nextLine();
		
		System.out.println("User name :"+userObject.username);
		System.out.println("Login name:"+userObject.loginname);
		System.out.println("Passwords :"+userObject.passwords);
		System.out.println("Confirm password:"+userObject.confirmpassword);
		System.out.println("User role :"+userObject.userrole);
		
		String query = "insert INTO users(username,loginname,passwords,confirmpassword,userrole)Values('"+userObject.username+"','"+userObject.loginname+"', '"+userObject.passwords+"', '"+userObject.confirmpassword+"','"+userObject.userrole+"' )";
		
		DBUtils.executeQuery(query);
	}
	public static void SearchUser(String username) {
		    String query ="select * from users where username ='"+ username + "' ";
		    
		    ResultSet rs = DBUtils.executeQueryGetResult(query);
		    
		    try{
		    	
		    while (rs.next()) {
		    	if(rs.getString("username").equalsIgnoreCase(username)) {
		    
				System.out.println("User name:"+rs.getString("username"));
				System.out.println("Login name:"+rs.getString("loginname"));
				System.out.println("Password:"+rs.getString("passwords"));
				System.out.println("Confirm password:"+rs.getString("confirmpassword"));
				System.out.println("User role:"+rs.getString("userrole"));
			    return;
			}
		    }
		    }
		    catch (Exception e) {
		    	System.out.println("User not found!!");
		    }
		    System.out.println("User not found!!!");
		    
	}
		public static void EditUser(String username) {
			String query = "select * from users where username = '"+username+"'";

			ResultSet rs = DBUtils.executeQueryGetResult(query);
			try {

				while(rs.next()) {
					if(rs.getString("username").equalsIgnoreCase(username)) {
					Scanner sc = new Scanner(System.in);
					User users =new User();

					System.out.print("New username is :");
					users.username= sc.nextLine();

					System.out.print("New loginname is :");
					users.loginname = sc.nextLine();

					System.out.print("New Password is :");
					users.passwords = sc.nextLine();

					System.out.print("New Conform Password is :");
					users.confirmpassword = sc.nextLine();

					System.out.print("New User Role is :");
					users.userrole = sc.nextLine();
					
					
					String updateQuery = "update users set "
							+ "username='"+users.username+"', loginname = '"+users.loginname+"', "
									+ "passwords='"+users.passwords+"', confirmpassword='"+users.confirmpassword+"', "
											+ "userrole='"+users.userrole+"' where username='"+rs.getString("username")+"'";

					

					DBUtils.executeQuery(updateQuery);

					System.out.println("User updated successfully.");
					return;

				}
				else {
					System.out.println("!!!!USER NOT FOUND!!!!!");
				}
			}
			}catch(SQLException e) {
				System.out.println("User not found!!");
			}
			System.out.println("User not found..");
	}

	public static void DeleteUser(String username) {
		 String query = "delete from users where username ='" + username +"' ";
	     DBUtils.executeQuery(query);
	}
	
	
	public static boolean validateUserandPasswords(String loginname, String passwords) throws IOException {
		String query = "select * from users where loginname='"+loginname+"' and passwords='"+passwords+"' ";
		ResultSet rs =DBUtils.executeQueryGetResult(query);
	try {	
		if (rs.next()) {
			return true;
		}
	}
		 catch(SQLException e) {
		 e.printStackTrace();
		
		 }
	
		
		return false;
    }
}
