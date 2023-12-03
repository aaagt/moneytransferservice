package aaagt.moneytransferservice.backend.service;

import aaagt.moneytransferservice.backend.exception.ErrorInputData;
import aaagt.moneytransferservice.backend.model.Operation;
import aaagt.moneytransferservice.backend.repository.NotConfirmedOperationRepository;
import aaagt.moneytransferservice.backend.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {

    @Autowired
    private final OperationRepository operationRepository;
    @Autowired
    private final NotConfirmedOperationRepository notConfirmedOperationRepository;

    public OperationService(OperationRepository operationRepository, NotConfirmedOperationRepository notConfirmedOperationRepository) {
        this.operationRepository = operationRepository;
        this.notConfirmedOperationRepository = notConfirmedOperationRepository;
    }

    /**
     * Заглушка получения кода подтверждения
     *
     * @return код подтверждения
     */
    private static boolean isNotValidConfirmationCode(String code) {
        return !"0000".equals(code);
    }

    public String transfer(Operation operation) {
        return notConfirmedOperationRepository.put(operation);
    }

    public String confirm(String confirmationCode, String id) {
        if (isNotValidConfirmationCode(confirmationCode)) {
            throw new ErrorInputData("Confirmation code is not valid.", 3);
        }

        var operation = notConfirmedOperationRepository.take(id)
                .orElseThrow(() -> new ErrorInputData("Operation with id %s is not found".formatted(id), 4));

        return operationRepository.transfer(operation);
    }
}
