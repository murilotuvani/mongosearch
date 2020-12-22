package com.example.mongosearch.item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("home");
		return mv;
	}
	
	@PostMapping
	public ModelAndView save(Item item, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<>();
			for (ObjectError err : result.getAllErrors()) {
				erros.add(err.getDefaultMessage());
			}

			ModelAndView mv = new ModelAndView("home");
			mv.addObject("item", item);
			mv.addObject("mensagemErro", erros);

			return mv;
		}
		ModelAndView mv = new ModelAndView("redirect:/list");

		try {
			itemService.save(item);
		} catch (Exception ex) {
			mv.addObject("mensagemErro", "Não foi possível salvar/editar este cargo! ");
		}
		attributes.addFlashAttribute("mensagemSucesso", "Cargo alterado/cadastrado com sucesso!");
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("list");
		List<Item> items = itemService.list();
		mv.addObject("items", items);		
		return mv;
	}

}
