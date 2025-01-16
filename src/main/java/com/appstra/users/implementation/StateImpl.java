package com.appstra.users.implementation;

import com.appstra.users.entity.State;
import com.appstra.users.service.StateService;
import com.appstra.users.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateImpl implements StateService {
    private final StateRepository stateRepository;

    public StateImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State saveState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State upDateState(State state) {
        State existingState = stateRepository.findById(state.getStateId())
                .orElseThrow(() -> new IllegalArgumentException("el estado no existe: " + state.getStateId()));
        state.setStateCreationDate(existingState.getStateCreationDate());

        return stateRepository.save(state);
    }

    @Override
    public Boolean deleteState(Integer stateId) {
        if (stateRepository.existsById(stateId)) {
            stateRepository.deleteById(stateId);
            return true;
        }
        return false;
    }

    @Override
    public List<State> listState() {
        return stateRepository.findAll();
    }

    @Override
    public List<State> listStateForStateType(Integer stateTypeId) {
        return stateRepository.findByStateTypeStateTypeId(stateTypeId);
    }
}
