import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CheckCandidateOK implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        delegateExecution.setVariable("recevable", true);
    }
}
