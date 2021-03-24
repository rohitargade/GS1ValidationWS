package com.ascention.validationWS.beans;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends PagingAndSortingRepository<RuleTable, Integer>{

}