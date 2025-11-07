package com.test.test;

import annotation.Controller;
import annotation.GetURL;
import service.ModelView;

@Controller
public class Test2 {
    
    @GetURL(url = "/test2")
    public ModelView getTest2 () {
        return new ModelView("bonjour.html");
    }

}