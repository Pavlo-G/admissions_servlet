package model.DAO.mysql;

public final class Constants {

    private Constants() {
    }


    //Sql constants for MySqlAdmissionRequestDAO

    public static final String SQL_FIND_ALL_ADMISSION_REQUESTS = "SELECT * FROM admission_request";
    public static final String SQL_DELETE_ADMISSION_REQUEST = "DELETE  FROM admission_request WHERE  id= ?";
    public static final String SQL_CHANGE_ADMISSION_REQUEST_STATUS = "UPDATE  admission_request SET admission_request.status=? WHERE  id= ?";
    public static final String SQL_INSERT_ADMISSION_REQUEST = "INSERT INTO admission_request " +
            "(faculty_id,candidate_id,req_subject1_grade,req_subject2_grade,req_subject3_grade,admission_request.status)" +
            "Values(?,?,?,?,?,?);";
    public static final String SQL_FIND_ADMISSION_REQUEST_BY_ID = "SELECT cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school,cp.certificate_file, cp.candidate_id, " +
            "                f.id, budget_capacity, description_en, name_en, name_uk,description_uk, req_subject1_en,req_subject1_uk, req_subject2_en,req_subject2_uk, req_subject3_en,req_subject3_uk, total_capacity, admission_open, " +
            "                admission_request.id, admission_request.status, creation_date_time, req_subject1_grade, req_subject2_grade, req_subject3_grade,admission_request.candidate_id,faculty_id, " +
            "                 c.id,c.username,c.password,c.role,c.candidate_status " +
            "                FROM admission_request  " +
            "                Left JOIN candidate c on admission_request.candidate_id=c.id " +
            "                Left JOIN  candidate_profile cp on admission_request.candidate_id = cp.candidate_id " +
            "                Left JOIN faculty f on admission_request.faculty_id = f.id WHERE admission_request.id=?";


    // Sql constants for MySqlCandidateDAO

    public static final String SQL_FIND_ALL_CANDIDATES ="SELECT c.id, candidate_status, password, role, username," +
            " cp.id, address, city, email, first_name, last_name, phone_number, region, school,certificate_file, candidate_id " +
            "FROM candidate c left join candidate_profile cp on c.id = cp.candidate_id" ;
    public static final String SQL_FIND_CANDIDATE_BY_ID = "SELECT c.id, candidate_status, password, role, username," +
            " cp.id, address, city, email, first_name, last_name, phone_number, region, school,certificate_file, candidate_id " +
            "FROM candidate c  left join candidate_profile cp on c.id = cp.candidate_id WHERE c.id =?";
    public static final String SQL_INSERT_CANDIDATE="INSERT INTO candidate (username,password,role,candidate_status) Values(?,?,?,?);" ;
    public static final String SQL_INSERT_CANDIDATE_PROFILE="INSERT INTO candidate_profile(first_name,last_name,email,address,city,region,school,phone_number,certificate_file,candidate_id)" +
            " Values(?,?,?,?,?,?,?,?,?,?)";
    public static final String SQL_DELETE_CANDIDATE = "DELETE FROM candidate WHERE id=?;";
    public static final String SQL_UPDATE_CANDIDATE = " UPDATE  candidate SET role=?,candidate_status=? WHERE id=?;";
    public static final String SQL_UPDATE_CANDIDATE_PROFILE ="UPDATE candidate_profile SET first_name=?,last_name=?,email=?,address=?,city=?,region=?,school=?,phone_number=? cp.certificate_file=? " +
            "WHERE id=?";
    public static final String SQL_FIND_CANDIDATE_PROFILE = "SELECT cp.id, address, city, email, first_name, last_name, phone_number, region, school, certificate_file, candidate_id From candidate_profile cp Where candidate_id=?";

    public static final String SQL_FIND_CANDIDATE_BY_USERNAME = "SELECT c.id, c.candidate_status, c.password, c.role, c.username " +
            "FROM  candidate c " +
            " WHERE username = ?;";

    // Sql constants for MySqlFacultyDAO

    public static final String SQL_UPDATE_FACULTY_ADMISSION_STATUS = "UPDATE  faculty SET admission_open=? WHERE id=?;";
    public static final String SQL_DELETE_FACULTY = "DELETE  FROM faculty WHERE  id= ?";
    public static final String SQL_FIND_ALL_FACULTIES = "SELECT * From faculty f LEFT JOIN admission_request on f.id = admission_request.faculty_id";
    public static final String SQL_UPDATE_FACULTY =  " UPDATE  faculty " +
            "SET name_en=?, name_uk=?,description_en=?,description_uk=?,budget_capacity=?, " +
            "total_capacity=?,req_subject1_en=?,req_subject1_uk=?,req_subject2_en=?," +
            " req_subject2_uk=?,req_subject3_en=?,req_subject3_uk=? " +
            "WHERE id=?;";
    public static final String SQL_FIND_FACULTY_BY_ID = "SELECT  " +
            "f.id, f.budget_capacity, f.description_en, f.name_en,f.description_uk,f.name_uk, f.req_subject1_en, f.req_subject1_uk, f.req_subject2_en ,f.req_subject2_uk, f.req_subject3_en ,f.req_subject3_uk, f.total_capacity, f.admission_open," +
            "admission_request.id, admission_request.status, admission_request.creation_date_time, admission_request.req_subject1_grade, admission_request.req_subject2_grade, admission_request.req_subject3_grade, admission_request.candidate_id, admission_request.faculty_id," +
            "c.id, c.candidate_status, c.password, c.role, c.username," +
            "cp.id, cp.address, cp.city, cp.email, cp.first_name, cp.last_name, cp.phone_number, cp.region, cp.school,cp.certificate_file, cp.candidate_id " +
            "FROM faculty f " +
            "Left Join admission_request  on f.id = admission_request.faculty_id " +
            "Left Join candidate c on admission_request.candidate_id = c.id  " +
            "Left Join candidate_profile cp on c.id = cp.candidate_id" +
            " WHERE f.id=?";
    public static final String SQL_CREATE_FACULTY = " INSERT INTO faculty  " +
            " (name_en,name_uk,description_en,description_uk,budget_capacity," +
            " total_capacity,req_subject1_en,req_subject1_uk,req_subject2_en," +
            "req_subject2_uk,req_subject3_en,req_subject3_uk,admission_open)" +
            "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

}
