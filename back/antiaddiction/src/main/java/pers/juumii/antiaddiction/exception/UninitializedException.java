package pers.juumii.antiaddiction.exception;

public class UninitializedException extends Exception{

    String detail;
    public UninitializedException(String detail){
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "UninitializedException: " + detail;
    }
}
