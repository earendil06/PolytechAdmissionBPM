package processes.numerusClausus;

import org.flowable.AbstractProcess;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Task;
import processes.AdmissionVariables;
import processes.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DefineNumerusClaususProcess extends AbstractProcess {

    private static final String RESOURCE_XML = "numerusClausus.bpmn20.xml";
    private String key = "numerusClausus";
    private Scanner scanner = new Scanner(System.in);

    public DefineNumerusClaususProcess() {
        super(RESOURCE_XML);
        getProcessEngine().getRuntimeService().startProcessInstanceByKey(key);
    }

    @Override
    public void process() {
        bePolytech();
        beDirectors();
    }

    @Override
    public boolean isCompleted() {
        return AdmissionVariables.getInstance().getPlaceNumber() != null;
    }

    private void bePolytech() {
        Task t = getProcessEngine().getTaskService().createTaskQuery().singleResult();
        Map<String, Object> variables = new HashMap<>();

        System.out.println("Acting as Polytech Nice Sophia");
        System.out.println("Send a number places request");
        System.out.println("How many places ?");

        int number = Integer.parseInt(scanner.nextLine());

        variables.put("number", number);
        getProcessEngine().getTaskService().complete(t.getId(), variables);
    }

    private void beDirectors() {
        System.out.println("Acting as the directors");
        Task t = getProcessEngine().getTaskService().createTaskQuery().singleResult();
        Map<String, Object> processVariables = getProcessEngine().getTaskService().getVariables(t.getId());
        int number = (int) processVariables.get("number");
        System.out.println("Polytech Nice Sophia wants " + number + " places.");
        System.out.println("Do you accept the number ? (y/n)");

        boolean approved = scanner.nextLine().toLowerCase().equals("y");

        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);
        getProcessEngine().getTaskService().complete(t.getId(), variables);


    }
}
