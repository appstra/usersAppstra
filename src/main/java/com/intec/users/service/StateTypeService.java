package com.intec.users.service;

import com.intec.users.entity.StateType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateTypeService {
    @Transactional
    StateType saveStateType (StateType stateType);
    StateType upDateStateType (StateType stateType);
    Boolean deleteStateType (Integer stateTypeId);
    List<StateType> listStateType();

}
