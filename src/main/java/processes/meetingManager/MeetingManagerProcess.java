package processes.meetingManager;

import org.flowable.AbstractProcess;

import java.util.*;

public class MeetingManagerProcess extends AbstractProcess {

    //private String key = "organizeInterviews";
    //private static final String RESOURCE_XML = "organizeInterviews.bpmn20.xml";
    private String key = "organizeInterviewsAlt";
    private static final String RESOURCE_XML = "organizeInterviewsAlt.bpmn20.xml";

    public static boolean complete = false;

    private Scanner scanner = new Scanner(System.in);
    private List<String> meetings = new ArrayList<>();

    public MeetingManagerProcess() {
        super(RESOURCE_XML);
        getProcessEngine().getRuntimeService().startProcessInstanceByKey(key);
    }

    @Override
    public boolean isCompleted() {
        return complete;
    }

    @Override
    public void process() {
        //bePolytech();
        //beCandidate();
    }

//    private void beCandidate() {
//        while (!complete){
//            System.out.println("Acting as Candidate");
//            System.out.println("Who are you ?");
//            String name = scanner.nextLine();
//            if (name.equals("next")){
//                complete = true;
//                return;
//            }
//            System.out.println("Choose date :");
//            for (int i = 0; i < meetings.size(); i++) {
//                System.out.println(i + 1 + ") " + meetings.get(i));
//            }
//            int choice = scanner.nextInt();
//            System.out.println(name + " has taken " + meetings.get(choice - 1));
//        }
//    }
//
//
//
//    private void bePolytech() {
//        System.out.println("Acting as Polytech");
//        System.out.println("Create meetings :");
//        String day;
//        do {
//            day = scanner.nextLine();
//            if (!day.equals("next"))meetings.add(day);
//        }while(!day.equals("next"));
//
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("day", day);
//
//        ProcessInstance inst = getProcessEngine().getRuntimeService().startProcessInstanceByKey(key, variables);
//        System.out.println("Meetings created");
//
//        TaskService taskService = getProcessEngine().getTaskService();
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup(Role.POLYTECH.getCandidateGroup()).list();
//
//        getProcessEngine().getTaskService().complete(tasks.get(0).getId(), variables);
//
//    }
}
