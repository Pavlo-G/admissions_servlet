package DAO.mysql;

public final class Constants {

    private Constants() {
    }

    public static final String SQL_FIND_ALL_CANDIDATES = "SELECT * FROM candidate";
    public static final String SQL_FIND_ALL_ADMISSION_REQUESTS = "SELECT * FROM admission_request";
    public static final String SQL_FIND_CANDIDATE_BY_USERNAME = "SELECT * FROM  candidate WHERE username = ?";

}
