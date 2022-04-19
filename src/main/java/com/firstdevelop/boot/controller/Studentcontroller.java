package com.firstdevelop.boot.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
//		for(int i = 0; i < stu_list.size(); i++) {
//			System.out.println(stu_list.get(i));
//		}
//		int sumAge = stu_list.get(0).getAge();
//		for(int g = 0; g < stu_list.size(); g++) {
//			sumAge += stu_list.get(g).getAge();
//			
//		}
//		System.out.println(sumAge);
//		
//		int sumScore = (int) stu_list.get(0).getScore();
//		for(int h = 0; h < stu_list.size(); h++) {
//			sumScore += stu_list.get(h).getScore();
//			
//		}
//		System.out.println(sumScore);
//		
//		int maxAge = stu_list.get(0).getAge();
//		for(int a = 0; a < stu_list.size(); a++) {
//			if(stu_list.get(1).getAge() < stu_list.get(a).getAge()){
//				maxAge = stu_list.get(a).getAge();
//			}
//		}
//		System.out.println(maxAge);
//		
//		int maxScore = (int) stu_list.get(0).getScore();
//		for(int b = 1; b < stu_list.size(); b++) {
//			if(stu_list.get(0).getScore() < stu_list.get(b).getScore()){
//				maxScore = (int) stu_list.get(b).getScore();
//			}
//		}
//		System.out.println(maxScore);
//		
		

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
}