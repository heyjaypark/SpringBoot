package com.account.editor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {
	  List<Account> findByPnameContaining(String pname);
	
	
	
	

}
