package com.codelean.service;

import com.codelean.model.Post;
import com.codelean.responsitory.PostResponsitory;
import com.codelean.responsitory.PostResponsitoryPaging;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PostServiceImpl implements PostService {


    @Autowired
    private PostResponsitory postResponsitory;

    @Autowired
    private PostResponsitoryPaging postResponsitoryPaging;

    @Override
    public List<Post> findAll() {
        return postResponsitory.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postResponsitoryPaging.findById(id);
    }

    @Override
    public void save(Post post) {
        postResponsitoryPaging.save(post);
    }

    @Override
    public void remove(Long id) {
        postResponsitoryPaging.deleteById(id);

    }

    @Override
    public void update(Post post) {
        postResponsitory.update(post.getId(),post);

    }

    @Override
    public List<Post> findAllByUsername(String Username) {
        return postResponsitory.findAllByUsername(Username);
    }
}
