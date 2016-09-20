package lyan.artyom.rest;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static lyan.artyom.rest.RestaurantController.JSON_TEMPLATE;

/**
 * This class is responsible for help output and only.
 * This controller outputs short manual for system.
 * Created by lyan on 17.09.16.
 */
@RestController
public class HelpController {

    private static final String HELP = "This is simple CRUD application, that uses spring API. Author - Lyan Artyom." +
            "Print data task create to create empty task with default parameters. System will show task id. \n" +
            "Print data task create {name} {status} {time} {action} {comment} {alarm} here place in brackets concrete parameters. " +
            "here name is any string without spaces, status can be true or false, time should be entered as YYYY-MM-DD, action is id of action to perform," +
            "this functionality is not still implemented clearly, comment is string without spaces, alarm can be true or false. \n" +
            "Print data task all to list all tasks. \n" +
            "Print data task find id {id}, here id should task id - integer number. \n" +
            "Print data task find name {name} to filter tasks according to given name. Name should be given without spaces and brackets. \n" +
            "Print data task find status {status} to filter tasks according to given status. Status should be true or false and given without brackets. \n" +
            "Print data task delete {id} to delete task by given id. Id should be integer number and exist in database. \n" +
            "Print data task update {id} name {newName} to update task name with given id by given newName. \n" +
            "Print data task update {id} status {status} to update task status with given id by given status. \n" +
            "Print data task update {id} action {actionId} to update task action with given id by given actionId. \n";

    @RequestMapping(value = "/help")
    public String help(ModelMap  modelMap){
        modelMap.addAttribute("help", HELP);
        return JSON_TEMPLATE;
    }
}
