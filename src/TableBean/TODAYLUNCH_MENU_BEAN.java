package TableBean;

public class TODAYLUNCH_MENU_BEAN {
    private String MENU_NAME;
    private String MENU_STORENAME;
    private String MENU_CATE;
    private String MENU_ADDRESS;
    private int MENU_SELECT_COUNT;
    private String MENU_INTRODUCTION;
    private String MENU_NO;
    private int MENU_CANSLE_COUNT;
    private String MENU_UPDATE_DAY;

    public TODAYLUNCH_MENU_BEAN() {
    }

    public TODAYLUNCH_MENU_BEAN(String MENU_NAME, String MENU_STORENAME, String MENU_CATE, String MENU_ADDRESS, int MENU_SELECT_COUNT, String MENU_INTRODUCTION, String MENU_NO, int MENU_CANSLE_COUNT) {
        this.MENU_NAME = MENU_NAME;
        this.MENU_STORENAME = MENU_STORENAME;
        this.MENU_CATE = MENU_CATE;
        this.MENU_ADDRESS = MENU_ADDRESS;
        this.MENU_SELECT_COUNT = MENU_SELECT_COUNT;
        this.MENU_INTRODUCTION = MENU_INTRODUCTION;
        this.MENU_NO = MENU_NO;
        this.MENU_CANSLE_COUNT = MENU_CANSLE_COUNT;
    }

    public String getMENU_NAME() {
        return MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getMENU_STORENAME() {
        return MENU_STORENAME;
    }

    public void setMENU_STORENAME(String MENU_STORENAME) {
        this.MENU_STORENAME = MENU_STORENAME;
    }

    public String getMENU_CATE() {
        return MENU_CATE;
    }

    public void setMENU_CATE(String MENU_CATE) {
        this.MENU_CATE = MENU_CATE;
    }

    public String getMENU_ADDRESS() {
        return MENU_ADDRESS;
    }

    public void setMENU_ADDRESS(String MENU_ADDRESS) {
        this.MENU_ADDRESS = MENU_ADDRESS;
    }

    public int getMENU_SELECT_COUNT() {
        return MENU_SELECT_COUNT;
    }

    public void setMENU_SELECT_COUNT(int MENU_SELECT_COUNT) {
        this.MENU_SELECT_COUNT = MENU_SELECT_COUNT;
    }

    public String getMENU_INTRODUCTION() {
        return MENU_INTRODUCTION;
    }

    public void setMENU_INTRODUCTION(String MENU_INTRODUCTION) {
        this.MENU_INTRODUCTION = MENU_INTRODUCTION;
    }

    public String getMENU_NO() {
        return MENU_NO;
    }

    public void setMENU_NO(String MENU_NO) {
        this.MENU_NO = MENU_NO;
    }

    public int getMENU_CANSLE_COUNT() {
        return MENU_CANSLE_COUNT;
    }

    public void setMENU_CANSLE_COUNT(int MENU_CANSLE_COUNT) {
        this.MENU_CANSLE_COUNT = MENU_CANSLE_COUNT;
    }
    
    public String getMENU_UPDATE_DAY() {
        return MENU_UPDATE_DAY;
    }

    public void setMENU_UPDATE_DAY(String MENU_UPDATE_DAY) {
        this.MENU_UPDATE_DAY = MENU_UPDATE_DAY;
    }
    
}
