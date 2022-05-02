package com.firstdevelop.boot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.firstdevelop.boot.entity.Student;
import com.firstdevelop.boot.entity.UserEntity;
import com.firstdevelop.boot.form.StudentForm;
import com.firstdevelop.boot.mapper.StudentMapper;
import com.firstdevelop.boot.service.StudentService;

@Controller
@RequestMapping("/student")
public class Studentcontroller {
	@Autowired
	private StudentService studentService;
	@Autowired
	private StudentMapper studentMapper;
	
	@RequestMapping("/searchAll")
	public String searchALL(Model model, HttpSession session) {
		UserEntity user = (UserEntity) session.getAttribute("user");
		//List<Student> stu_list = studentMapper.searchClassByStuID(user.getStuID());

		
		List<Student> stu_list = studentService.searchALL();
		model.addAttribute("stu_list",stu_list);
	return "/student/all";			
	}
	@RequestMapping("/add")
	public String add() {
		return "/student/add";
	}
	
	@RequestMapping("/insert")
	public String insert(StudentForm form) {
		studentService.insert(form);
		return "redirect:/student/searchAll";
	}
	
	@RequestMapping("/delete/{StuID}")
	public String delete(@PathVariable("StuID")int StuID) {
		System.out.println(StuID);
		studentMapper.delete(StuID);
		return "redirect:/student/searchAll";
	}
		
	
	
}