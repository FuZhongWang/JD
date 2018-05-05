package xudeyang.bawie.com.jd.helper;

/**
 * Created by Mac on 2018/4/20.
 */

public class EventRegister {
    private String name;
    private String icon;

    public String getName() {
        return name;
    }
    public String getIcon() {
        return icon;
    }

    public void setName(String name) {
        this.name = name;
    }
    public EventRegister(String name,String icon){
        this.name = name;
        this.icon=icon;
    }
}
