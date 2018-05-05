package xudeyang.bawie.com.jd.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mac on 2018/4/12.
 */
@Entity
public class Daobean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    @Generated(hash = 2048859901)
    public Daobean(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1831228405)
    public Daobean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
