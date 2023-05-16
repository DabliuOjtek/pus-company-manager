package com.pus.companymanager.service.healthcheck;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StatusUtil {

    private static final String FAILED = "failed";
    private static final String OK = "ok";

    public static String getStatus(boolean check){
        return check ? OK : FAILED;
    }

    public static String failed(){
        return FAILED;
    }

    public static String ok(){
        return OK;
    }
}
