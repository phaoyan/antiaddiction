package pers.juumii.antiaddiction.exception;

public class NonexistenceException extends Exception{
    private String detail;
    public NonexistenceException(String detail){
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "NonexistenceException: " + detail;
    }
}
