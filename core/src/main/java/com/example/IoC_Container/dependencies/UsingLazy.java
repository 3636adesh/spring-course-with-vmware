package com.example.IoC_Container.dependencies;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
public class UsingLazy {

    record LazyService(String name) {
    }

    record EagerService(String name) {
    }

    @Configuration
    class LazyCFG{
        @Bean
        @Lazy
        LazyService lazyService() {
            log.info("LazyService bean initialized ");
            return new LazyService("LazyService");
        }

        @Bean
        EagerService eagerService() {
            log.info("EagerService bean initialized ");
            return new EagerService("EagerService");
        }
    }


    @Component
    static class Runner{
//        @Bean
//        ApplicationRunner applicationRunner() {
//            return args -> {
//               System.out.println("Runner bean initialized ");
//            };
//        }
    }



}
