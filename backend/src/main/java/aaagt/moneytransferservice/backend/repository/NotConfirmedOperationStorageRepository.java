package aaagt.moneytransferservice.backend.repository;

import aaagt.moneytransferservice.backend.model.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class NotConfirmedOperationStorageRepository implements NotConfirmedOperationRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotConfirmedOperationStorageRepository.class);
    ConcurrentHashMap<String, Operation> storage = new ConcurrentHashMap<>();

    @Override
    public String put(Operation operation) {
        LOGGER.debug("Putting operation: {}", operation.toString());
        String id = UUID.randomUUID().toString();
        storage.put(id, operation);
        return id;
    }

    @Override
    public Optional<Operation> take(String id) {
        LOGGER.debug("Taking operation by id: {}", id);
        return Optional.ofNullable(storage.getOrDefault(id, null));
    }
}
