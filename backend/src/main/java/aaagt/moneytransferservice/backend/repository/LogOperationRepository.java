package aaagt.moneytransferservice.backend.repository;

import aaagt.moneytransferservice.backend.controller.OperationController;
import aaagt.moneytransferservice.backend.model.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Repository
public class LogOperationRepository implements OperationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);

    @Override
    public String transfer(Operation operation) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        LOGGER.info("transfer operation", kv("operation", operation));
        return uuidAsString;
    }
}
