import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class CheckCandidateMark implements JavaDelegate {
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("dossier complet, on check la note");
    }
}
