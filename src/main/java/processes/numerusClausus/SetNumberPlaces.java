package processes.numerusClausus;


import models.PlaceNumber;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.ExecutionListener;
import processes.AdmissionVariables;

public class SetNumberPlaces implements ExecutionListener {


    @Override
    public void notify(DelegateExecution delegateExecution) {
        int number = delegateExecution.getVariable("number", Integer.class);
        System.out.println("The number of places has been set to " + number);
        AdmissionVariables.getInstance().setPlaceNumber(new PlaceNumber(number));
    }
}
