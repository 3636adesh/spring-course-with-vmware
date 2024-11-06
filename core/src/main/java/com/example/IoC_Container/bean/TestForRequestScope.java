package com.example.IoC_Container.bean;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestForRequestScope {

    private final UsingBeanScopes.RequestBean requestBean;

    public TestForRequestScope(UsingBeanScopes.RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    @GetMapping("/request-scope/1")
    public String testingForRequestBean() {
        System.out.println("Request: "+requestBean);
        return "success";
    }
}
