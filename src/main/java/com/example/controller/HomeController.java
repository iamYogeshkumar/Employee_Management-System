package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Employee;
import com.example.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	@Autowired
	private EmpService service;
	
	@GetMapping("/")
	public String index(Model m ) {
		List<Employee> allEmp = service.getAllEmp();
		m.addAttribute("empList",allEmp);
		return "index";
	}

	@GetMapping("/loadEmpSave")
	public String loadEmpSave() {
		return "emp_save";
	}

	@GetMapping("/editEmp/{id}")
	public String editEmp(@PathVariable int id,Model m) {
		Employee employee = service.getEmpById(id);
		m.addAttribute("emp", employee);
		return "edit_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute Employee emp,HttpSession  session) {
		System.out.println(emp);
		
		Employee saveEmp = service.saveEmp(emp);
		if(saveEmp!=null) {
			session.setAttribute("msg", "Register Successfully");
		}else {
			session.setAttribute("msg", "something wrong on server");
		}
		
		return "redirect:/loadEmpSave";
	}

	
	@PostMapping("/updateEmpDtls")
	public String updateEmp(@ModelAttribute Employee emp,HttpSession  session) {
		System.out.println(emp);
		
		Employee updateEmp = service.saveEmp(emp);
		if(updateEmp!=null) {
			session.setAttribute("msg", "Updated successfully");
		}else {
			session.setAttribute("msg", "something wrong on server");
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/deleteEmp/{id}")
	public String deleteEmp(@PathVariable int id,Model m,HttpSession session) {
       boolean status = service.deleteEmp(id);
		if(status) {
			session.setAttribute("msg", "Deleted Successfully");
		}
		else {
			session.setAttribute("msg", "something wrong on server");
		}
		return "redirect:/";
	}
}
