package com.contas.pagar.configuration;


import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Objects;

import static java.util.Objects.nonNull;

public class ContextProvider {
    private static ApplicationContext context;

    ContextProvider() {
    }

    public static ApplicationContext get() {
        Objects.requireNonNull(context, "Contexto não inicializado");
        return context;
    }

    public static <T> T autowire(T bean) {
        get().getAutowireCapableBeanFactory().autowireBean(bean);
        return bean;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static <T> T getBean(String name, @Nullable Class<T> requiredType) {
        return context.getBean(name, requiredType);
    }

    public static Object getBean(String name, Object... params) {
        return context.getBean(name, params);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return context.getBean(requiredType);
    }

    public static <T> T getBean(Class<T> requiredType, Object... params) {
        return context.getBean(requiredType, params);
    }

    public static void setContext(ApplicationContext context) {
        ContextProvider.context = context;
    }

    public static boolean hasContext() {
        return nonNull(context);
    }
}
