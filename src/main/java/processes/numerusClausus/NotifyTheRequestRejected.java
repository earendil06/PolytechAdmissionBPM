package processes.numerusClausus;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;

public class NotifyTheRequestRejected implements ExecutionListener {
    @Override
    public void notify(DelegateExecution delegateExecution) {
        System.out.println("Notify Polytech Nice Sophia the request has been rejected");
    }
}
