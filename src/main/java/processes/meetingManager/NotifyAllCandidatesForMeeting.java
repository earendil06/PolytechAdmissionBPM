package processes.meetingManager;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

public class NotifyAllCandidatesForMeeting implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        //AdmissionVariables.getInstance().getApplications().forEach(c -> System.out.println("Notify " + c.getName() + " to choose a date"));
    }
}
