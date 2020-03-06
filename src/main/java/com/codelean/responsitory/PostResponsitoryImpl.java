package com.codelean.responsitory;

import com.codelean.model.Post;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public class PostResponsitoryImpl implements PostResponsitory {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Post> findAll() {
        String query = "select c from Post c";
        TypedQuery<Post> postTypedQuery=entityManager.createQuery(query,Post.class);
        return postTypedQuery.getResultList();
    }

    @Override
    public Post findById(Long id) {
        try{
            String query = "Select c from Post c where c.id = :id";
            TypedQuery<Post> baiVietTypedQuery = entityManager.createQuery(query.toString(),Post.class);
            baiVietTypedQuery.setParameter("id",id);
            return baiVietTypedQuery.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }


    @Override
    public void save(Post model) {


    }

    @Override
    public void remove(Post id) {

    }


    @Override
    public void update(Long id, Post model) {
        entityManager.merge(model);

    }



    @Override
    public List<Post> findAllByUsername(String Username) {
        String query = "Select c from Post c where c.userName = :userName";
        TypedQuery<Post> BaiVietTypedQuery=entityManager.createQuery(query.toString(),Post.class);
        BaiVietTypedQuery.setParameter("userName",Username);
        return BaiVietTypedQuery.getResultList();
    }
}
