package com.tasks.tag_service.service;


import com.tasks.tag_service.model.Tag;
import com.tasks.tag_service.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> getTagById(Long id) {
        return tagRepository.findById(id);
    }

    @Override
    public Tag createTag(Tag tag) {
        if (tag.getName() == null || tag.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tag name cannot be empty.");
        }
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tagDetails) {
        Optional<Tag> tagOptional = tagRepository.findById(id);
        if (tagOptional.isPresent()) {
            Tag existingTag = tagOptional.get();
            if (tagDetails.getName() != null && !tagDetails.getName().trim().isEmpty()) {
                existingTag.setName(tagDetails.getName());
            }
            if (tagDetails.getDescription() != null) {
                existingTag.setDescription(tagDetails.getDescription());
            }
            return tagRepository.save(existingTag);
        } else {
            throw new RuntimeException("Tag not found with id " + id);
        }
    }

    @Override
    public void deleteTag(Long id) {
        // Aquí, en el futuro, podrías añadir lógica para desvincular etiquetas de tareas
        tagRepository.deleteById(id);
    }
}