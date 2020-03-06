package com.codelean.responsitory;

import com.codelean.model.Post;

import java.util.List;

public interface PostResponsitory extends Responsitory<Post> {
    public List<Post> findAllByUsername(String Username);
}
