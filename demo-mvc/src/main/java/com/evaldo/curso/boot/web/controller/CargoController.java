package com.evaldo.curso.boot.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String salvar(@Valid Cargo cargo, BindingResult result,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		cargoService.salvar(cargo);
        attr.addFlashAttribute("success", "Cargo salvo com sucesso.");		
		return "redirect:/cargos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("cargo",cargoService.buscarPorId(id));
		return "/cargo/cadastro";
	}
	
	
	@PostMapping("/editar")
	public String editar(@Valid Cargo cargo,BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()) {
			return "cargo/cadastro";
		}
		
		cargoService.editar(cargo);
		redirectAttributes.addFlashAttribute("success", "Cargo editado com sucesso.");
		return "redirect:/cargos/cadastrar";
	}
	
	
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamento(){
		return departamentoService.buscarTodos();
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes  redirectAttributes){
		if(cargoService.cargoTemFuncionario(id)) {
			redirectAttributes.addFlashAttribute("fail", "Cargo n??o pode ser exclu??do pois possui funcion??rio atrelados.");
		}
		else {
			cargoService.excluir(id);
			redirectAttributes.addFlashAttribute("success", "Cargo excluido com sucesso.");
		}
		return "redirect:/cargos/listar";
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
