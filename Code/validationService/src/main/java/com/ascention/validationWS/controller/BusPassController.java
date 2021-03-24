package com.ascention.validationWS.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ascention.validationWS.service.BusPassService;

@RestController
public class BusPassController {

    private static Logger log = LoggerFactory.getLogger(BusPassController.class);

    private final BusPassService busPassService;

    @Autowired
    public BusPassController(BusPassService busPassService) {
        this.busPassService = busPassService;
    }

//    @GetMapping(value = "/buspass", 
//            produces = "application/json")
//    public BusPass getQuestionsBusPass(
//            @RequestParam(required = true) String name,
//            @RequestParam(required = true) int age) {
//
//        Person person = new Person(name, age);
//
//        log.debug("Bus pass request received for: " + person);
//        
//        BusPass busPass = busPassService.getBusPass(person);
//
//        return busPass;
//    }

}
