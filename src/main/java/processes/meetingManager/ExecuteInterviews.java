package processes.meetingManager;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import processes.AdmissionVariables;

import java.util.Random;

public class ExecuteInterviews implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) {
        AdmissionVariables.getInstance().getApplications().forEach(a ->
        {
            double interviewMark = randDouble(0, 20);
            a.setMarkInterview(interviewMark);
            System.out.println(a.getUsername() + " has passed the interview and he got a mark of " + a.getMarkInterview());
        });

        MeetingManagerProcess.complete = true;
    }

    private static double randDouble(double a, double b){
        Random r = new Random();
        return (double)((int)((a + (b - a) * r.nextDouble()) * 100)) / 100;
    }
}
