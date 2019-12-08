package com.reddoor.framework.common;

import java.util.UUID;

/**
 * 
 */
public class Constants {
    public static final String ACTIVE_USER = "activeUser";
    public static final String VALIDATE_CODE = "validateCode";

    public static final String NO_PERMISSION = "no permission : ";

    public static final String ERROR_HAPPENS = "error happens : ";
    
    
    /**加密方法*/
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;	//盐长度
	
	/**JWT*/
	public static final String JWT_ID = UUID.randomUUID().toString();
	public static final String JWT_ISSUER = "RedDoor";

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "JWT-SECRET-2019";
    public static final int JWT_TTL = 30*24*60*60*1000;  //millisecond
}
