package org.example.ex2.controllers;

import org.example.ex2.models.Student;
import org.example.ex2.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping
    public ModelAndView list(
            @RequestParam(defaultValue = "") String q,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {


        List<Student> students = studentService.findAll(q, sort, dir, page, size);


        int totalItems = studentService.count(q);
        int totalPages = (int) Math.ceil((double) totalItems / size);

        ModelAndView modelAndView = new ModelAndView("student-form");
        modelAndView.addObject("students", students);


        modelAndView.addObject("q", q);
        modelAndView.addObject("sort", sort);
        modelAndView.addObject("dir", dir);
        modelAndView.addObject("page", page);
        modelAndView.addObject("size", size);
        modelAndView.addObject("totalPages", totalPages);
        modelAndView.addObject("totalItems", totalItems);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable String id, Model model, RedirectAttributes ra) {
        Optional<Student> studentOpt = studentService.findById(id);

        if (studentOpt.isEmpty()) {
            ra.addFlashAttribute("message", "Sinh viên có mã số " + id + " không tồn tại!");
            return "redirect:/students";
        }

        model.addAttribute("student", studentOpt.get());
        return "student-detail";
    }

    // 3. Thêm sinh viên (GET/POST /students/add)
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("studentForm", new Student());
        return "student-add";
    }


    @PostMapping("/add")
    public String doAdd(@ModelAttribute("studentForm") Student studentForm,
                        BindingResult binding,
                        RedirectAttributes ra) {

        if (studentForm.getId().trim().isEmpty()) {
            binding.rejectValue("id", "id.empty", "Mã số không được để trống.");
        } else if (studentForm.getId().length() < 3 || studentForm.getId().length() > 20) {
            binding.rejectValue("id", "id.length", "Mã số phải dài từ 3 đến 20 ký tự.");
        } else if (studentService.existsById(studentForm.getId())) {
            binding.rejectValue("id", "id.duplicate", "Mã số này đã tồn tại.");
        }


        if (studentForm.getName().trim().isEmpty()) {
            binding.rejectValue("name", "name.empty", "Họ tên không được để trống.");
        } else {
            studentForm.setName(studentForm.getName().trim().replaceAll("\\s+", " ")); // Loại bỏ khoảng trắng dư thừa
        }


        if (studentForm.getGpa() < 0.0f || studentForm.getGpa() > 10.0f) {
            binding.rejectValue("gpa", "gpa.range", "Điểm tổng kết phải nằm trong khoảng 0.0 đến 10.0.");
        }

        if (binding.hasErrors()) {
            return "student-add";
        }


        studentService.save(studentForm);
        ra.addFlashAttribute("message", "Thêm sinh viên **" + studentForm.getId() + "** thành công!");
        return "redirect:/students";
    }


    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model, RedirectAttributes ra) {
        Optional<Student> studentOpt = studentService.findById(id);

        if (studentOpt.isEmpty()) {
            ra.addFlashAttribute("message", "Sinh viên có mã số " + id + " không tồn tại!");
            return "redirect:/students";
        }

        model.addAttribute("studentForm", studentOpt.get());
        return "student-edit";
    }


    @PostMapping("/{id}/edit")
    public String doEdit(@PathVariable String id,
                         @ModelAttribute("studentForm") Student studentForm,
                         BindingResult binding,
                         RedirectAttributes ra) {


        studentForm.setId(id);

        if (studentForm.getName().trim().isEmpty()) {
            binding.rejectValue("name", "name.empty", "Họ tên không được để trống.");
        } else {
            studentForm.setName(studentForm.getName().trim().replaceAll("\\s+", " "));
        }

        if (studentForm.getGpa() < 0.0f || studentForm.getGpa() > 10.0f) {
            binding.rejectValue("gpa", "gpa.range", "Điểm tổng kết phải nằm trong khoảng 0.0 đến 10.0.");
        }


        if (binding.hasErrors()) {
            return "student-edit";
        }

        studentService.save(studentForm);
        ra.addFlashAttribute("message", "Sửa sinh viên **" + id + "** thành công!");
        return "redirect:/students";
    }


    @PostMapping("/{id}/delete")
    public String doDelete(@PathVariable String id, RedirectAttributes ra) {
        Optional<Student> studentOpt = studentService.findById(id);

        if (studentOpt.isPresent()) {
            studentService.deleteById(id);
            ra.addFlashAttribute("message", "Xóa sinh viên **" + id + "** thành công!");
        } else {
            ra.addFlashAttribute("message", "Lỗi: Không tìm thấy sinh viên **" + id + "** để xóa!");
        }

        return "redirect:/students";
    }
}