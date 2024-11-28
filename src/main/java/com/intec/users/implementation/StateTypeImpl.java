package com.intec.users.implementation;

import com.intec.users.entity.StateType;
import com.intec.users.repository.StateTypeRepository;
import com.intec.users.service.StateTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateTypeImpl implements StateTypeService {
    private final StateTypeRepository stateTypeRepository;

    public StateTypeImpl(StateTypeRepository stateTypeRepository) {
        this.stateTypeRepository = stateTypeRepository;
    }

    @Override
    public StateType saveStateType(StateType stateType) {
        return stateTypeRepository.save(stateType);
    }

    @Override
    public StateType upDateStateType(StateType stateType) {
        StateType existingStateType = stateTypeRepository.findById(stateType.getStateTypeId())
                .orElseThrow(() -> new IllegalArgumentException("Tipo de estado no existe: " + stateType.getStateTypeId()));
        stateType.setStateTypeCreationDate(existingStateType.getStateTypeCreationDate());
        return stateTypeRepository.save(stateType);
    }

    @Override
    public Boolean deleteStateType(Integer stateTypeId) {
        if (stateTypeRepository.existsById(stateTypeId)) {
            stateTypeRepository.deleteById(stateTypeId);
            return true;
        }
        return false;
    }

    @Override
    public List<StateType> listStateType() {
        return stateTypeRepository.findAll();
    }
}
