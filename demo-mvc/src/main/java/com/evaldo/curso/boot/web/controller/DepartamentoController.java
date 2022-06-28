package com.evaldo.curso.boot.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evaldo.curso.boot.domain.Departamento;
import com.evaldo.curso.boot.service.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Departamento departamento) {
		return "/departamento/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("departamentos", departamentoService.buscarTodos());
		return "/departamento/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Departamento departamento,BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()) {
			return "departamento/cadastro";
		}
		departamentoService.salvar(departamento);
		redirectAttributes.addFlashAttribute("success", "Departamento Salvo com sucesso.");
		
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model){
		model.addAttribute("departamento",departamentoService.buscarPorId(id));
		return "/departamento/cadastro";
	}
	@PostMapping("/editar")
	public String editar(@Valid Departamento departamento,BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()) {
			return "departamento/cadastro";
		}
		departamentoService.editar(departamento);
		redirectAttributes.addFlashAttribute("success", "Departamento editado com sucesso.");
		return "redirect:/departamentos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model){
		if(departamentoService.departamentoTemcargos(id)) {
			model.addAttribute("fail", "Departamento não pode ser excluído pois possui cargos atrelados.");
		}
		else {
			departamentoService.excluir(id);
			model.addAttribute("success", "Departamento excluido com sucesso.");
		}
		return listar(model);
	}

}
