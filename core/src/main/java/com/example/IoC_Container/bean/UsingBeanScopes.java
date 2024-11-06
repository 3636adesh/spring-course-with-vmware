package com.example.IoC_Container.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

import java.util.function.Consumer;

/**
 * Demonstrates various Spring bean scopes and their usage.
 *
 * <p>Spring offers several scopes for managing the lifecycle of beans:
 *
 * <ul>
 *   <li><b>singleton</b> - (Default) A single bean instance per Spring IoC container.</li>
 *   <li><b>prototype</b> - A new bean instance is created each time it is requested.</li>
 *   <li><b>request</b> - A single bean instance for each HTTP request (web context only).</li>
 *   <li><b>session</b> - A single bean instance per HTTP session (web context only).</li>
 *   <li><b>application</b> - A single bean instance per ServletContext (web context only).</li>
 *   <li><b>websocket</b> - A single bean instance per WebSocket session (web context only).</li>
 * </ul>
 *
 * <p>These scopes help control bean lifecycle based on application requirements, especially
 * in a web environment where multiple requests, sessions, or WebSocket connections
 * may need isolated instances.
 */

@Configuration
@Slf4j
public class UsingBeanScopes {

    static class SingleTonBean {}
    static class PrototypeBean {}
    static class RequestBean{}
    static class SessionBean{}

    @Bean
    SingleTonBean singleTonBean() {
        return new SingleTonBean();
    }

    @Bean
    @Scope("prototype")
    PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    RequestBean requestBean() {
        return new RequestBean();
    }


    /**
     * Creates a session-scoped {@link SessionBean} bean.
     * <p>
     * The bean is scoped to the HTTP session, meaning a new instance is created per session.
     * A proxy is used with {@link ScopedProxyMode#TARGET_CLASS} to enable access in other scopes.
     * </p>
     *
     * Handling for {@link jakarta.servlet.http.HttpSession}
     *
     * @return a new {@link SessionBean} instance for the current HTTP session.
     */

    @Bean
    @Scope(value = "session",proxyMode = ScopedProxyMode.TARGET_CLASS)
    SessionBean sessionBean() {
        return new SessionBean();
    }

    @Bean
    ApplicationRunner runner1(SingleTonBean singleTonBean, PrototypeBean prototypeBean) {
        return args -> {
           singleTonBeanConsumer.accept(singleTonBean);
           prototypeBeanConsumer.accept(prototypeBean);
        };
    }

    @Bean
    ApplicationRunner runner2(SingleTonBean singleTonBean, PrototypeBean prototypeBean) {
        return args -> {
            singleTonBeanConsumer.accept(singleTonBean);
            prototypeBeanConsumer.accept(prototypeBean);
        };
    }

    Consumer<SingleTonBean> singleTonBeanConsumer = singleTonBean-> log.info("Singleton : " + singleTonBean);
    Consumer<PrototypeBean> prototypeBeanConsumer = prototypeBean-> log.info("Prototype: " + prototypeBean);



}
