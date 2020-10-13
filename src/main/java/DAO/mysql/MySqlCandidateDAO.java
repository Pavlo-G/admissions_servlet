package DAO.mysql;

import DAO.CandidateDAO;
import DAO.mapper.CandidateMapper;
import DAO.mapper.CandidateProfileMapper;
import entity.Candidate;
import entity.CandidateProfile;
import entity.CandidateStatus;
import entity.Role;

import javax.sql.RowSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlCandidateDAO implements CandidateDAO {


    private final Connection connection;


    public MySqlCandidateDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insertCandidate(Candidate candidate, CandidateProfile candidateProfile) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Long candidateId = null;
        try {
            conn = connection;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            pstmt = conn.prepareStatement(
                    "INSERT INTO candidate (username,password,role,status) Values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, candidate.getUsername());
            pstmt.setString(2, candidate.getPassword());
            pstmt.setString(3, candidate.getRole().getName());
            pstmt.setString(4, candidate.getCandidateStatus().name());
            candidateId = getGeneratedKey(pstmt);


            pstmt = conn.prepareStatement(
                    "INSERT INTO candidate_profile(first_name,last_name,email,address,city,region,school,phone_number,candidate_id)" +
                            " Values(?,?,?,?,?,?,?,?,?);");

            pstmt.setString(1, candidateProfile.getFirstName());
            pstmt.setString(2, candidateProfile.getLastName());
            pstmt.setString(3, candidateProfile.getEmail());
            pstmt.setString(4, candidateProfile.getAddress());
            pstmt.setString(5, candidateProfile.getCity());
            pstmt.setString(6, candidateProfile.getRegion());
            pstmt.setString(7, candidateProfile.getSchool());
            pstmt.setString(8, candidateProfile.getPhoneNumber());
            pstmt.setLong(9, candidateId);
            pstmt.execute();
            conn.commit();

        } catch (SQLException ex) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException exp) {
                throw new SQLException("Can not make rollback of transaction ", exp);
            }
            throw new SQLException("Can not make  transaction" + ex.getMessage(), ex);
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {

            }
        }

    }


    private Long getGeneratedKey(PreparedStatement pstmt) throws SQLException {
        ResultSet rs = null;
        try {
            if (pstmt.executeUpdate() > 0) {
                rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Can not return generated keys!", ex);
        }

        return -1L;
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
    public Candidate findCandidateByUsername(String username) {
        Candidate candidate = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = connection;
            pstmt = con.prepareStatement("SELECT c.id, c.candidate_status, c.password, c.role, c.username FROM  candidate c  WHERE username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            CandidateMapper candidateMapper = new CandidateMapper();
            if (rs.next())
                candidate = candidateMapper.extractFromResultSet(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
            try {
                rs.close();

                pstmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
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
    public List<Candidate> getAllCandidatesTO() throws SQLException {
        List<Candidate> listCandidates = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_CANDIDATES)) {
            CandidateMapper candidateMapper = new CandidateMapper();
            while (rs.next()) {
                listCandidates.add(candidateMapper.extractFromResultSet(rs));
            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all candidates!", ex);
        }
        return listCandidates;


    }


    @Override
    public Optional<CandidateProfile> getCandidateProfile(Candidate candidate) {
        Optional<CandidateProfile> result = Optional.empty();
        try (Connection con = connection;
             PreparedStatement ps = con.prepareCall("SELECT * From candidate_profile Where candidate_id=?")) {
            ps.setLong(1, candidate.getId());
            try (ResultSet rs = ps.executeQuery()) {

                CandidateProfileMapper mapper = new CandidateProfileMapper();
                if (rs.next()) {
                    result = Optional.of(mapper.extractFromResultSet(rs));
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }


    @Override
    public void updateCandidateProfile(CandidateProfile candidateProfile) throws SQLException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = connection;
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            pstmt = conn.prepareStatement(
                    "UPDATE candidate_profile SET first_name=?,last_name=?,email=?,address=?,city=?,region=?,school=?,phone_number=? " +
                            "WHERE id=?");
            pstmt.setString(1, candidateProfile.getFirstName());
            pstmt.setString(2, candidateProfile.getLastName());
            pstmt.setString(3, candidateProfile.getEmail());
            pstmt.setString(4, candidateProfile.getAddress());
            pstmt.setString(5, candidateProfile.getCity());
            pstmt.setString(6, candidateProfile.getRegion());
            pstmt.setString(7, candidateProfile.getSchool());
            pstmt.setString(8, candidateProfile.getPhoneNumber());
            pstmt.setLong(9, candidateProfile.getId());
            pstmt.execute();
            pstmt.close();
            conn.commit();

        } catch (SQLException ex) {
            try {
                assert conn != null;
                conn.rollback();
            } catch (SQLException exp) {
                throw new SQLException("Can not make rollback of transaction ", exp);
            }
            throw new SQLException("Can not make  transaction" + ex.getMessage(), ex);
        } finally {
            try {
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {

            }
        }


    }
}
