package model.entity;

public enum CandidateStatus {
    ACTIVE, BLOCKED;


    public String getName() {
        return name();
    }

    private static CandidateStatus[] values = CandidateStatus.values();

    public static CandidateStatus getCandidateStatus(int i) {
        return values[i];
    }
}
