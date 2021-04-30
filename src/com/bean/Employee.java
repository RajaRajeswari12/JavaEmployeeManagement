package com.bean;

import java.util.ArrayList;

import com.exception.IdExistException;
import com.exception.NotAValidAgeException;

public class Employee {
	Integer id;
	String name;
	Integer age;
	static ArrayList<Integer> idList = new ArrayList<>();
	public Integer getId() {

		return id;
	}
	public void setId(Integer id) throws IdExistException {
		if(!idList.contains(id)) {
			this.id = id;
			
		}else {
			throw new IdExistException("ID already exist.");
		}

	}
	public String getName() {
		return name;
	}
	public void setName(String name) {

		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) throws NotAValidAgeException {
		if(age > 21) {
			this.age = age;
			
		}else {
			throw new NotAValidAgeException("Age must be above 21");
		}
	}
	public ArrayList<Integer> getIdList() {
		return idList;
	}
	public void setIdList() {
		if(!(id.equals(null) && name.equals(null) && age.equals(null))) {
			idList.add(id);
		}
		this.idList = idList;
	}
}
