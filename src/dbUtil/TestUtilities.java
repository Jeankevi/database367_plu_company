package dbUtil;
/**
 * This program is used to test the Utilities class
 */
 
// You need to import the java.sql package to use JDBC
import java.sql.*; 
 
import java.util.Scanner;

/**
 * @author kenb
 * 
 */
public class TestUtilities {

	// Global variables
	static Utilities testObj = new Utilities(); // Utilities object for testing
	static Scanner keyboard = new Scanner(System.in); // standard input

	public static void main(String[] args) throws SQLException {

		// variables needed for menu
		int choice;
		boolean done = false;

		while (!done) {
			System.out.println();
			displaymenu();
			choice = getChoice();
			switch (choice) {
			case 1: {
				openDefault();
				break;
			}
			case 2: {
				callGetNameSalary();
				break;
			}
			case 3: {
				callMatchName();
				break;
			}
			case 4: {
				callEmployeeByDNO();
				break;
			}
			case 9: {
				testObj.closeDB(); //close the DB connection 
				break;
			}
			case 10: {
				done = true;
				System.out.println("Good bye");
				break;
			}
			}// switch
		}

	}// main

	static void displaymenu() {
		System.out.println("1)  open default DB");
		System.out.println("2)  call getNameSalary()");
		System.out.println("3)  call matchLastName(String)");
		System.out.println("4)  call employeeByDNO()");
		System.out.println("5)  open ");
		System.out.println("6)  call ");
		System.out.println("7)  call ");
		System.out.println("8)  call ");
		System.out.println("9)  close the DB");
		System.out.println("10) quit");
	}

	static int getChoice() {
		String input;
		int i = 0;
		while (i < 1 || i > 10) {
			try {
				System.out.print("Please enter an integer between 1-10: ");
				input = keyboard.nextLine();
				i = Integer.parseInt(input);
				System.out.println();
			} catch (NumberFormatException e) {
				System.out.println("I SAID AN INTEGER!!!!");
			}
		}
		return i;
	}

	// open the default database;
	static void openDefault() {
		testObj.openDB();
	}

	// test getNameSalary() method
	static void callGetNameSalary() throws SQLException {
		ResultSet rs;
		System.out.println("Research Department Employees");
		System.out.println("*****************************");
		System.out.printf("LastName, FirstName        Salary\n");
		rs = testObj.getNameSalary();
		while (rs.next()) {
			System.out.printf("%-26s %s \n", rs.getString(1) + ", " + rs.getString(2), 
					                         rs.getString(3));
		}
	}

	// test matchName() method
	static void callMatchName() throws SQLException {
		ResultSet rs;
		String target;
		target = "K";
		System.out.println("\nEmployees with name that starts with " + target);
		System.out.println("***************************************************");
		System.out.printf("%-12s  %s\n", "Dept Number",   "LastName, FirstName");
		rs = testObj.matchLastName(target);
		while (rs.next()) {
			System.out.printf("    %-8s    %s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3));
		}
	}
	
	 
	//test employeeByDNO() method 
	static void callEmployeeByDNO() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
		System.out.println("\nEmployees that work in department " + dno); 
		System.out.println("*******************************************");
		System.out.printf("%-12s   %s\n", "Dept Number",   "LastName, FirstName");
		rs = testObj.employeeByDNO(dno); 
		while(rs.next()){ 
			System.out.printf("    %-8s     %s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3));
			}
		
	}
	
		
}//MyUtilitiesTest	
