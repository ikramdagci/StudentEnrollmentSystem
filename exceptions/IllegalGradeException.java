package com.ikramdg.exceptions;

public class IllegalGradeException extends Exception{

	public IllegalGradeException() {
		super("Grade MUST be between 0-8");
	}
}
