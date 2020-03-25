package com.sbs.sbsgroup7.dao;

import com.sbs.sbsgroup7.model.User;

import java.io.Serializable;
import java.util.List;

public interface UserDaoInterface<T, Id extends Serializable> {

    public void persist(T userEntity);

    public void update(T userEntity);

    public User findById(Id id);

    public void delete(T userEntity);

    public List<User> findAll();

    public void deleteAll();

}