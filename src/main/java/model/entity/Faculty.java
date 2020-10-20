package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private Long id;
    private String nameEn;
    private String nameUk;
    private String descriptionEn;
    private String descriptionUk;
    private int budgetCapacity;
    private int totalCapacity;
    private String requiredSubject1En;
    private String requiredSubject1Uk;
    private String requiredSubject2En;
    private String requiredSubject2Uk;
    private String requiredSubject3En;
    private String requiredSubject3Uk;
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

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameUk() {
        return nameUk;
    }

    public void setNameUk(String nameUk) {
        this.nameUk = nameUk;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionUk() {
        return descriptionUk;
    }

    public void setDescriptionUk(String descriptionUk) {
        this.descriptionUk = descriptionUk;
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

    public String getRequiredSubject1En() {
        return requiredSubject1En;
    }

    public void setRequiredSubject1En(String requiredSubject1En) {
        this.requiredSubject1En = requiredSubject1En;
    }

    public String getRequiredSubject1Uk() {
        return requiredSubject1Uk;
    }

    public void setRequiredSubject1Uk(String requiredSubject1Uk) {
        this.requiredSubject1Uk = requiredSubject1Uk;
    }

    public String getRequiredSubject2En() {
        return requiredSubject2En;
    }

    public void setRequiredSubject2En(String requiredSubject2En) {
        this.requiredSubject2En = requiredSubject2En;
    }

    public String getRequiredSubject2Uk() {
        return requiredSubject2Uk;
    }

    public void setRequiredSubject2Uk(String requiredSubject2Uk) {
        this.requiredSubject2Uk = requiredSubject2Uk;
    }

    public String getRequiredSubject3En() {
        return requiredSubject3En;
    }

    public void setRequiredSubject3En(String requiredSubject3En) {
        this.requiredSubject3En = requiredSubject3En;
    }

    public String getRequiredSubject3Uk() {
        return requiredSubject3Uk;
    }

    public void setRequiredSubject3Uk(String requiredSubject3Uk) {
        this.requiredSubject3Uk = requiredSubject3Uk;
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
