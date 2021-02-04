package com.ikramdg.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.ikramdg.exceptions.IllegalGradeException;
import com.ikramdg.exceptions.StudentNotFoundException;

public class Automation {

	private Student student;
	private static ArrayList<String> studentsWithSpecifiedName = new ArrayList<String>();
	private static Set<Student> students = new HashSet<Student>();
	private static Set<Student> graduatedStudents = new HashSet<Student>();
	private static Set<Student> failOutStudents = new HashSet<Student>();
	static Locale locale = new Locale("tr", "TR");
	public final String schoolName;

	public Automation(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public boolean addNewStudent(Builder studentBuilder) {
		student = new Student(studentBuilder);
		return students.add(student);
	}
	
	public boolean graduateStudent(String studentId) throws StudentNotFoundException {
		Student student = searchStudentById(studentId);
		graduatedStudents.add(student);
		return students.remove(searchStudentById(studentId));
	}
	
	public boolean expelStudent(String studentId) throws StudentNotFoundException {
		Student student = searchStudentById(studentId);
		failOutStudents.add(student);
		return students.remove(searchStudentById(studentId));
	}

	public void increaseStudentGrade(String studentId) throws StudentNotFoundException {
		Student student = searchStudentById(studentId);
		student.setGrade((student.getGrade() + 1));
	}

	public String getStudentById(String studentId) throws StudentNotFoundException {
		return searchStudentById(studentId).toString();
	}

	public String getStudentByFirstName(String firstName) throws StudentNotFoundException {
		return searchStudentByFirstName(firstName);

	}

	public String getStudentByLastName(String lastName) throws StudentNotFoundException {
		return searchStudentByLastName(lastName);

	}
	
	public Set<Student> listStudents(){
		return students;
	}

	private Student searchStudentById(String studentId) throws StudentNotFoundException {
		for (Student searchingStudent : students) {
			if (searchingStudent.getId().equals(studentId)) {
				return searchingStudent;
			}
		}
		throw new StudentNotFoundException();
	}

	private String searchStudentByFirstName(String firstName) throws StudentNotFoundException {
		for (Student searchingStudent : students) {
			if (searchingStudent.getFirstName().contains(firstName.toLowerCase(locale))) {
				studentsWithSpecifiedName.add(searchingStudent.toString());
			}
		}
		if (studentsWithSpecifiedName.size() == 0)
			throw new StudentNotFoundException();
		String[] studentsWithSearchingName = studentsWithSpecifiedName
				.toArray(new String[studentsWithSpecifiedName.size()]);
		studentsWithSpecifiedName.clear();
		return Arrays.toString(studentsWithSearchingName);
	}

	private String searchStudentByLastName(String lastName) throws StudentNotFoundException {
		for (Student searchingStudent : students) {
			if (searchingStudent.getLastName().contains(lastName.toLowerCase(locale))) {
				studentsWithSpecifiedName.add(searchingStudent.toString());
			}
		}
		if (studentsWithSpecifiedName.size() == 0)
			throw new StudentNotFoundException();
		String[] studentsWithSearchingName = studentsWithSpecifiedName
				.toArray(new String[studentsWithSpecifiedName.size()]);
		studentsWithSpecifiedName.clear();
		return Arrays.toString(studentsWithSearchingName);
	}
	
	
	 // TODO private String searchStudentByFullName(String fullName)

	private static class Student {
		
		private static int idCounter;
		private Integer tempId = ++idCounter;
		private String id = idGenerator.toString(); 
		private String firstName;
		private String lastName;
		private int grade;
		private double score;

		public Student(Builder builder) {
			firstName = builder.firstName;
			lastName = builder.lastName;
			grade = builder.grade;

		}

		public String getId() {
			return id;
		}

		private String getFirstName() {
			return firstName.toLowerCase(locale);
		}

		private void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		private String getLastName() {
			return lastName.toLowerCase(locale);
		}

		private void setLastName(String lastName) {
			this.lastName = lastName;
		}

		private int getGrade() {
			return grade;
		}

		private void setGrade(int grade) {
			this.grade = grade;
		}
		
		private void setScore(double score) {
			this.score = score;
		}
		
		private double getScore() {
			return this.score;
		}

		@Override
		public int hashCode() {
			return idGenerator;
		}

		@Override
		public String toString() {
			return "\nFirst Name            : " + this.firstName + "\nLast Name             : " + this.lastName
					+ "\nIdentification Number : " + this.id + "\nGrade                 : " + this.grade
					+ "\n*****************************";
		}

		@Override
		public boolean equals(Object obj) {
			return this.hashCode() == obj.hashCode();
		}

	}

	/**
	 * Separate the construction of a complex object from its representation 
	 * so that the same construction process can create different representations
	 * 
	 * @author ikramdagci
	 */
	public static class Builder {

		private String id;
		private String firstName;
		private String lastName;
		private int grade;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder grade(int grade) throws IllegalGradeException {
			if (grade <= 0 || grade > 8)
				throw new IllegalGradeException();
			this.grade = grade;
			return this;
		}

	}

}
