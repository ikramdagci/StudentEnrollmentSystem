package com.ikramdg.util;

import java.util.Scanner;

import com.ikramdg.domain.Automation;
import com.ikramdg.domain.Automation.Builder;
import com.ikramdg.exceptions.IllegalGradeException;
import com.ikramdg.exceptions.StudentNotFoundException;

public class Server {

	static Automation schoolAutomation;
	static Builder studentBuilder = new Builder();
	static Scanner input = new Scanner(System.in);
	static int choice;

	public static void loginAutomation(String schoolName) throws StudentNotFoundException,IllegalGradeException{
		schoolAutomation = new Automation(schoolName);
		displayWelcomeMessage(schoolName);
		run();
	}

	private static void displayWelcomeMessage(String schoolName) {
		System.out.println("Welcome to " + schoolName + "'s student registration system!");
	}

	private static void displayOperations() {
		System.out.println("1. Register new student\n2. Graduate student\n3. Get student informations by student ID\n"
				+ "4. Get student informations by student first name\n5. Get student informations by student last name\n"
				+ "6. Increase student's grade\n7. Expel student\n8. List all Students\n\n9. Log out.");
	}

	private static void askOperationInput() {
		System.out.println("\nEnter the operation number you want to do\n");
	}

	private static boolean askInputForAddStudent() throws IllegalGradeException{
		System.out.println("Please enter student informations\n\nName:");
		input.nextLine();
		String firstName = input.nextLine();
		System.out.println("Last Name:");
		String lastName = input.nextLine();
		System.out.println("Grade:");
		int grade = input.nextInt();
		return schoolAutomation.addNewStudent(studentBuilder.firstName(firstName).lastName(lastName).grade(grade));
	}
	
	private static void listAllStudents() {
		System.out.println(schoolAutomation.listStudents());
	}

	private static boolean askStudentIdForExpelStudent() throws StudentNotFoundException {
		System.out.println("Please enter student ID for expel the student\nID :");
		input.nextLine();
		String id = input.nextLine();
		return schoolAutomation.expelStudent(id);
	}

	private static boolean askStudentIdForGraduateStudent() throws StudentNotFoundException {
		System.out.println("Please enter student ID for graduate the student\nID :");
		input.nextLine();
		String id = input.nextLine();
		return schoolAutomation.graduateStudent(id);
	}

	private static String askStudentFirstNameForGetInformations() throws StudentNotFoundException {
		System.out.println("Please enter student first name for get informations\nFirst Name :");
		input.nextLine();
		String firstName = input.nextLine();
		return schoolAutomation.getStudentByFirstName(firstName);
	}

	private static String askStudentLastNameForGetInformations() throws StudentNotFoundException {
		System.out.println("Please enter student last name for get informations\nLast Name :");
		String lastName = input.nextLine();
		return schoolAutomation.getStudentByFirstName(lastName);
	}

	private static String askStudentIdForGetInformations() throws StudentNotFoundException {
		System.out.println("Please enter student ID for get informations\nID :");
		input.nextLine();
		String id = input.nextLine();
		return schoolAutomation.getStudentById(id);
	}

	private static void askStudentIdForIncreaseGrade() throws StudentNotFoundException {
		System.out.println("Please enter student ID for increase grade\nID :");
		input.nextLine();
		String id = input.nextLine();
		schoolAutomation.increaseStudentGrade(id);
	}

	private static void run() throws StudentNotFoundException, IllegalGradeException{
		boolean flag = true;
		while (flag) {
			askOperationInput();
			displayOperations();
			choice = input.nextInt();
			switch (choice) {
			case 1:
				if(askInputForAddStudent()) System.out.println("\n**********Student registered**********");;
				continue;
			case 2:
				if(askStudentIdForGraduateStudent()) System.out.println("Student has graduated");;
				continue;
			case 3:
				System.out.println(askStudentIdForGetInformations());
				continue;
			case 4:
				System.out.println(askStudentFirstNameForGetInformations());
				continue;
			case 5:
				System.out.println(askStudentLastNameForGetInformations());
				continue;
			case 6:
				askStudentIdForIncreaseGrade();
				continue;
			case 7:
				if(askStudentIdForExpelStudent()) System.out.println("Student expelled.");;
				continue;
			case 8:
				listAllStudents();
				continue;
			case 9:
				System.out.println("Logging out...");
				flag = false;
				break;
			default:
				System.err.println("You entered an incorrect entry. Do you want to terminate it? (Y/N)");
				input.nextLine();
				String selection = input.nextLine();
				if(selection.equalsIgnoreCase("y")) {
					System.out.println("Terminating...");
					flag = false;
					break;
				}
				continue;
			}

		}

	}
}
