package com.empik.recruitmenttask.repository;

import com.empik.recruitmenttask.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

//@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {

}
