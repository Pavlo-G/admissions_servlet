package DAO.mysql;

import DAO.CandidateDAO;
import entity.Candidate;
import entity.CandidateProfile;
import entity.CandidateStatus;
import entity.Role;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySqlCandidateDAO implements CandidateDAO {

    private static MySqlCandidateDAO instance;

    public static synchronized MySqlCandidateDAO getInstance() {
        if (instance == null) {
            instance = new MySqlCandidateDAO();
        }
        return instance;
    }

    private MySqlCandidateDAO() {

    }

    @Override
    public int insertCandidate(Candidate candidate) {
        PreparedStatement pstmt = null;
        int result = 0;
        Connection con = null;

        try {
            con = MySqlDAOFactory.createConnection();
            pstmt = con.prepareStatement("INSERT INTO candidate (username,password,role,status) Values(?,?,?,?)");
            pstmt.setString(1, candidate.getUsername());
            pstmt.setString(2, candidate.getPassword());
            pstmt.setString(3, candidate.getRole().getName());
            pstmt.setString(4, candidate.getCandidateStatus().name());
            result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }


    public int insertCandidateProfile(CandidateProfile candidateProfile) {
        PreparedStatement pstmt = null;
        int result = 0;
        Connection con = null;

        try {
            con = MySqlDAOFactory.createConnection();
            pstmt = con.prepareStatement("INSERT INTO candidate_profile (" +
                    "first_name,last_name,email,address,city,region,school,phone_number,candidate_id) Values(?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, candidateProfile.getFirstName());
            pstmt.setString(2, candidateProfile.getLastName());
            pstmt.setString(3, candidateProfile.getEmail());
            pstmt.setString(4, candidateProfile.getAddress());
            pstmt.setString(5, candidateProfile.getCity());
            pstmt.setString(6, candidateProfile.getRegion());
            pstmt.setString(7, candidateProfile.getSchool());
            pstmt.setString(8, candidateProfile.getPhoneNumber());
            pstmt.setLong(9, candidateProfile.getCandidate().getId());
            result = pstmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return result;
    }




    @Override
    public boolean deleteCandidate() {
        return false;
    }

    @Override
    public Candidate findCandidateById(int id) {
        return null;
    }

    @Override
    public Candidate findCandidateByLogin(String login) {
        Candidate candidate = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = MySqlDAOFactory.createConnection();
            pstmt = con.prepareStatement(Constants.SQL_FIND_CANDIDATE_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                candidate = mapCandidate(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            //  DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            try {
                rs.close();

                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            //  DBManager.getInstance().commitAndClose(con);
        }
        return candidate;
    }

    @Override
    public boolean updateCandidate() {
        return false;
    }

    @Override
    public RowSet selectCandidatesRS() {
        return null;
    }

    @Override
    public List<Candidate> getAllCandidatesTO() {
        List<Candidate> listCandidates = new ArrayList<>();

        try (Connection con = MySqlDAOFactory.createConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_CANDIDATES)) {

            while (rs.next()) {
                listCandidates.add(mapCandidate(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            //LOGGER.severe("Cannot get all users!");
            //  throw new SQLException("Cannot get all candidates!", ex);
        }
        return listCandidates;


    }

    private Candidate mapCandidate(ResultSet rs) throws SQLException {
        Candidate candidate = new Candidate();
        candidate.setId(rs.getLong("id"));
        candidate.setUsername(rs.getString("username"));
        candidate.setPassword(rs.getString("password"));
        candidate.setRole(Role.valueOf(rs.getString("role")));
        candidate.setCandidateStatus(CandidateStatus.valueOf(rs.getString("status")));
        return candidate;
    }


}
