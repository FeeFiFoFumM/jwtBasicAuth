package com.basicjwt.basicjwt.utils;

import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {
    public Date now() {
        return new Date();
    }
}
