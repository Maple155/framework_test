package com.test.test;

import annotation.Controller;
import annotation.GetURL;

@Controller
public class Test2 {
    
    @GetURL(url = "/sum")
    public void getSum () {}

}
