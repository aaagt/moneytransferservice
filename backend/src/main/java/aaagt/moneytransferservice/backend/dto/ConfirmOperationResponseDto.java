package aaagt.moneytransferservice.backend.dto;

public class ConfirmOperationResponseDto {
    private String operationId;

    public ConfirmOperationResponseDto(String operationId) {
        this.operationId = operationId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }
}
