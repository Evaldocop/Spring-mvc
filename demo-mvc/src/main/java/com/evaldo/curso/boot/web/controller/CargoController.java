package com.evaldo.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evaldo.curso.boot.domain.Cargo;
import com.evaldo.curso.boot.domain.Departamento;
import com.evaldo.curso.boot.service.CargoService;
import com.evaldo.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoController {
	
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Cargo cargo) {
		return "/cargo/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("cargos", cargoService.buscarTodos());
		return "/cargo/lista";
	}
	
	
	@PostMapping("/salvar")
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo salvo com sucesso.");		
		return "redirect:/cargos/cadastrar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamento(){
		return departamentoService.buscarTodos();
	}
	
	/* outra maneira de levar a lista 
	 * para view, ver opcao em listar 
	 * 
	 * 
	 * @ModelAttribute("cargos") 
	 * public List<Cargo> listaCargo(){ 
	 * return  cargoService.buscarTodos(); 
	 * }
	 */

}
