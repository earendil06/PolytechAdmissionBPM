package processes.publishResults;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;
import processes.manageApplications.Application;

import java.util.Collections;
import java.util.Comparator;

public class SortCandidates implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        AdmissionVariables.getInstance().getApplications().sort(Comparator.comparingDouble(Application::getTotal));
        Collections.reverse(AdmissionVariables.getInstance().getApplications());

        System.out.println("The final sorted results are :");
        for (int i = 0; i < AdmissionVariables.getInstance().getApplications().size(); i++) {
            Application app = AdmissionVariables.getInstance().getApplications().get(i);
            System.out.println((i + 1) + ") " + app.getUsername() + " with total of " + app.getTotal() + ((i < AdmissionVariables.getInstance().getPlaceNumber().getMark()) ? " --> ACCEPTED" : " --> NOT ACCEPTED (good but not enough)"));
        }
        System.out.println("The following applications are eliminations at the interview :");
        for (int i = 0; i < AdmissionVariables.getInstance().getEliminations().size(); i++) {
            Application app = AdmissionVariables.getInstance().getEliminations().get(i);
            System.out.println((i + 1) + ") " + app.getUsername());
        }
        System.out.println("The following applications are under the eligibility threshold :");
        for (int i = 0; i < AdmissionVariables.getInstance().getUnderMark().size(); i++) {
            Application app = AdmissionVariables.getInstance().getUnderMark().get(i);
            System.out.println((i + 1) + ") " + app.getUsername());
        }

        System.out.println("The following applications were incomplete :");
        for (int i = 0; i < AdmissionVariables.getInstance().getIncompletes().size(); i++) {
            Application app = AdmissionVariables.getInstance().getIncompletes().get(i);
            System.out.println((i + 1) + ") " + app.getUsername());
        }
    }
}
