package by.sample.exception.dao;

public class DataNotFoundDAOException extends Exception{
    public DataNotFoundDAOException(String message) {
        super(message);
    }
}
