package processes.publishResults;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;
import processes.manageApplications.Application;

public class EliminateCandidates implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Application app = delegateExecution.getVariable("application", Application.class);
        AdmissionVariables.getInstance().getApplications().remove(app);
        AdmissionVariables.getInstance().getEliminations().add(app);
        System.out.println("Elimination candidate " + app.getUsername());
    }
}
