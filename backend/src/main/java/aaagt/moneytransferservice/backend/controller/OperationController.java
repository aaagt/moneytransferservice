package aaagt.moneytransferservice.backend.controller;

import aaagt.moneytransferservice.backend.dto.ConfirmOperationRequestDto;
import aaagt.moneytransferservice.backend.dto.TransferOperationRequestDto;
import aaagt.moneytransferservice.backend.dto.TransferResponseDto;
import aaagt.moneytransferservice.backend.exception.ErrorInputData;
import aaagt.moneytransferservice.backend.model.Operation;
import aaagt.moneytransferservice.backend.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class OperationController {

    @Autowired
    private final OperationService service;

    public OperationController(OperationService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public TransferResponseDto transfer(TransferOperationRequestDto operationDto) {
        return Optional.of(operationFromTransferOperationRequestDto(operationDto))
                .flatMap(service::transfer)
                .map(TransferResponseDto::new)
                .orElseThrow(() -> new ErrorInputData("cant perform operation", 2));
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(ConfirmOperationRequestDto requestDto) {
        return "{\"operationId\":\"%s\"}".formatted(requestDto.getOperationId());
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
