package processes.common;

import models.Candidature;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class NotifyCandidateFail implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Candidature candidature = delegateExecution.getVariable("candidature", Candidature.class);
        System.out.println("Notify " + candidature.getName() + " : you failed...");
    }
}
