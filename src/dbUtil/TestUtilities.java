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
			case 5: {
				callGetProjectDetails();
				break;
			}
			case 6: {
				callEmployeeProjectByDNO();
				break;
			}
			case 7: {
				openManual();
				break;
			}
			case 8: {
				callMatchWorkOnSameProject();
				break;
			}
			case 9: {
				callGetEmployeeHasNoPROJECT();
				break;
			}
			case 10: {
				callInsertNewArrayListData();
				break;
			}
			case 13: {
				testObj.closeDB(); //close the DB connection 
				break;
			}
			case 14: {
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
		System.out.println("5)  call getProjectDetails");
		System.out.println("6)  call employeeProjectByDNO()");
		System.out.println("7)  open manual DB ");
		System.out.println("8)  call matchWorkOnSameProject()");
		System.out.println("9)  call getEmployeeHasNoPROJECT()");
		System.out.println("10) call callInsertNewArrayListData()");
		System.out.println("13)  close the DB");
		System.out.println("14) quit");
	}

	static int getChoice() {
		String input;
		int i = 0;
		while (i < 1 || i > 15) {
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

	// open the manual database;
	static void openManual() {
		System.out.print("Please enter username: ");
		String username = keyboard.nextLine();
		System.out.print("Please enter password: ");
		String password = keyboard.nextLine();
		testObj.openDB(username,password);
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

	//test employeeProjectByDNO method 
	static void callEmployeeProjectByDNO() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter a department number: ");
		String input = keyboard.nextLine();
		int dno= Integer.parseInt(input); 
		System.out.println("\nEmployees that work in department " + dno); 
		System.out.println("*******************************************");
		System.out.printf("%s\t%s\t%s\n", "Project Number","LastName, FirstName","Hours");
		rs = testObj.employeeProjectByDNO(dno); 
		while(rs.next()){ 
			System.out.printf("     %s\t\t%s\t\t%s\n", rs.getString(3), 
					rs.getString(1) + ", " + rs.getString(2),rs.getString(4));
		}

	}	

	// test getProjectDetails() method
	static void callGetProjectDetails() throws SQLException {
		ResultSet rs;
		System.out.println("Project Details");
		System.out.println("*********************************************");
		System.out.printf("Project number\tNumber Employee\tTotal Hours\tAverage Hours\n");
		rs = testObj.getProjectDetails();
		while (rs.next()) {
			System.out.printf("      %s     \t\t%s\t%s\t\t%s\n", rs.getString(1),rs.getString(2), 
					rs.getString(3),rs.getString(4));
		}
	}

	//test matchWorkOnSameProject method 
	static void callMatchWorkOnSameProject() throws SQLException {
		ResultSet rs;
		System.out.print("Please enter employee firstname: ");
		String fname = keyboard.nextLine();
		System.out.print("Please enter employee lastname: ");
		String lname = keyboard.nextLine();
		System.out.println("\nEmployees that work in same project with " + fname + " " + lname); 
		System.out.println("******************************************************");
		System.out.printf("%s\t%s\t%s\n", "#_Department","LastName, FirstName","Salary");
		rs = testObj.matchWorkOnSameProject(fname,lname); 
		while(rs.next()){ 
			System.out.printf("  %s\t\t%s\t\t%s\n", rs.getString(1), 
					rs.getString(2) + ", " + rs.getString(3),rs.getString(4));
		}

	}

	// test getProjectDetails() method
	static void callGetEmployeeHasNoPROJECT() throws SQLException {
		ResultSet rs;
		System.out.println("Employee who does not work on any project");
		System.out.println("*********************************************");
		System.out.printf("Project number\tSalary\n");
		rs = testObj.getEmployeeHasNoPROJECT();
		while (rs.next()) {
			System.out.printf("%s\t%s\n", rs.getString(1),rs.getString(2));
		}
	}

	// test insertNewArrayListData()
	static void callInsertNewArrayListData(){
		int successRow;
		String [][] test = new String[3][3];
		for(int i = 0; i < test.length;i++){
			test[i][0] = "000110004";
			test[i][1] = ""+i+1;
			test[i][2] = "50";
		}
		System.out.println("Insert Default data into works_on");
		System.out.println("*********************************************");
		successRow = testObj.insertNewArrayListData(test);
		System.out.println("Susccess Row inserted: "+successRow);
	}

}//MyUtilitiesTest	
