package processes.manageCandidates;

import models.Candidature;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

public class SaveCandidatureDatabase implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Candidature candidature = delegateExecution.getVariable("candidature", Candidature.class);
        AdmissionVariables.getInstance().getCandidatures().add(candidature);
        System.out.println("Candidature of " + candidature.getName() + " saved in the database");
    }
}
