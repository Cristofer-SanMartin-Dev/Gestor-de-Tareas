package com.tasks.tag_service.controller;



import com.tasks.tag_service.model.Tag;
import com.tasks.tag_service.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tags") // URL base para etiquetas
@CrossOrigin(origins = "*") // Permite peticiones de cualquier origen
public class TagController {

    @Autowired
    private TagService tagService;

    // --- ENDPOINTS PARA ETIQUETAS ---

    // 1. Obtener todas las etiquetas
    // GET http://localhost:8082/api/tags
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    // 2. Obtener una etiqueta por ID
    // GET http://localhost:8082/api/tags/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        Optional<Tag> tag = tagService.getTagById(id);
        return tag.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    // 3. Crear una nueva etiqueta
    // POST http://localhost:8082/api/tags
    @PostMapping
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        try {
            Tag savedTag = tagService.createTag(tag);
            return new ResponseEntity<>(savedTag, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Devuelve 400 Bad Request
        }
    }

    // 4. Actualizar una etiqueta existente
    // PUT http://localhost:8082/api/tags/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tagDetails) {
        try {
            Tag updatedTag = tagService.updateTag(id, tagDetails);
            return ResponseEntity.ok(updatedTag);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // 404 si no encuentra la etiqueta
        }
    }

    // 5. Eliminar una etiqueta
    // DELETE http://localhost:8082/api/tags/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable Long id) {
        try {
            tagService.deleteTag(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 si hay otro error
        }
    }
}