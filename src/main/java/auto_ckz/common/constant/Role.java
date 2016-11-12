package auto_ckz.common.constant;

public final class Role {

    public static final String ROLE_DIRECTOR = "ROLE_DIRECTOR";
    public static final String ROLE_MECHANIC = "ROLE_MECHANIC";
    public static final String ROLE_MEMBER = "ROLE_MEMBER";
    public static final String ROLE_CLIENT = "ROLE_CLIENT";

    public static String[] all(){
        return new String[]{ROLE_CLIENT, ROLE_MECHANIC, ROLE_MEMBER, ROLE_CLIENT};
    }

    public static String[] employees(){
        return new String[]{ROLE_MECHANIC, ROLE_MEMBER, ROLE_CLIENT};
    }

}
