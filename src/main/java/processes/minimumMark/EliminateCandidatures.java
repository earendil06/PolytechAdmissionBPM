package processes.minimumMark;

import models.Candidature;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

import java.util.List;
import java.util.stream.Collectors;

public class EliminateCandidatures implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Eliminating all the candidature below the minimum mark...");

        List<Candidature> fails =
                AdmissionVariables.getInstance().getCandidatures()
                        .stream()
                        .filter(f -> f.getMark() < AdmissionVariables.getInstance().getMinimumMark().getMark())
                        .collect(Collectors.toList());
        for (Candidature fail : fails) {
            System.out.println("Removing the candidature of " + fail.getName() + " : mark -> " + fail.getMark());
            AdmissionVariables.getInstance().getCandidatures().remove(fail);
        }

    }
}
