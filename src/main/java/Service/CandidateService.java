package Service;

import exception.CandidateNotFoundException;
import exception.DbProcessingException;
import model.DAO.CandidateDAO;
import model.DAO.DAOFactory;
import model.DAO.mysql.ConnectionPoolHolder;
import model.entity.Candidate;
import model.entity.CandidateProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;

public class CandidateService {


    DAOFactory daoFactory = DAOFactory.getDAOFactory(1);


    public List<Candidate> findAll() {

        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findAll();
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void delete(Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.delete(candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not delete candidate with id:" + candidateId);
        }
    }

    public void update(String role, String candidateStatus, Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.updateCandidate(role, candidateStatus, candidateId);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not update  status of candidate with id: " + candidateId);
        }
    }

    public Candidate findById(Long candidateId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findById(candidateId)
                    .orElseThrow(() -> new CandidateNotFoundException("Can not find candidate with id: " + candidateId));
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public Optional<CandidateProfile> getCandidateProfile(Candidate candidate) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.getCandidateProfile(candidate);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void updateCandidateProfile(CandidateProfile candidateProfile) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.updateCandidateProfile(candidateProfile);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not update candidate profile with id:" + candidateProfile.getId());
        }
    }

    public Optional<Candidate> findCandidateByUsername(String username) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.findCandidateByUsername(username);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }

    public void create(Candidate candidate, CandidateProfile candidateProfile) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            dao.insertCandidate(candidate, candidateProfile);
        } catch (SQLException e) {
            throw new DbProcessingException("Can not create Candidate!");
        }
    }


    public String saveFile(HttpServletRequest request) throws IOException {


        Part filePart = null;
        try {
            filePart = request.getPart("file");
            if (!(filePart.getSize() > 0)) {
                return "";
            }

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        try (InputStream in = ConnectionPoolHolder.class.getClassLoader().getResourceAsStream("db\\db.properties")) {
            properties.load(in);
        }

        String uuidFile = UUID.randomUUID().toString();
        String fileName = uuidFile + "." + getFileName(filePart);

        try (OutputStream out = new FileOutputStream(new File(properties.getProperty("path") + File.separator + fileName));
             InputStream filecontent = filePart.getInputStream();
        ) {
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
           out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileName;
    }



    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    public Optional<CandidateProfile> getCandidateProfileById(Long candidateProfileId) {
        try (CandidateDAO dao = daoFactory.getCandidateDAO()) {
            return dao.getCandidateProfileById(candidateProfileId);
        } catch (SQLException e) {
            throw new DbProcessingException(e.getMessage());
        }
    }
}
