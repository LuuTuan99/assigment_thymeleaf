package com.fpt.controller;

import com.fpt.entity.Student;
import com.fpt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("students", studentService.getList());
        return "list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create")
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("student", studentService.getDetail(id));
        return "detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public String store(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        studentService.create(student);
        return "redirect:/students";
    }
}
