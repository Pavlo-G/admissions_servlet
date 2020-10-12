package entity;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private Long id;
    private String name;
    private String description;
    private int budgetCapacity;
    private int totalCapacity;
    private String requiredSubject1;
    private String requiredSubject2;
    private String requiredSubject3;
    private boolean admissionOpen;
    private List<AdmissionRequest> admissionRequestList = new ArrayList<>();

    public Long numberOfRequestsNew() {
        return getAdmissionRequestList().stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.NEW.ordinal())
                .count();
    }

    public Long numberOfRequestsApproved() {
        return getAdmissionRequestList().stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.APPROVED.ordinal())
                .count();
    }

    public Long numberOfRequestsRejected() {
        return getAdmissionRequestList().stream()
                .filter(ar -> ar.getAdmissionRequestStatus().ordinal() == AdmissionRequestStatus.REJECTED.ordinal())
                .count();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBudgetCapacity() {
        return budgetCapacity;
    }

    public void setBudgetCapacity(int budgetCapacity) {
        this.budgetCapacity = budgetCapacity;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public String getRequiredSubject1() {
        return requiredSubject1;
    }

    public void setRequiredSubject1(String requiredSubject1) {
        this.requiredSubject1 = requiredSubject1;
    }

    public String getRequiredSubject2() {
        return requiredSubject2;
    }

    public void setRequiredSubject2(String requiredSubject2) {
        this.requiredSubject2 = requiredSubject2;
    }

    public String getRequiredSubject3() {
        return requiredSubject3;
    }

    public void setRequiredSubject3(String requiredSubject3) {
        this.requiredSubject3 = requiredSubject3;
    }

    public boolean isAdmissionOpen() {
        return admissionOpen;
    }

    public void setAdmissionOpen(boolean admissionOpen) {
        this.admissionOpen = admissionOpen;
    }

    public List<AdmissionRequest> getAdmissionRequestList() {
        return admissionRequestList;
    }

    public void setAdmissionRequestList(List<AdmissionRequest> admissionRequestList) {
        this.admissionRequestList = admissionRequestList;
    }
}
