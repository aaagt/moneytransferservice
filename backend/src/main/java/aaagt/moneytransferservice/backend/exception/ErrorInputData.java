package aaagt.moneytransferservice.backend.exception;

public class ErrorInputData extends RuntimeException {

    private int id;

    public ErrorInputData(String message, int id) {
        super(message);
    }

    public int getId() {
        return id;
    }
}
