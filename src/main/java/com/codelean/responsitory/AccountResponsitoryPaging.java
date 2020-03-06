package com.codelean.responsitory;

import com.codelean.model.Account;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountResponsitoryPaging extends PagingAndSortingRepository<Account, Long> {
}
