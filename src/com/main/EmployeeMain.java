package com.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.bean.Employee;
import com.exception.AgeUpdationException;
import com.exception.EmployeeNotFoundException;
import com.exception.IdExistException;
import com.exception.NotAValidAgeException;
import com.service.EmployeeList;


public class EmployeeMain {
	EmployeeList employees = new EmployeeList();
	Employee employee;
	String[] mainMenuList = {"Add an Employee","Delete an EmployeeDetail","Update Age of an Employee","Display Employee Detail","Display All Employee Details","Quit"};
	Scanner getUserInput;

	int empId;
	int empAge;

	public EmployeeMain()
	{
		getUserInput = new Scanner(System.in);
	}

	void displayMenu() 
	{
		int index = 1;
		for(String menuItem:mainMenuList) 
		{
			System.out.println(index+".) "+menuItem);
			index++;
		}
	}

	void mainMenu() 
	{
		System.out.println("Enter No[1-6] to select the option :");
		displayMenu();
		int menuOption =0 ;
		boolean isValueSet = false;
		String inputValue = getUserInput.next();
		while(!isValueSet) 
		{
			if(inputValue.matches("[0-9]+"))
			{
				menuOption =Integer.parseInt(inputValue);
				isValueSet =true;
			}
			else 
			{
				System.out.println("Enter No[1-6] to select the option :");
				inputValue = getUserInput.next();
			}
		}

		switch(menuOption) 
		{
		case 1:
			System.out.println("Enter the Details of the Employee ");
			addEmployee();
			break;
		case 2:
			deleteEmployee();
			break;
		case 3:
			updateAge() ;
			break;
		case 4:
			displayEmp();
			break;
		case 5:
			employees.displayAll();
			mainMenu();
			break;
		case 6:
			quitFunction();
			break;
		default:
			mainMenu();
			break;
		}
	}


	private  void getEmpIdAsInput(int methodNo)
	{	
		String inputValue = getUserInput.next();
		if(inputValue.matches("[0-9]+")) 
		{
			empId =Integer.parseInt(inputValue);		
		}
		else
		{
			switchMethods(methodNo);
		}

	}

	private  void getEmpAgeAsInput(int methodNo)
	{	
		String inputValue = getUserInput.next();
		if(inputValue.matches("[0-9]+")) 
		{
			empAge =Integer.parseInt(inputValue);		
		}
		else
		{
			switchMethods(methodNo);
		}

	}

	private void switchMethods(int methodNo) {
		switch(methodNo) {
		case 1:
			setEmpId();
			break;
		case 2:
			setEmpAge();
			break;
		case 3:
			deleteEmployee();
			break;
		case 4:
			getEmpId();
			break;
		case 5:
			getAgeToBeUpdated();
			break;
		case 6:
			displayEmp();
			break;
		case 7:
			mainMenu();
			break;
		}


	}


	private void updateAge() {

		getEmpId();
		getAgeToBeUpdated();
		try {
			employees.updateAge( empId, empAge);
			System.out.println("Employee Age updated successfully");
			mainMenu();
		} catch (NotAValidAgeException | AgeUpdationException e) {
			System.out.println(e.getMessage());
			mainMenu();
		} catch (EmployeeNotFoundException e) {
			System.out.println(e.getMessage());
			mainMenu();
		}

	}

	private int getEmpId() {
		System.out.println("Enter the Id of the Employee to be updated ");
		getEmpIdAsInput(4);
		return empId;
	}


	private int getAgeToBeUpdated() {
		System.out.println("Enter the Age of the Employee to be updated ");
		getEmpAgeAsInput(5);
		return empId;
	}

	private void displayEmp() {
		System.out.println("Enter the Id of the Employee details to be displayed ");
		getEmpIdAsInput(6);
		employees.display(empId);
		mainMenu();
	}

	private void deleteEmployee() 
	{
		System.out.println("Enter the Id of the Employee to be deleted ");
		getEmpIdAsInput(3);
		employees.deleteEmployee(empId);
		mainMenu();
	}

	public void setEmpId() {

		System.out.println("Enter the Employee Id :");
		getEmpIdAsInput(1);		
		try {
			if(empId!=0)
				employee.setId(empId);
		} catch (IdExistException e) {
			System.out.println(e.getMessage() + " Kindly Enter Different Employee Id ");
			setEmpId();
		}
	}

	public void setEmpAge() 
	{
		System.out.println("Enter the Employee age :");
		getEmpAgeAsInput(2);
		try 
		{
			employee.setAge(empAge);
		} catch (NotAValidAgeException e) 
		{
			System.out.println(e.getMessage()+" Come Back When you are above 21 ");
			mainMenu();
		}
	}

	private void addEmployee() 
	{
		employee = new Employee();

		setEmpId();
		setEmpName() ;
		setEmpAge() ;

		employees.addEmployee(employee);
		mainMenu();
	}

	public void setEmpName() 
	{
		System.out.println("Enter the Employee name :");
		String name = getUserInput.next();
		employee.setName(name);
	}


	private void quitFunction() 
	{
		System.out.println("Thanks for using this Employee details handling Application");
		System.exit(0);
	}

	public static void main(String[] args) 
	{
		EmployeeMain empMain = new EmployeeMain();

		try 
		{
			empMain.mainMenu();
		}
		catch(InputMismatchException e) 
		{
			System.out.println("Input MisMatch Exception try again with correct input data" );

		}
	}
}
