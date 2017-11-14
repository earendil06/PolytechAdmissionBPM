import org.flowable.IFlowableProcess;
import org.flowable.engine.ProcessEngines;
import processes.meetingManager.MeetingManagerProcess;
import processes.minimumMark.MinimumMarkProcess;
import processes.numerusClausus.DefineNumerusClaususProcess;
import processes.manageApplications.ManageApplicationsProcess;
import processes.publishResults.PublishResultsProcess;


public class Main {

    public static void main(String[] args) {
        IFlowableProcess process1 = new DefineNumerusClaususProcess();
        process1.execute();
        IFlowableProcess process2 = new ManageApplicationsProcess();
        process2.execute();
        IFlowableProcess process3 = new MinimumMarkProcess();
        process3.execute();
        IFlowableProcess process4 = new MeetingManagerProcess();
        process4.execute();
        IFlowableProcess process5 = new PublishResultsProcess();
        process5.execute();
        ProcessEngines.destroy();
    }
}