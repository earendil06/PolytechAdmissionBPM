package processes.minimumMark;

import models.Mark;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import processes.AdmissionVariables;

import java.util.Scanner;

public class DefineMinimumMark implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Defining the minimum mark for the admission :");
        Scanner scanner = new Scanner(System.in);
        double mark = scanner.nextDouble();

        AdmissionVariables.getInstance().setMinimumMark(new Mark(mark));
        System.out.println("Admission mark is set to " + mark);
    }
}
