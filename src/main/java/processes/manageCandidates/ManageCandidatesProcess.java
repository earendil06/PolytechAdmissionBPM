package processes.manageCandidates;

import models.Candidature;
import org.flowable.AbstractProcess;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.task.Task;
import processes.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ManageCandidatesProcess extends AbstractProcess {

    private String key = "manageCandidates";
    private static final String RESOURCE_XML = "Traiter_les_dossiers.bpmn20.xml";

    private Scanner scanner = new Scanner(System.in);
    private boolean complete = false;

    public ManageCandidatesProcess() {
        super(RESOURCE_XML);
    }

    @Override
    public boolean isCompleted() {
        return complete;
    }

    @Override
    public void process() {
        switch (chooseARole()){
            case CANDIDATE:
                beCandidate();
                break;
            case POLYTECH:
                bePolytech();
                break;
            case NONE:
                break;
        }
    }

    private Role chooseARole() {
        System.out.println("Choose a role (POLYTECH or CANDIDATE or next) :");
        String choice = scanner.nextLine();
        try {
            complete = choice.equals("next");
            if (complete) return Role.NONE;
            return Role.valueOf(choice);
        }catch (Exception e){
            return chooseARole();
        }

    }

    private void beCandidate() {
        System.out.println("Acting as a candidature");
        System.out.println("Send a candidature :");
        Scanner scanner= new Scanner(System.in);
        System.out.println("Who are you ?");
        String name = scanner.nextLine();
        System.out.println("what is your mark ?");
        Integer mark = Integer.valueOf(scanner.nextLine());
        System.out.println("Is the candidature complete ?");
        Boolean complete = scanner.nextBoolean();

        Candidature candidature = new Candidature(name, mark, complete);

        RuntimeService runtimeService = getProcessEngine().getRuntimeService();
        Map<String, Object> variables = new HashMap<>();

        variables.put("candidature", candidature);

        ProcessInstance inst = runtimeService.startProcessInstanceByKey(key, variables);
        System.out.println("Candidature sent, #" + inst.getId());
    }



    private void bePolytech() {
        System.out.println("\n\n/**\n * Acting as Polytech\n **/");
        TaskService taskService = getProcessEngine().getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(Role.POLYTECH.getCandidateGroup()).list();
        if (tasks.isEmpty()) {
            System.out.println("No candidatures received yet!");
            return;
        }

        System.out.println("You have " + tasks.size() + " candidatures:");
        for (int i = 0; i < tasks.size(); i++) {
            Candidature candidature = (Candidature) taskService.getVariables(tasks.get(i).getId()).get("candidature");
            System.out.println((i+1) + ") Candidature of " + candidature.getName() + " : #" + tasks.get(i).getProcessInstanceId());
        }
        System.out.println("Which candidature would you like to complete?");
        Scanner scanner = new Scanner(System.in);

        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);

        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        Candidature candidature = (Candidature) processVariables.get("candidature");


        Map<String, Object> variables = new HashMap<>();
        variables.put("recevable", candidature.isComplete());

        if (candidature.isComplete()){
            System.out.println(candidature.getName() + " has a complete candidature");
        }else {
            System.out.println(candidature.getName() + " hasn't a complete candidature");
        }

        taskService.complete(task.getId(), variables);
    }
}
