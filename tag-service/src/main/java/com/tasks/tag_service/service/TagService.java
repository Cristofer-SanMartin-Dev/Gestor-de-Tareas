package com.tasks.tag_service.service;


import com.tasks.tag_service.model.Tag;
import java.util.List;
import java.util.Optional;

public interface TagService {
    List<Tag> getAllTags();
    Optional<Tag> getTagById(Long id);
    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tagDetails);
    void deleteTag(Long id);
}