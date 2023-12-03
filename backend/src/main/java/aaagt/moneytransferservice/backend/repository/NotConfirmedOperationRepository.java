package aaagt.moneytransferservice.backend.repository;

import aaagt.moneytransferservice.backend.model.Operation;

import java.util.Optional;

public interface NotConfirmedOperationRepository {

    String put(Operation operation);

    Optional<Operation> take(String id);
}
