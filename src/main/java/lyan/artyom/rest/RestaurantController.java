package lyan.artyom.rest;

import com.sun.tracing.dtrace.ProviderAttributes;
import lyan.artyom.rest.service.TaskService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is simple controller for rest application.
 * It handles all requests, delegaets it to {@link TaskService} and
 * returnes JSON answer.
 * All methods return {@code RestaurantController.JSON_TEMPLATE},
 * that returns JSON representation of object.
 *
 * Created by lyan on 13.09.16.
 */

@RestController
@RequestMapping("/data")
public class RestaurantController {

    private static final Logger logger = Logger.getLogger(RestaurantController.class);
    public static final String JSON_TEMPLATE = "jsonTemplate";

    @Autowired
    TaskService service;

    /**
     * This method recieves request to server
     * and create new task.
     * @param model model to put JSON reponse in. Response contains new
     *              task id. This thing is common for all methods represented
     *              in this class, so further methods description is omitted.
     * @return redirect to json view. Same remark here.
     */
    @RequestMapping(value="/task/create")
    public String createTask(Model model) {
        model.addAttribute("msg", service.createTask("", "", "", "", "", ""));
//        return service.getTask(service.createTask("","","","","",""));
        return JSON_TEMPLATE;
    }

    /**
     * This method recieves request to server
     * and create new task.
     * @param model model to put JSON reponse in. Response contains new
     *              task id. This thing is common for all methods represented
     *              in this class, so further methods description is omitted.
     * @return redirect to json view. Same remark here.
     */
    @RequestMapping(value="/task/create/{name}/{status}/{time}/{action}/{comment}/{alarm}")
    public String createTask(Model model,
                             @PathVariable String name,
                             @PathVariable String status,
                             @PathVariable String time,
                             @PathVariable String action,
                             @PathVariable String comment,
                             @PathVariable String alarm) {

        model.addAttribute("msg", service.createTask(name, status, time, action, comment,alarm));
//        return service.getTask(service.createTask("","","","","",""));
        return JSON_TEMPLATE;
    }

    /**
     * This method retrieves from db list of all tasks.
     * @param model
     * @return
     */
    @RequestMapping(value = "/task/all")
    public String getAllTask(Model model){
        model.addAttribute("Tasks", service.getAllTasks());
        return "jsonTemplate";
    }

    /**
     * This method retrievs concrete task from by given id.
     * @param id task id, that should be passed in request.
     * @param modelMap
     * @return
     */
    @RequestMapping(value="/task/find/id/{id}", method = RequestMethod.GET)
    public String findTaskById(@PathVariable String id, ModelMap modelMap){
        modelMap.addAttribute("Task", service.getTask(id));
        return JSON_TEMPLATE;
    }

    /**
     * Find list of tasks with name like given one.
     * @param name filter task name.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/task/find/name/{name}", method = RequestMethod.GET)
    public String findTaskByName(@PathVariable String name, ModelMap modelMap){
        modelMap.addAttribute("Task", service.getTaskByName(name));
        return JSON_TEMPLATE;
    }

    /**
     * This method retrieves tasks with status equals to given one.
     * @param status task status.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/task/find/status/{status}", method = RequestMethod.GET)
    public String findTaskByStatus(@PathVariable String status, ModelMap modelMap){
        modelMap.addAttribute("Task", service.getTaskByStatus(status));
        return JSON_TEMPLATE;
    }

    /**
     * This method deletes task from db by given id.
     * @param id task id to delete.
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/task/delete/{id}", method = RequestMethod.GET)
    public String deleteTaskById(@PathVariable String id, ModelMap modelMap){
        service.deleteTask(id);
        modelMap.addAttribute("Task", id);
        return JSON_TEMPLATE;
    }

    /**
     * This method updates task with given id.
     * Existing task name will be replaced with given one.
     * @param id task id.
     * @param newName task new name.
     * @return
     */
    @RequestMapping(value = "/task/update/{id}/name/{newName}", method = RequestMethod.GET)
    public String updateName(@PathVariable String id, @PathVariable String newName, ModelMap modelMap){
        service.modifyTask(id, newName, null,null,null,null,null);
        modelMap.addAttribute("task", id);
        return JSON_TEMPLATE;
    }

    /**
     * This method updates task status with given one.
     * Existing task status will be replaced with given one.
     * @param id task id.
     * @param newStatus task new status.
     * @return
     */
    @RequestMapping(value = "/task/update/{id}/status/{status}", method = RequestMethod.GET)
    public String updateStatus(@PathVariable String  id, @PathVariable String newStatus){
        service.modifyTask(id, null, newStatus, null, null, null, null);
        return JSON_TEMPLATE;
    }

    /**
     * This method updates task action with given actionId.
     * Existing task action will be replaced with action,
     * that has given actionId.
     * @param id task id.
     * @param actionId new task action id.
     * @return
     */
    @RequestMapping(value = "/task/update/{id}/action/{actionId}", method = RequestMethod.GET)
    public String updateAction(@PathVariable String id, @PathVariable String actionId){
        service.modifyTask(id, null, null, null, actionId, null, null);
        return JSON_TEMPLATE;
    }

}
