package aaagt.moneytransferservice.backend.dto;

public class TransferResponseDto {

    private String operationId;

    public TransferResponseDto(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

}
