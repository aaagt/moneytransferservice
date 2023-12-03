package aaagt.moneytransferservice.backend.controller;

import aaagt.moneytransferservice.backend.dto.ConfirmOperationRequestDto;
import aaagt.moneytransferservice.backend.dto.ConfirmOperationResponseDto;
import aaagt.moneytransferservice.backend.dto.TransferOperationRequestDto;
import aaagt.moneytransferservice.backend.dto.TransferResponseDto;
import aaagt.moneytransferservice.backend.model.Operation;
import aaagt.moneytransferservice.backend.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);
    @Autowired
    private final OperationService service;

    public OperationController(OperationService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public TransferResponseDto transfer(TransferOperationRequestDto operationDto) {
        var operation = operationFromTransferOperationRequestDto(operationDto);
        var operationId = service.transfer(operation);
        return new TransferResponseDto(operationId);
    }

    @PostMapping("/confirmOperation")
    public ConfirmOperationResponseDto confirmOperation(@RequestBody ConfirmOperationRequestDto requestDto) {
        LOGGER.debug("ConfirmOperation: {}", requestDto);
        var operationId = service.confirm(requestDto.getCode(), requestDto.getOperationId());
        return new ConfirmOperationResponseDto(operationId);
    }

    private Operation operationFromTransferOperationRequestDto(TransferOperationRequestDto operationDto) {
        return new Operation(
                operationDto.getCardFromNumber(),
                operationDto.getCardFromValidTill(),
                operationDto.getCardFromCVV(),
                operationDto.getCardToNumber(),
                new Operation.Amount(
                        operationDto.getAmount().getValue(),
                        operationDto.getAmount().getCurrency()
                )
        );
    }

}
