package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.fra.uas.service.Kurs;
import edu.fra.uas.service.Studiengang;

@Controller
public class NotenController {

    @Autowired
    private Studiengang studiengang;

    @GetMapping("/kurs")
    public String showKursManagement(Model model) {
        model.addAttribute("kurse", studiengang.getKurse());
        return "kurs-management";
    }

    @PostMapping("/kurs/add")
    public String addKurs(@RequestParam String name, 
                         @RequestParam int code, 
                         @RequestParam int semester) {
        Kurs newKurs = new Kurs(name, code, semester);
        studiengang.addKurs(newKurs);
        return "redirect:/kurs";
    }

    @PostMapping("/kurs/{code}/delete")
    public String deleteKurs(@PathVariable int code) {
        studiengang.getKurse().removeIf(k -> k.getCode() == code);
        return "redirect:/kurs";
    }

    @GetMapping("/kurs/{code}/noten")
    public String showNotenManagement(@PathVariable int code, Model model) {
        Kurs kurs = studiengang.getKurse().stream()
                              .filter(k -> k.getCode() == code)
                              .findFirst()
                              .orElseThrow();
        model.addAttribute("kurs", kurs);
        return "noten-management";
    }

    @PostMapping("/kurs/{code}/note/add")
    public String addNote(@PathVariable int code,
                         @RequestParam double note,
                         @RequestParam int gewichtung) {
        Kurs kurs = studiengang.getKurse().stream()
                              .filter(k -> k.getCode() == code)
                              .findFirst()
                              .orElseThrow();
        kurs.addNote(note, gewichtung);
        return "redirect:/kurs/" + code + "/noten";
    }

    @PostMapping("/kurs/{code}/note/{index}/delete")
    public String deleteNote(@PathVariable int code, @PathVariable int index) {
        Kurs kurs = studiengang.getKurse().stream()
                              .filter(k -> k.getCode() == code)
                              .findFirst()
                              .orElseThrow();
        kurs.removeNote(index);
        return "redirect:/kurs/" + code + "/noten";
    }
}
