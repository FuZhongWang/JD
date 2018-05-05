package xudeyang.bawie.com.jd.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mac on 2018/4/19.
 */
@Entity
public class ListBean {
    private String icon;
    private String name;
    private int pcid;
    @Id
    private Long pscid;

    @Generated(hash = 136404071)
    public ListBean(String icon, String name, int pcid, Long pscid) {
        this.icon = icon;
        this.name = name;
        this.pcid = pcid;
        this.pscid = pscid;
    }

    @Generated(hash = 777734033)
    public ListBean() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPcid() {
        return pcid;
    }

    public void setPcid(int pcid) {
        this.pcid = pcid;
    }

    public Long getPscid() {
        return pscid;
    }

    public void setPscid(Long pscid) {
        this.pscid = pscid;
    }
}
