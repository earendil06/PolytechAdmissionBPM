package processes.publishResults;

import org.flowable.AbstractProcess;
import org.flowable.engine.task.Task;
import processes.AdmissionVariables;
import processes.manageApplications.Application;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PublishResultsProcess extends AbstractProcess{
    private static final String RESOURCE_XML = "publishResults.bpmn20.xml";
    private static final double ELIMINATION = 5;
    private String key = "publishResults";
    private Scanner scanner = new Scanner(System.in);
    private boolean completed = false;
    private Map<String, Application> tuplesAssociated = new HashMap<>();

    public PublishResultsProcess() {
        super(RESOURCE_XML);

        Map<String, Object> variables = new HashMap<>();
        variables.put("applications", AdmissionVariables.getInstance().getApplications());
        getProcessEngine().getRuntimeService().startProcessInstanceByKey(key, variables);

        for (int i = 0; i < AdmissionVariables.getInstance().getApplications().size(); i++) {
            tuplesAssociated.put(getProcessEngine().getTaskService().createTaskQuery().list().get(i).getId(), AdmissionVariables.getInstance().getApplications().get(i));
        }

    }

    @Override
    public void process() {
        while(getProcessEngine().getTaskService().createTaskQuery().list().size() > 0){
            saveOneApplication();
        }
        completed = true;
    }

    private void saveOneApplication() {
        System.out.println("Acting as Polytech Nice Sophia");
        System.out.println("Record all the results of the interviews");
        List<Task> list = getProcessEngine().getTaskService().createTaskQuery().list();
        System.out.println("There are " + list.size() + " to perform :");
        for (int i = 0; i < list.size(); i++) {
            Application application = tuplesAssociated.get(list.get(i).getId());
            System.out.println((i + 1) + ") Application of " + application.getUsername());
        }
        System.out.print("Choose which one :");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;
        Application application = tuplesAssociated.get(list.get(choice).getId());

        System.out.println("You are recording the application of " + application.getUsername());
        System.out.println("He has got " + application.getMarkInterview() + " at the interview");
        System.out.println("Record it ?(y/n)");

        if (scanner.nextLine().toLowerCase().equals("y")){
            Map<String, Object> variables = new HashMap<>();
            variables.put("eliminate", application.getMarkInterview() < ELIMINATION);
            variables.put("application", application);
            getProcessEngine().getTaskService().complete(list.get(choice).getId(), variables);
        }



    }

    @Override
    public boolean isCompleted() {
        return completed;
    }
}
