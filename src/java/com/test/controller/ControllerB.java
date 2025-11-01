package com.test.controller;

import annotation.Controller;
import annotation.GetURL;

@Controller
public class ControllerB {
    
    @GetURL(url = "/testController")
    public void testController () {}

}
