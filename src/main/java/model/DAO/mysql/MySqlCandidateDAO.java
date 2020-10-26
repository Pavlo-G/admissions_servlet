package model.DAO.mysql;

import model.DAO.CandidateDAO;
import model.DAO.mapper.CandidateMapper;
import model.DAO.mapper.CandidateProfileMapper;
import model.entity.Candidate;
import model.entity.CandidateProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MySqlCandidateDAO implements CandidateDAO {
    static final Logger LOG = LoggerFactory.getLogger(MySqlCandidateDAO.class);


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
                    Constants.SQL_INSERT_CANDIDATE, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, candidate.getUsername());
            pstmt.setString(2, candidate.getPassword());
            pstmt.setString(3, candidate.getRole().getName());
            pstmt.setString(4, candidate.getCandidateStatus().name());
            candidateId = getGeneratedKey(pstmt);

            pstmt = conn.prepareStatement(Constants.SQL_INSERT_CANDIDATE_PROFILE);
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
            LOG.error("Can not make  transaction",ex);
            throw new SQLException("Can not make  transaction" + ex.getMessage(), ex);
        } finally {
            try {
                assert pstmt != null;
                pstmt.close();
                assert conn != null;
                conn.close();
            } catch (SQLException ex) {
                LOG.error("Can not close statement or connection",ex);
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
    public void delete(Long id) throws SQLException {
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_DELETE_CANDIDATE)) {
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Can not delete candidate", e);
        }

    }

    @Override
    public Optional<Candidate> findById(Long id) throws SQLException {
        Optional<Candidate> candidate = Optional.empty();
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_FIND_CANDIDATE_BY_ID)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                CandidateMapper candidateMapper = new CandidateMapper();
                CandidateProfileMapper candidateProfileMapper = new CandidateProfileMapper();
                while (rs.next()) {
                    candidate = Optional.of(candidateMapper.extractFromResultSet(rs));
                    Optional<CandidateProfile> candidateProfile = Optional.of(candidateProfileMapper.extractFromResultSet(rs));
                    candidate.ifPresent(c -> c.setCandidateProfile(candidateProfile.get()));
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
    public Optional<Candidate> findCandidateByUsername(String username) throws SQLException {
        Optional<Candidate> candidate = Optional.empty();

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(
                     Constants.SQL_FIND_CANDIDATE_BY_USERNAME)) {
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                CandidateMapper candidateMapper = new CandidateMapper();
                if (rs.next())
                    candidate = Optional.ofNullable(candidateMapper.extractFromResultSet(rs));
            }

        } catch (SQLException exp) {
            throw new SQLException("Cannot find  candidate with username: !" + username, exp);
        }
        return candidate;
    }

    @Override
    public boolean updateCandidate(String role, String candidateStatus, Long id) throws SQLException {

        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareStatement(Constants.SQL_UPDATE_CANDIDATE)) {
            pstmt.setString(1, role);
            pstmt.setString(2, candidateStatus);
            pstmt.setLong(3, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new SQLException("Cannot update  candidate with id: " + id, e);
        }

    }


    @Override
    public Optional<CandidateProfile> getCandidateProfile(Candidate candidate) throws SQLException {
        Optional<CandidateProfile> candidateProfile = Optional.empty();
        try (Connection con = connection;
             PreparedStatement pstmt = con.prepareCall(Constants.SQL_FIND_CANDIDATE_PROFILE)) {
            pstmt.setLong(1, candidate.getId());
            try (ResultSet rs = pstmt.executeQuery()) {

                CandidateProfileMapper mapper = new CandidateProfileMapper();
                if (rs.next()) {
                    candidateProfile = Optional.of(mapper.extractFromResultSet(rs));
                }
            }
        } catch (SQLException ex) {
            throw new SQLException("Cannot get candidate profile for candidate with id: " + candidate.getId(), ex);
        }

        return candidateProfile;
    }


    @Override
    public void updateCandidateProfile(CandidateProfile candidateProfile) throws SQLException {

        try (Connection conn = connection;
             PreparedStatement pstmt = conn.prepareCall(Constants.SQL_UPDATE_CANDIDATE_PROFILE)) {
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
        } catch (SQLException ex) {
            throw new SQLException("Cannot update candidate profile with id: " + candidateProfile.getId(), ex);
        }

    }


    @Override
    public List<Candidate> findAll() throws SQLException {
        List<Candidate> listCandidates = new ArrayList<>();

        try (Connection con = connection;
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(Constants.SQL_FIND_ALL_CANDIDATES)) {
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
    public void update(Candidate entity) throws SQLException {

    }
    @Override
    public void create(Candidate entity) throws SQLException {

    }


    @Override
    public void close() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
