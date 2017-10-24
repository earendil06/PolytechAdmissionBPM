package processes;

public enum Role {
    CANDIDATE("candidate"),
    DIRECTORS("directors"),
    POLYTECH("polytech"),
    NONE("none")
    ;

    private String candidateGroup;

    Role(String candidateGroup){
        this.candidateGroup = candidateGroup;
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }
}
