package aaagt.moneytransferservice.backend.controller;

import aaagt.moneytransferservice.backend.dto.TransferOperationRequestDto;
import aaagt.moneytransferservice.backend.dto.TransferResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static net.logstash.logback.argument.StructuredArguments.kv;

@RestController
public class OperationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationController.class);

    @PostMapping("/transfer")
    public TransferResponseDto transfer(TransferOperationRequestDto operation) {
        LOGGER.info("transfer operation", kv("operation", operation));
        return new TransferResponseDto("1");
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation() {
        //LOGGER.info("transfer operation", kv("confirmOperation", operation));
        return "{\"operationId\":\"1\"}";
    }

    @GetMapping("/transfer")
    public String t() {
        return "transfer";
    }

    @GetMapping("/confirmOperation")
    public String c() {
        return "confirmOperation";
    }

    @GetMapping("/")
    public String home() {
        return "hello";
    }

}
