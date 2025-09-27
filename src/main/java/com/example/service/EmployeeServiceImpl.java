package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.entity.Employee;
import com.example.repo.EmployeeRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmpService {

	@Autowired
	private EmployeeRepo repo;
	
	@Override
	public Employee saveEmp(Employee emp) {
		Employee entity = repo.save(emp);
		if(entity!=null) {
			return entity;
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmp() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Employee getEmpById(int id) {
		
		return repo.findById(id).get();
	}

	@Override
	public boolean deleteEmp(int id) {
		Employee emp = repo.findById(id).get();
		if(emp!=null) {
			repo.delete(emp);
			return true;
		}
		return false;
	}
	
	public void remSessionMsg() {
		HttpSession session = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
		session.removeAttribute("msg");
	}

}
