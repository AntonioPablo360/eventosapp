package com.appevento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.appevento.models.Convidado;
import com.appevento.models.Evento;
import com.appevento.repository.ConvidadoRepository;
import com.appevento.repository.EventoRepository;

import jakarta.validation.Valid;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository er;

    @Autowired
    private ConvidadoRepository cr;
    

    @RequestMapping(value = "/cadastrarEvento",method = RequestMethod.GET)
    public String form(){
        return "evento/formEvento";
    }
    
    
    @RequestMapping(value = "/cadastrarEvento",method = RequestMethod.POST)
    public String form(@Valid Evento evento, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique os Campos!");
            return "redirect:/cadastrarEvento";
        }
        er.save(evento);
        attributes.addFlashAttribute("mensagem", "Evento Cadastrado Com Sucesso!");
        return "redirect:/cadastrarEvento";
    }
    
    
    //retorna lista de eventos
    @RequestMapping("/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }
    
    
    //mostra detalhe evento
    @RequestMapping(value="/{codigo}",method=RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo")long codigo){
        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);
        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);
        return mv;
    }
    

    @RequestMapping(value="/{codigo}",method=RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo")long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique Os Campos!");
            return"redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("mensagem", "Adicionado Com Sucesso!");
        return "redirect:/{codigo}";
    }


    //Deleta Eventos
    @RequestMapping("/deletarEvento")
    public String deletarEvento(Long codigo){
        Evento evento = er.findByCodigo(codigo);
        er.delete(evento);
        return "redirect:/eventos";
    }

    //Deletar Convidado
    @RequestMapping("/deletarConvidado")
    public String deletarConvidado(Long idConvidado){
        Convidado convidado = cr.findByIdConvidado(idConvidado);
        cr.delete(convidado);
        Evento evento = convidado.getEvento();
        long codigoLong = evento.getCodigo();
        String codigo = ""+ codigoLong;
        return "redirect:/"+codigo;
    }

}