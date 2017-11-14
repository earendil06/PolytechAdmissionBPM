package processes.minimumMark;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;
import processes.manageApplications.Application;

import java.util.List;
import java.util.stream.Collectors;

public class FilterApplications implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Eliminating all the candidature below the eligibility threshold...");

        List<Application> fails =
                AdmissionVariables.getInstance().getApplications()
                        .stream()
                        .filter(f -> Double.parseDouble((String) f.getInformations().get(Application.Data.MARK.toString())) < AdmissionVariables.getInstance().getMinimumMark().getMark())
                        .collect(Collectors.toList());
        for (Application fail : fails) {
            System.out.println("Removing the candidature of " + fail.getUsername() + " : mark -> " + Double.parseDouble((String) fail.getInformations().get(Application.Data.MARK.toString())));
            AdmissionVariables.getInstance().getApplications().remove(fail);
            AdmissionVariables.getInstance().getUnderMark().add(fail);
        }

    }
}
