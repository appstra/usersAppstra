package com.appstra.users.repository;

import com.appstra.users.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepository extends JpaRepository<State,Integer> {
    List<State> findByStateTypeStateTypeId(Integer stateTypeId);
}
