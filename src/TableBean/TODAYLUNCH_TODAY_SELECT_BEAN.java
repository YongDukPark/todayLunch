package TableBean;

public class TODAYLUNCH_TODAY_SELECT_BEAN {
    private String MENU_SELECT;
    private String MENU_NO;
    private String MENU_NAME;
    private int MENU_RESET_COUNT;
    private String SELECT_YEAR;
    private String SELECT_MONTH;
    private String SELECT_DAY;
    private String LAST_START_TIME;

    public TODAYLUNCH_TODAY_SELECT_BEAN() {
    }

    public TODAYLUNCH_TODAY_SELECT_BEAN(String MENU_SELECT, String MENU_NO, String MENU_NAME, int MENU_RESET_COUNT, String SELECT_YEAR, String SELECT_MONTH, String SELECT_DAY, String LAST_START_TIME) {
        this.MENU_SELECT = MENU_SELECT;
        this.MENU_NO = MENU_NO;
        this.MENU_NAME = MENU_NAME;
        this.MENU_RESET_COUNT = MENU_RESET_COUNT;
        this.SELECT_YEAR = SELECT_YEAR;
        this.SELECT_MONTH = SELECT_MONTH;
        this.SELECT_DAY = SELECT_DAY;
        this.LAST_START_TIME = LAST_START_TIME;
    }

    public String getMENU_SELECT() {
        return MENU_SELECT;
    }

    public void setMENU_SELECT(String MENU_SELECT) {
        this.MENU_SELECT = MENU_SELECT;
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

    public int getMENU_RESET_COUNT() {
        return MENU_RESET_COUNT;
    }

    public void setMENU_RESET_COUNT(int MENU_RESET_COUNT) {
        this.MENU_RESET_COUNT = MENU_RESET_COUNT;
    }

    public String getSELECT_YEAR() {
        return SELECT_YEAR;
    }

    public void setSELECT_YEAR(String SELECT_YEAR) {
        this.SELECT_YEAR = SELECT_YEAR;
    }

    public String getSELECT_MONTH() {
        return SELECT_MONTH;
    }

    public void setSELECT_MONTH(String SELECT_MONTH) {
        this.SELECT_MONTH = SELECT_MONTH;
    }

    public String getSELECT_DAY() {
        return SELECT_DAY;
    }

    public void setSELECT_DAY(String SELECT_DAY) {
        this.SELECT_DAY = SELECT_DAY;
    }

    public String getLAST_START_TIME() {
        return LAST_START_TIME;
    }

    public void setLAST_START_TIME(String LAST_START_TIME) {
        this.LAST_START_TIME = LAST_START_TIME;
    }
}
