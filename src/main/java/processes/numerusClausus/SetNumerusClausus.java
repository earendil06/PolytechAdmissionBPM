package processes.numerusClausus;


import models.NumerusClausus;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

public class SetNumerusClausus implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        int number = delegateExecution.getVariable("number", Integer.class);
        System.out.println("Numerus clausus set to " + number);
        AdmissionVariables.getInstance().setNumerusClausus(new NumerusClausus(number));
    }
}
