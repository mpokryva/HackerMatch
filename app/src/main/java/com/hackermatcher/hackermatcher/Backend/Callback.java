package com.hackermatcher.hackermatcher.Backend;

/**
 * Created by Miki on 1/21/2018.
 */

public interface Callback<T> {

    public void onEvent(T data);
}

