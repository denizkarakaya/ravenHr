package com.raven.hr.controller;

import com.raven.hr.model.EmpRestURIConstants;
import com.raven.hr.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class TestController {

    //Map to store employees, ideally we should use database
    Map<Integer, Employee> empData = new HashMap<Integer, Employee>();

    @RequestMapping(value = EmpRestURIConstants.DUMMY_EMP, method = RequestMethod.GET)
    public @ResponseBody
    Employee getDummyEmployee() {
        Employee emp = new Employee();
        emp.setId(9999);
        emp.setName("Dummy");
        emp.setCreatedDate(new Date());
        empData.put(9999, emp);
        return emp;
    }

    @RequestMapping(value = EmpRestURIConstants.GET_EMP, method = RequestMethod.GET)
    public @ResponseBody
    Employee getEmployee(@PathVariable("id") int empId) {


        return empData.get(empId);
    }

    @RequestMapping(value = EmpRestURIConstants.GET_ALL_EMP, method = RequestMethod.GET)
    public @ResponseBody
    List<Employee> getAllEmployees() {

        List<Employee> emps = new ArrayList<Employee>();
        Set<Integer> empIdKeys = empData.keySet();
        for (Integer i : empIdKeys) {
            emps.add(empData.get(i));
        }
        return emps;
    }

    @RequestMapping(value = EmpRestURIConstants.CREATE_EMP, method = RequestMethod.POST)
    public @ResponseBody
    Employee createEmployee(@RequestBody Employee emp) {

        emp.setCreatedDate(new Date());
        empData.put(emp.getId(), emp);
        return emp;
    }

    @RequestMapping(value = EmpRestURIConstants.DELETE_EMP, method = RequestMethod.PUT)
    public @ResponseBody
    Employee deleteEmployee(@PathVariable("id") int empId) {

        Employee emp = empData.get(empId);
        empData.remove(empId);
        return emp;
    }

    @RequestMapping(value = "/login/{name}/{password}", method = RequestMethod.GET)
    public @ResponseBody
    String Login(@PathVariable("name") String name, @PathVariable("password") String pass) {

        if (StringUtils.equals(name, "milena") && StringUtils.equals(pass, "123")) {
            return "true";
        } else
            return "false";
    }

}
