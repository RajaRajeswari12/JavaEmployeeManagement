package com.service;
import java.util.ArrayList;

import com.bean.Employee;
import com.exception.AgeUpdationException;
import com.exception.EmployeeNotFoundException;
import com.exception.NotAValidAgeException;
public class EmployeeList {

	static ArrayList<Employee> employeeList = new ArrayList<>();

	public void addEmployee(Employee employee)
	{
		employee.setIdList();
		employeeList.add(employee);

	}


	public void deleteEmployee(Integer id) 
	{
		boolean isEmployeeDetailDeleted = employeeList.removeIf(emp->emp.getId().equals(id));
		if(isEmployeeDetailDeleted) 
		{
			System.out.println("Employee details deleted successfully");
		}
		else 
		{
			System.out.println("Employee Id not found");
		}
	}

	public boolean updateAge(int id,int age) throws  AgeUpdationException, NotAValidAgeException, EmployeeNotFoundException 
	{
		boolean updated = false;
		int age1 = 0;
		Employee employee ;
		if(employeeList.stream().anyMatch(emp->emp.getId().equals(id))) 
		{
			
			employee = 	employeeList.stream().filter(emp->emp.getId().equals(id)).findAny().get();

			if(employee.getAge() < age) 
			{
				System.out.println("Employee Age "+(age-employee.getAge()));

				if(age-employee.getAge() <= 2)
				{
					employee.setAge(age);
					updated =true;
				}
				else 
				{
					throw new AgeUpdationException("Age given for updation is too high");
				}
			}
			else 
			{
				throw new AgeUpdationException("Age given for updation is lesser than the given age. Kindly check it");
			}
			/*for(Employee empdetail: employeeList) 
			{
				if(empdetail.getId().equals(id))
				{
					updated = true;
					if(empdetail.getAge() < age) 
					{
						System.out.println("Employee Age "+(age-empdetail.getAge()));

						if(age-empdetail.getAge() <= 2)
						{
							empdetail.setAge(age);
						}
						else 
						{
							throw new AgeUpdationException("Age given for updation is too high");
						}
					}
					else 
					{
						throw new AgeUpdationException("Age given for updation is lesser than the given age. Kindly check it");
					}
				}
			}*/
		}
		else 
		{
			throw new EmployeeNotFoundException("Employee does not exist");
		}
		return updated;
	}

	public void display(int id)
	{
		if(employeeList.stream().anyMatch(emp->emp.getId().equals(id))) 
		{
			employeeList.stream().filter(emp->emp.getId().equals(id)).forEach(emp -> System.out.println("Employee Id: "+emp.getId()+"\nEmployee Name: "+emp.getName()+"\nEmployee Age: "+emp.getAge()+"\n"));
		}
		else 
		{
			System.out.println("Details of the Employee is not available ");
		}
	}

	public void displayAll() 
	{
		if(employeeList.size() > 0) 
		{
			System.out.println("*-*-*-*- Details of the EMPLOYEES -*-*-*-*");
			employeeList.forEach(emp -> System.out.println("Employee Id: "+emp.getId()+"\nEmployee Name: "+emp.getName()+"\nEmployee Age: "+emp.getAge()+"\n"));
		}
		else 
		{
			System.out.println("No Employee Details to be listed");
		}
	}
}
