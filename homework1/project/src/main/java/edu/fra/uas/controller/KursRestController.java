package edu.fra.uas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.fra.uas.dto.KursDTO;
import edu.fra.uas.exception.KursNotFoundException;
import edu.fra.uas.service.Kurs;
import edu.fra.uas.service.Studiengang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/kurse")
public class KursRestController {

    private static final Logger log = LoggerFactory.getLogger(KursRestController.class);

    @Autowired
    private Studiengang studiengang;

    private Kurs findKursByCode(int code) {
        return studiengang.getKurse().stream()
                .filter(k -> k.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new KursNotFoundException(code));
    }

    private KursDTO convertToDTO(Kurs kurs) {
        KursDTO dto = new KursDTO(
            kurs.getCode(),
            kurs.getName(),
            kurs.getSemester(),
            kurs.getNoten(),
            kurs.getGewichtungen(),
            kurs.berechneNotendurchschnitt()
        );
        
        // Add HATEOAS links
        dto.add(linkTo(methodOn(KursRestController.class).getKursById(kurs.getCode())).withSelfRel());
        dto.add(linkTo(methodOn(KursRestController.class).getAllKurse()).withRel("kurse"));
        dto.add(linkTo(methodOn(KursRestController.class).updateKurs(kurs.getCode(), dto)).withRel("update"));
        dto.add(linkTo(methodOn(KursRestController.class).deleteKurs(kurs.getCode())).withRel("delete"));
        
        return dto;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<KursDTO>> getAllKurse() {
        log.debug("GET /api/kurse - fetching all courses");
        
        List<KursDTO> kurse = studiengang.getKurse().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        
        CollectionModel<KursDTO> resource = CollectionModel.of(kurse,
                linkTo(methodOn(KursRestController.class).getAllKurse()).withSelfRel());
        
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{code}")
    public ResponseEntity<KursDTO> getKursById(@PathVariable int code) {
        try {
            log.debug("GET /api/kurse/{} - fetching course", code);
            Kurs kurs = findKursByCode(code);
            return ResponseEntity.ok(convertToDTO(kurs));
        } catch (KursNotFoundException e) {
            log.error("Course not found: {}", code);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<KursDTO> createKurs(@RequestBody KursDTO kursDTO) {
        try {
            log.debug("POST /api/kurse - creating new course: {}", kursDTO.getName());
            Kurs newKurs = new Kurs(kursDTO.getName(), kursDTO.getCode(), kursDTO.getSemester());
            studiengang.addKurs(newKurs);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(newKurs));
        } catch (Exception e) {
            log.error("Error creating course", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{code}")
    public ResponseEntity<KursDTO> updateKurs(@PathVariable int code, @RequestBody KursDTO kursDTO) {
        try {
            log.debug("PUT /api/kurse/{} - updating course", code);
            Kurs kurs = findKursByCode(code);
            kurs.setName(kursDTO.getName());
            kurs.setSemester(kursDTO.getSemester());
            return ResponseEntity.ok(convertToDTO(kurs));
        } catch (KursNotFoundException e) {
            log.error("Course not found for update: {}", code);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteKurs(@PathVariable int code) {
        try {
            log.debug("DELETE /api/kurse/{} - deleting course", code);
            studiengang.getKurse().removeIf(k -> k.getCode() == code);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            log.error("Error deleting course: {}", code);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{code}/noten")
    public ResponseEntity<KursDTO> addNote(@PathVariable int code,
                                           @RequestParam double note,
                                           @RequestParam int gewichtung) {
        try {
            log.debug("POST /api/kurse/{}/noten - adding grade", code);
            Kurs kurs = findKursByCode(code);
            kurs.addNote(note, gewichtung);
            return ResponseEntity.ok(convertToDTO(kurs));
        } catch (KursNotFoundException e) {
            log.error("Course not found: {}", code);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{code}/noten/{index}")
    public ResponseEntity<KursDTO> deleteNote(@PathVariable int code, @PathVariable int index) {
        try {
            log.debug("DELETE /api/kurse/{}/noten/{} - deleting grade", code, index);
            Kurs kurs = findKursByCode(code);
            kurs.removeNote(index);
            return ResponseEntity.ok(convertToDTO(kurs));
        } catch (KursNotFoundException e) {
            log.error("Course not found: {}", code);
            return ResponseEntity.notFound().build();
        }
    }
}