package com.example.IoC_Container.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestForRequestScope {

    private final UsingBeanScopes.RequestBean requestBean;

    public TestForRequestScope(UsingBeanScopes.RequestBean requestBean) {
        this.requestBean = requestBean;
    }

    @GetMapping("/request-scope/1")
    public String testingForRequestBean() {
        log.info("Request: {}",requestBean);
        return "success";
    }
}
