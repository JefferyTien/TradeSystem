package com.reddoor.framework.common.permission;

public class RequirePermissionException extends Exception {
    public RequirePermissionException(String exception){
        super(exception);
    }

    public RequirePermissionException(){
        super();
    }
}
