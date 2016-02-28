package lian.artyom.scheduler.action.impl;

import lian.artyom.scheduler.action.TaskAction;

/**
 * Created by dodler on 28/02/16.
 */
public class EmptyTaskAction implements TaskAction {
    @Override
    public void perform() {
        System.out.println("Empty action performed");
    }
}
