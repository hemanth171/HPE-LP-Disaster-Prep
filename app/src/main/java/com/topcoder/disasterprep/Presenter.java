package com.topcoder.disasterprep;

public abstract class Presenter<T> {
    protected T view;

    public Presenter(T view) {
        this.view = view;
    }
}
