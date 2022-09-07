package pers.juumii.antiaddiction.exception;

public class UnregisterException extends Exception{

    private String detail;
    public UnregisterException(String detail){
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "UnregisterException: " + detail;
    }
}
