package com.appstra.users.service;

import com.appstra.users.entity.StateType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateTypeService {
    @Transactional
    StateType saveStateType (StateType stateType);
    StateType upDateStateType (StateType stateType);
    Boolean deleteStateType (Integer stateTypeId);
    List<StateType> listStateType();

}
