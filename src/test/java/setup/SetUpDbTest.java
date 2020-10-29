package setup;

import model.DAO.mysql.ConnectionPoolHolder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SetUpDbTest {

    public static void setUpDataBase() {
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("delete from admissions_test.admission_request;");
            stmt.execute("delete from admissions_test.candidate_profile;");
            stmt.execute("delete from admissions_test.candidate;");
            stmt.execute("delete from admissions_test.faculty;");
            stmt.execute("ALTER TABLE admissions_test.admission_request AUTO_INCREMENT =1;");
            stmt.execute("ALTER TABLE admissions_test.candidate_profile AUTO_INCREMENT =1;");
            stmt.execute("ALTER TABLE admissions_test.candidate AUTO_INCREMENT = 1;");
            stmt.execute("ALTER TABLE admissions_test.faculty AUTO_INCREMENT =1;");
            stmt.execute("insert into admissions_test.candidate( username, password, candidate_status, role) values " +
                    "( 'admin', '$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC', 'ACTIVE', 'ADMIN'), " +
                    "( 'user', '$2a$08$fNUHI3FnO3cbT6VAcClJOOsIq93f2101ud2RAKiZFAh7Y2h.oFRzC', 'ACTIVE', 'USER');");
            stmt.execute("insert into" +
                    "            admissions_test.candidate_profile( first_name, last_name, email, address, city, region, school, phone_number, candidate_id)" +
                    "            values ( 'Admin', 'Adminov', 'admin@admin.com', 'some street 12/22', 'someCity', 'someRegion', 'someSchool', '050-123-1234', 1)," +
                    "                    ( 'User', 'Userov', 'user@user.com', 'some street 25/17', 'someCity', 'someRegion', 'someSchool', '050-123-1234', 2)" +
                    "            ;");
            stmt.execute("insert into admissions_test.faculty(name_en, name_uk, description_en,description_uk,budget_capacity,total_capacity,req_subject1_en,req_subject1_uk,req_subject2_en,req_subject2_uk,req_subject3_en,req_subject3_uk,admission_open)" +
                    " values ( 'Faculty of Art', 'Факультет Мистецтв', 'description english','опис українською',5,10,'English','Англійська','History','Історія','Ukrainian','Українська',true), " +
                    "( 'Faculty of Economics', 'Факультет Економіки', 'description english','опис українською',10,15,'Math','Математика','English','Англійська','Ukrainian','Українська',true);");
            stmt.execute("insert into admissions_test.admission_request( status, req_subject1_grade, req_subject2_grade, req_subject3_grade, candidate_id, faculty_id) values\n" +
                    "                ( 0, 6, 7,8,2,1);");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }


    public static void tearDownDataBase() {
        try (Connection connection = ConnectionPoolHolder.getDataSource().getConnection();
             Statement stmt = connection.createStatement()) {
            stmt.execute("delete from admissions_test.admission_request;");
            stmt.execute("delete from admissions_test.candidate_profile;");
            stmt.execute("delete from admissions_test.candidate;");
            stmt.execute("delete from admissions_test.faculty;");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
