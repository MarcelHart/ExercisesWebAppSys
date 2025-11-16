package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.fra.uas.dto.KursDTO;
import edu.fra.uas.dto.StudiengangDTO;
import edu.fra.uas.service.Kurs;
import edu.fra.uas.service.Studiengang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studiengang")
public class StudiengangRestController {

    private static final Logger log = LoggerFactory.getLogger(StudiengangRestController.class);

    @Autowired
    private Studiengang studiengang;

    private KursDTO convertKursToDTO(Kurs kurs) {
        KursDTO dto = new KursDTO(
            kurs.getCode(),
            kurs.getName(),
            kurs.getSemester(),
            kurs.getNoten(),
            kurs.getGewichtungen(),
            kurs.berechneNotendurchschnitt()
        );
        dto.add(linkTo(methodOn(KursRestController.class).getKursById(kurs.getCode())).withSelfRel());
        return dto;
    }

    @GetMapping
    public ResponseEntity<StudiengangDTO> getStudiengang() {
        log.debug("GET /api/studiengang - fetching studiengang");
        
        StudiengangDTO dto = new StudiengangDTO(
            studiengang.getName(),
            studiengang.getStudiengangscode(),
            studiengang.getKurse().stream()
                    .map(this::convertKursToDTO)
                    .collect(Collectors.toList()),
            studiengang.berechneNotendurchschnitt()
        );
        
        // Add HATEOAS links
        dto.add(linkTo(methodOn(StudiengangRestController.class).getStudiengang()).withSelfRel());
        dto.add(linkTo(methodOn(KursRestController.class).getAllKurse()).withRel("kurse"));
        
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    public ResponseEntity<StudiengangDTO> updateStudiengang(@RequestBody StudiengangDTO dto) {
        try {
            log.debug("PUT /api/studiengang - updating studiengang");
            studiengang.setName(dto.getName());
            return ResponseEntity.ok(getStudiengang().getBody());
        } catch (Exception e) {
            log.error("Error updating studiengang", e);
            return ResponseEntity.badRequest().build();
        }
    }
}