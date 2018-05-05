package xudeyang.bawie.com.jd.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mac on 2018/4/13.
 */
@Entity
public class Frag2LeftBean {
    /**
     * cid : 1
     * createtime : 2017-10-10T19:41:39
     * icon : http://120.27.23.105/images/category/shop.png
     * ishome : 1
     * name : 京东超市
     */
@Id
    private Long cid;
    private String createtime;
    private String icon;
    private int ishome;
    private String name;

    @Generated(hash = 877454574)
    public Frag2LeftBean(Long cid, String createtime, String icon, int ishome,
            String name) {
        this.cid = cid;
        this.createtime = createtime;
        this.icon = icon;
        this.ishome = ishome;
        this.name = name;
    }

    @Generated(hash = 926674311)
    public Frag2LeftBean() {
    }

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getIshome() {
        return ishome;
    }

    public void setIshome(int ishome) {
        this.ishome = ishome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
