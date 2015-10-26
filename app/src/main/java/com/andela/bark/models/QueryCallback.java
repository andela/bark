package com.andela.bark.models;

import java.util.List;

/**
 * Created by andela-cj on 26/10/2015.
 */
public interface QueryCallback {
    void onSuccess(List<?> list);
    void onError(Exception e);
}
