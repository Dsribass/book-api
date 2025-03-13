package com.example.domain.utils;

public abstract class Logger {
    private Class<?> clazz;

    public Class<?> getClazz() {
        if (clazz == null) {
            return this.getClass();
        }
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    abstract public void log(String message);
}
