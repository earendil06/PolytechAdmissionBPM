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

    private String key = "defineNumerusClausus";
    private static final String RESOURCE_XML = "Definir_les_places.bpmn20.xml";
    private Scanner scanner = new Scanner(System.in);

    public DefineNumerusClaususProcess() {
        super(RESOURCE_XML);
    }

    @Override
    public void process() {
        switch (chooseARole()){
            case DIRECTORS:
                beDirectors();
                break;
            case POLYTECH:
                bePolytech();
                break;
        }
    }

    private Role chooseARole() {
        System.out.println("Choose a role (DIRECTORS or POLYTECH) :");
        try {
            return Role.valueOf(scanner.nextLine());
        }catch (Exception e){
            return chooseARole();
        }

    }

    @Override
    public boolean isCompleted() {
        return AdmissionVariables.getInstance().getNumerusClausus() != null;
    }

    private void bePolytech() {
        List<Task> tasks = getProcessEngine().getTaskService().createTaskQuery().taskCandidateGroup(Role.DIRECTORS.getCandidateGroup()).list();
        if (tasks.size() == 1){
            System.out.println("You already submitted a request !");
            return;
        }
        System.out.println("Acting as Polytech");
        System.out.println("Send a numerus clausus request");
        System.out.println("How many places ?");

        int number = scanner.nextInt();

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        Map<String, Object> variables = new HashMap<>();

        variables.put("number", number);

        ProcessInstance inst = runtimeService.startProcessInstanceByKey(key, variables);

        System.out.println("Request successfully sent ! #" + inst.getId());
    }

    private void beDirectors() {
        System.out.println("Acting as the directors");

        TaskService taskService = getProcessEngine().getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(Role.DIRECTORS.getCandidateGroup()).list();

        if (tasks.isEmpty()) {
            System.out.println("You have 0 request to validate !");
            return;
        }

        System.out.println("You have " + tasks.size() + " requests :");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ") request #" + tasks.get(i).getProcessInstanceId());
        }

        System.out.println("Which task would you like to process ?");

        int taskIndex = Integer.valueOf(scanner.nextLine());

        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());

        System.out.println("Polytech'Nice wants " +
                processVariables.get("number") + " of places. Do you approve this? (y/n)");

        boolean approved = scanner.nextLine().toLowerCase().equals("y");

        Map<String, Object> variables = new HashMap<>();
        variables.put("approved", approved);

        taskService.complete(task.getId(), variables);
    }
}
