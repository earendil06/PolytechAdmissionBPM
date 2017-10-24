package org.flowable;

import org.flowable.engine.ProcessEngine;

public interface IFlowableProcess {
    default void execute(){
        while (!isCompleted()){
            process();
        }
    }

    void process();

    boolean isCompleted();
    ProcessEngine getProcessEngine();
}
