package entity;

import java.util.List;

public class Candidate {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private CandidateStatus candidateStatus;
    private CandidateProfile candidateProfile;
    private List<AdmissionRequest> admissionRequestList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public CandidateStatus getCandidateStatus() {
        return candidateStatus;
    }

    public void setCandidateStatus(CandidateStatus candidateStatus) {
        this.candidateStatus = candidateStatus;
    }

    public CandidateProfile getCandidateProfile() {
        return candidateProfile;
    }

    public void setCandidateProfile(CandidateProfile candidateProfile) {
        this.candidateProfile = candidateProfile;
    }

    public List<AdmissionRequest> getAdmissionRequestList() {
        return admissionRequestList;
    }

    public void setAdmissionRequestList(List<AdmissionRequest> admissionRequestList) {
        this.admissionRequestList = admissionRequestList;
    }
}
