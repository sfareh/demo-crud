package com.adminbookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adminbookstore.model.Employe;
import com.adminbookstore.service.EmployeService;

@Controller
public class EmployeController {

	@Autowired
	private EmployeService employeService;

	/**
	 * 
	 * @param model le model contient les données
	 * @return le template de la page index
	 */
	@GetMapping("/")
	public String homePage(Model model) {
		return pagination(1,"firstName", "asc", model);
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/newEmployeForm")
	public String addEmploye(Model model) {

		Employe employe = new Employe();
		model.addAttribute("employe", employe);
		return "addEmploye";
	}

	@PostMapping("saveEmploye")
	public String saveEmploye(@ModelAttribute("employe") Employe employe) {
		employeService.saveEmploye(employe);

		return "redirect:/";
	}

	@GetMapping("/updateEmployeForm/{id}")
	public String updateEmploye(@PathVariable(value = "id") Long id, Model model) {
		Employe employe = employeService.getEmployeById(id);
		model.addAttribute("employe", employe);

		return "updateEmploye";
	}

	@GetMapping("/deleteEmploye/{id}")
	public String deleteEmploye(@PathVariable(value = "id") Long id) {
		employeService.deleteEmploye(id);

		return "redirect:/";
	}

	@GetMapping("/page/{pageNumber}")
	public String pagination(@PathVariable(value = "pageNumber") int pageNumber, @RequestParam(name = "field") String field,
			@RequestParam(name = "direction") String direction, Model model) {
		int rowSize = 5;

		Page<Employe> page = employeService.getEmployePagination(pageNumber, rowSize, field, direction);
		List<Employe> listEmploye = page.getContent();

		model.addAttribute("currentPage", pageNumber);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("field", field);
		model.addAttribute("direction", direction);
		model.addAttribute("reverseDirection", direction.equalsIgnoreCase("asc") ? "desc" : "asc");
		model.addAttribute("listEmploye", listEmploye);
		return "index";
	}
}
