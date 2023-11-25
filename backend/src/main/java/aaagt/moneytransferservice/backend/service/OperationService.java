package aaagt.moneytransferservice.backend.service;

import aaagt.moneytransferservice.backend.model.Operation;
import aaagt.moneytransferservice.backend.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private final OperationRepository repository;

    public OperationService(OperationRepository repository) {
        this.repository = repository;
    }

    public Optional<String> transfer(Operation operation) {
        return Optional.ofNullable(repository.transfer(operation));
    }
}
