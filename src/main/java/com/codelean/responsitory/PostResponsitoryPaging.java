package com.codelean.responsitory;

import com.codelean.model.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostResponsitoryPaging extends PagingAndSortingRepository<Post,Long> {

}
