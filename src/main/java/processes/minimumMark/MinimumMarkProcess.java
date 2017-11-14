package processes.minimumMark;

import models.Mark;
import org.flowable.AbstractProcess;
import org.flowable.engine.task.Task;
import processes.AdmissionVariables;
import processes.manageApplications.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinimumMarkProcess extends AbstractProcess {


    private String key = "defineMinimalMark";
    private static final String RESOURCE_XML = "defineMinimalMark.bpmn20.xml";
    private Scanner scanner = new Scanner(System.in);

    public MinimumMarkProcess() {
        super(RESOURCE_XML);
        getProcessEngine().getRuntimeService().startProcessInstanceByKey(key);
    }

    @Override
    public boolean isCompleted() {
        return AdmissionVariables.getInstance().getMinimumMark() != null;
    }

    @Override
    public void process() {
        beDirectors();
    }

    private void beDirectors() {
        System.out.println("Acting as the directors");
        Task t = getProcessEngine().getTaskService().createTaskQuery().singleResult();

        displayStatsOfTheYear();

        System.out.print("Enter the eligibility threshold : ");

        double minimum = Double.parseDouble(scanner.nextLine());

        AdmissionVariables.getInstance().setMinimumMark(new Mark(minimum));

        getProcessEngine().getTaskService().complete(t.getId());
    }

    private void displayStatsOfTheYear() {
        double average = AdmissionVariables.getInstance().getApplications()
                .stream().map(a -> Double.parseDouble((String) a.getInformations().get(Application.Data.MARK.toString())))
                .reduce(0d, (a, b) -> a + b) / AdmissionVariables.getInstance().getApplications().size();

        System.out.println("/***** Stats of the year *****/");
        System.out.println("AVERAGE MARK : " + average);
    }
}
