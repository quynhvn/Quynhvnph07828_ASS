package com.codelean.service;

import com.codelean.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void save(Post post);
    void remove(Long id);
    void update(Post post);

    List<Post> findAllByUsername( String Username);
}
