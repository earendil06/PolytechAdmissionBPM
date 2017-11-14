package processes.manageApplications;

import org.flowable.AbstractProcess;
import org.flowable.engine.TaskService;
import org.flowable.engine.task.Task;
import processes.AdmissionVariables;
import processes.Role;

import java.util.*;
import java.util.stream.Collectors;

public class ManageApplicationsProcess extends AbstractProcess {
    private String key = "manageApplications";
    private static final String RESOURCE_XML = "manageApplications.bpmn20.xml";
    private Scanner scanner = new Scanner(System.in);
    private boolean complete = false;

    private List<String> usernamePending = new ArrayList<>();

    public ManageApplicationsProcess() {
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
        String choice = scanner.nextLine().toUpperCase();
        try {
            complete = choice.equals("NEXT");
            if (complete) return Role.NONE;
            return Role.valueOf(choice);
        }catch (Exception e){
            return chooseARole();
        }

    }

    private void beCandidate() {
        System.out.println("Acting as a candidate");
        System.out.println("You are on the website to manage your application");
        System.out.print("Log in the manager :");
        String usernameInput;
        do {
             usernameInput = scanner.nextLine();
        } while (usernameInput.trim().equals(""));
        final String username = usernameInput;

        if (AdmissionVariables.getInstance()
                .getApplications()
                .stream().map(Application::getUsername).collect(Collectors.toList())
                .contains(username)){
            System.out.println("Application status : Saved in the database");
            return;
        }
        if(usernamePending.contains(username)){
            System.out.println("Application status : Waiting for check");
            return;
        }
        System.out.println("Hello " + username + "! Please send a complete application");

        Application app = new Application();
        app.setUsername(username);

        for (Application.Data data : Application.Data.values()) {
            System.out.print(data + " :");
            String value = scanner.nextLine();
            if (!value.equals("")){
                app.getInformations().put(data.toString(), value);
            }
        }

        Map<String, Object> variables = new HashMap<>();
        variables.put("application", app);
        usernamePending.add(username);
        getProcessEngine().getRuntimeService().startProcessInstanceByMessage("newApplicationReceived", variables);
    }


    private void bePolytech() {
        System.out.println("Acting as Polytech Nice Sophia");
        TaskService taskService = getProcessEngine().getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(Role.POLYTECH.getCandidateGroup()).list();
        if (tasks.isEmpty()) {
            System.out.println("No applications received yet!");
            return;
        }

        System.out.println("You have " + tasks.size() + " applications :");
        for (int i = 0; i < tasks.size(); i++) {
            Application application = (Application) taskService.getVariables(tasks.get(i).getId()).get("application");
            System.out.println((i + 1) + ") Application of " + application.getUsername() + " : #" + tasks.get(i).getProcessInstanceId());
        }
        System.out.println("Which application would you want to check ?");
        Scanner scanner = new Scanner(System.in);

        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);

        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        Application application = (Application) processVariables.get("application");

        Map<String, Object> variables = new HashMap<>();

        System.out.println("This application seems to be " + (application.isComplete() ? "a COMPLETE" : "an INCOMPLETE") + " one");
        System.out.println("Do you approve this (y/n) ?");
        if (scanner.nextLine().toLowerCase().equals("y")){
            variables.put("complete", application.isComplete());
            usernamePending.remove(application.getUsername());
            getProcessEngine().getTaskService().complete(task.getId(), variables);
            if (!application.isComplete()){
                AdmissionVariables.getInstance().getApplications().remove(application);
                AdmissionVariables.getInstance().getIncompletes().add(application);
            }
        }
    }
}
