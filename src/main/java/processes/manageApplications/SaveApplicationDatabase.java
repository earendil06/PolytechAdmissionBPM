package processes.manageApplications;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

public class SaveApplicationDatabase implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        Application application = delegateExecution.getVariable("application", Application.class);
        AdmissionVariables.getInstance().getApplications().add(application);
        System.out.println("The application of " + application.getUsername() + " has been saved!");
    }
}
