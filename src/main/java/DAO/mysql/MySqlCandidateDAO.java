package DAO.mysql;

import DAO.CandidateDAO;
import DAO.mapper.CandidateMapper;
import DAO.mapper.CandidateProfileMapper;
import entity.Candidate;
import entity.CandidateProfile;

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
                    "INSERT INTO candidate (username,password,role,candidate_status) Values(?,?,?,?);", Statement.RETURN_GENERATED_KEYS);

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
                assert pstmt != null;
                pstmt.close();
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
    public boolean deleteCandidate(Long id) {
        String sql = " DELETE FROM candidate " +
                " WHERE id=?;";
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }

    }

    @Override
    public Optional<Candidate> findCandidateById(Long id) throws SQLException {

        Optional<Candidate> candidate = Optional.empty();
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement("SELECT c.id, candidate_status, password, role, username," +
                     " cp.id, address, city, email, first_name, last_name, phone_number, region, school, candidate_id " +
                     "FROM candidate c  left join candidate_profile cp on c.id = cp.candidate_id WHERE c.id =?")) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                CandidateMapper candidateMapper = new CandidateMapper();
                CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();
                while (rs.next()) {
                    candidate = candidateMapper.extractFromResultSetOpt(rs);
                    CandidateProfile candidateProfile = candidateProfileMapper.extractFromResultSet(rs);
                    candidate.ifPresent(c -> c.setCandidateProfile(candidateProfile));
                }

            } catch (SQLException ex) {
                throw new SQLException("Cannot get  candidate with id: !" + id, ex);
            }
        } catch (SQLException exp) {
            throw new SQLException("Cannot get  candidate with id: !" + id, exp);
        }
        return candidate;
    }

    @Override
    public Candidate findCandidateByUsername(String username) {
        Candidate candidate = null;

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(
                     "SELECT c.id, c.candidate_status, c.password, c.role, c.username " +
                             "FROM  candidate c " +
                             " WHERE username = ?;")) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                CandidateMapper candidateMapper = new CandidateMapper();
                if (rs.next())
                    candidate = candidateMapper.extractFromResultSet(rs);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
        return candidate;
    }

    @Override
    public boolean updateCandidate(String role, String candidateStatus, Long id) {
        String sql = " UPDATE  candidate " +
                "SET role=?,candidate_status=? " +
                " WHERE id=?;";


        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, role);
            pstmt.setString(2, candidateStatus);
            pstmt.setLong(3, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public List<Candidate> getAllCandidates() throws SQLException {
        List<Candidate> listCandidates = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT c.id, candidate_status, password, role, username," +
                     " cp.id, address, city, email, first_name, last_name, phone_number, region, school, candidate_id " +
                     "FROM candidate c left join candidate_profile cp on c.id = cp.candidate_id")) {
            CandidateMapper candidateMapper = new CandidateMapper();
            CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();
            while (rs.next()) {
                Candidate candidate = candidateMapper.extractFromResultSet(rs);
                CandidateProfile candidateProfile = candidateProfileMapper.extractFromResultSet(rs);
                candidate.setCandidateProfile(candidateProfile);
                listCandidates.add(candidate);

            }
        } catch (SQLException ex) {

            throw new SQLException("Cannot get all candidates!", ex);
        }
        return listCandidates;


    }


    @Override
    public Optional<CandidateProfile> getCandidateProfile(Candidate candidate) throws SQLException {
        Optional<CandidateProfile> result = Optional.empty();
        try (Connection con = connection;
             PreparedStatement ps = con.prepareCall("SELECT cp.id, address, city, email, first_name, last_name, phone_number, region, school, candidate_id From candidate_profile cp Where candidate_id=?")) {
            ps.setLong(1, candidate.getId());
            try (ResultSet rs = ps.executeQuery()) {

                CandidateProfileMapper mapper = new CandidateProfileMapper();
                if (rs.next()) {
                    result = Optional.of(mapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
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
