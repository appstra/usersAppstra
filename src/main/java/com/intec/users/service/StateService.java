package com.intec.users.service;

import com.intec.users.entity.State;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StateService {
    @Transactional
    State saveState(State state);
    State upDateState (State state);
    Boolean deleteState (Integer stateId);
    List<State> listState();
}
