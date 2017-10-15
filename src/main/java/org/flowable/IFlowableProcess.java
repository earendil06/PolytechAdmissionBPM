package org.flowable;

import org.flowable.engine.ProcessEngine;

public interface IFlowableProcess {
    void execute();
    ProcessEngine getProcessEngine();
}
