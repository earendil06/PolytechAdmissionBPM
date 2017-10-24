package processes;

import models.Candidature;
import models.Mark;
import models.NumerusClausus;

import java.util.ArrayList;
import java.util.List;

public class AdmissionVariables {
    private static AdmissionVariables instance;
    private Mark minimumMark;

    public static AdmissionVariables getInstance(){
        if (instance == null){
            instance = new AdmissionVariables();
        }
        return instance;
    }


    //TODO remove the initialization for numerus clausus
    private NumerusClausus numerus = new NumerusClausus(100);
    private List<Candidature> candidatures = new ArrayList<>();

    public List<Candidature> getCandidatures() {
        return candidatures;
    }

    public NumerusClausus getNumerusClausus() {
        return numerus;
    }

    public void setNumerusClausus(NumerusClausus numerus) {
        this.numerus = numerus;
    }

    public void setMinimumMark(Mark minimumMark) {
        this.minimumMark = minimumMark;
    }

    public Mark getMinimumMark() {
        return minimumMark;
    }
}
