package lian.artyom.scheduler.action.impl;

import lian.artyom.scheduler.action.TaskAction;

/**
 * Created by dodler on 28/02/16.
 */
public class SampleTaskAction implements TaskAction {
    @Override
    public void perform() {
        System.out.println("Hello world!");
    }
}
