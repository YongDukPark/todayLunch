package TableBean;

public class TODAYLUNCH_LOG_BEAN {
    private String MENU_NO;
    private String MENU_NAME;
    private String LAST_START_TIME;

    public TODAYLUNCH_LOG_BEAN() {
    }

    public TODAYLUNCH_LOG_BEAN(String MENU_NO, String MENU_NAME, String LAST_START_TIME) {
        this.MENU_NO = MENU_NO;
        this.MENU_NAME = MENU_NAME;
        this.LAST_START_TIME = LAST_START_TIME;
    }

    public String getMENU_NO() {
        return MENU_NO;
    }

    public void setMENU_NO(String MENU_NO) {
        this.MENU_NO = MENU_NO;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getLAST_START_TIME() {
        return LAST_START_TIME;
    }

    public void setLAST_START_TIME(String LAST_START_TIME) {
        this.LAST_START_TIME = LAST_START_TIME;
    }
    
    
}
