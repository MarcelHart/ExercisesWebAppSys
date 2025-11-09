package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.fra.uas.service.Kurs;
import edu.fra.uas.service.Studiengang;
import edu.fra.uas.exception.KursNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class NotenController {

    private static final Logger log = LoggerFactory.getLogger(NotenController.class);

    @Autowired
    private Studiengang studiengang;

    private Kurs findKursByCode(int code) {
        return studiengang.getKurse().stream()
                .filter(k -> k.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new KursNotFoundException(code));
    }

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
        try {
            Kurs kurs = findKursByCode(code);
            model.addAttribute("kurs", kurs);
            return "noten-management";
        } catch (KursNotFoundException e) {
            log.error("Error accessing kurs with code: {}", code, e);
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/kurs/{code}/note/add")
    public String addNote(@PathVariable int code,
                         @RequestParam double note,
                         @RequestParam int gewichtung) {
        try {
            Kurs kurs = findKursByCode(code);
            kurs.addNote(note, gewichtung);
            return "redirect:/kurs/" + code + "/noten";
        } catch (KursNotFoundException e) {
            log.error("Error adding note to kurs with code: {}", code, e);
            return "redirect:/kurs?error=" + e.getMessage();
        }
    }

    @PostMapping("/kurs/{code}/note/{index}/delete")
    public String deleteNote(@PathVariable int code, @PathVariable int index) {
        try {
            Kurs kurs = findKursByCode(code);
            kurs.removeNote(index);
            return "redirect:/kurs/" + code + "/noten";
        } catch (KursNotFoundException e) {
            log.error("Error deleting note from kurs with code: {}", code, e);
            return "redirect:/kurs?error=" + e.getMessage();
        }
    }
}
