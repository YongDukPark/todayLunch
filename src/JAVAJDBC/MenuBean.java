package JAVAJDBC;

public class MenuBean {
    private String MENU_NAME;
    private String MENU_STORENAME;
    private String MENU_CATE;
    private String MENU_ADDRESS;
    private int MENU_SELECT_COUNT;
    private String MENU_INTRODUCTION;
    
    public MenuBean(){
        
    };
    
    public MenuBean(String MENU_NAME, String MENU_STORENAME, String MENU_CATE, String MENU_ADDRESS, int MENU_SELECT_COUNT, String MENU_INTRODUCTION) {
        this.MENU_NAME = MENU_NAME;
        this.MENU_STORENAME = MENU_STORENAME;
        this.MENU_CATE = MENU_CATE;
        this.MENU_ADDRESS = MENU_ADDRESS;
        this.MENU_SELECT_COUNT = MENU_SELECT_COUNT;
        this.MENU_INTRODUCTION = MENU_INTRODUCTION;
    }

    
    
    public String getMENU_NAME() {
        return this.MENU_NAME;
    }

    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME;
    }

    public String getMENU_STORENAME() {
        return this.MENU_STORENAME;
    }

    public void setMENU_STORENAME(String MENU_STORENAME) {
        this.MENU_STORENAME = MENU_STORENAME;
    }

    public String getMENU_CATE() {
        return this.MENU_CATE;
    }

    public void setMENU_CATE(String MENU_CATE) {
        this.MENU_CATE = MENU_CATE;
    }

    public String getMENU_ADDRESS() {
        return this.MENU_ADDRESS;
    }

    public void setMENU_ADDRESS(String MENU_ADDRESS) {
        this.MENU_ADDRESS = MENU_ADDRESS;
    }

    public int getMENU_SELECT_COUNT() {
        return this.MENU_SELECT_COUNT;
    }

    public void setMENU_SELECT_COUNT(int MENU_SELECT_COUNT) {
        this.MENU_SELECT_COUNT = MENU_SELECT_COUNT;
    }

    public String getMENU_INTRODUCTION() {
        return this.MENU_INTRODUCTION;
    }

    public void setMENU_INTRODUCTION(String MENU_INTRODUCTION) {
        this.MENU_INTRODUCTION = MENU_INTRODUCTION;
    }


    
}
