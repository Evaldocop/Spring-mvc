package com.evaldo.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.evaldo.curso.boot.domain.Cargo;
import com.evaldo.curso.boot.domain.Funcionario;
import com.evaldo.curso.boot.domain.UF;
import com.evaldo.curso.boot.service.CargoService;
import com.evaldo.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private CargoService cargoService;

	@GetMapping("/cadastrar")
	public String Cadastro(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap modelMap) {
		modelMap.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}

	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes redirectAttributes) {
		funcionarioService.salvar(funcionario);
		redirectAttributes.addFlashAttribute("success", "Funcionario Salvo com sucesso.");

		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "/funcionario/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes redirectAttributes) {
		funcionarioService.editar(funcionario);
		redirectAttributes.addFlashAttribute("success", "Funcionario editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		funcionarioService.excluir(id);
		redirectAttributes.addFlashAttribute("success", "Funcionario excluido com sucesso.");
		return "redirect:/funcionarios/listar";
	}
	
	
	@GetMapping("/buscar/nome")
	private String porNome(@RequestParam("nome") String nome, ModelMap model ) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "/funcionario/lista";
	}
	@GetMapping("/buscar/cargo")
	private String porCargo(@RequestParam("id") Long id, ModelMap model ) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorCargo(id));
		return "/funcionario/lista";
	}
	@ModelAttribute("cargos")
	public List<Cargo> getCargos() {
		return cargoService.buscarTodos();
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();

	}

}
