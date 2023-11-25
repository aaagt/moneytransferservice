package aaagt.moneytransferservice.backend.repository;

import aaagt.moneytransferservice.backend.model.Operation;

public interface OperationRepository {
    String transfer(Operation operation);
}
