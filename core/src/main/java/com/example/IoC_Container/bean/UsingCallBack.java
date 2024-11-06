package com.example.IoC_Container.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UsingCallBack implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("Properties setting");
    }

    @Override
    public void destroy() throws Exception {
        log.info("Disposing bean");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("PreDestroy");
    }
}
