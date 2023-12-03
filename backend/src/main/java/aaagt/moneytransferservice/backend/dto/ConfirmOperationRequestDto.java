package aaagt.moneytransferservice.backend.dto;

public class ConfirmOperationRequestDto {
    private String operationId;
    private String code;

    public ConfirmOperationRequestDto(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperationRequestDto{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
