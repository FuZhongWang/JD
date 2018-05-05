package xudeyang.bawie.com.jd.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mac on 2018/4/21.
 */
@Entity
public class ListCartsBean {
    private float bargainPrice;
    private String createtime;
    private String detailUrl;
    private String images;
    private int num;
    private int pid;
    private float price;
    private int pscid;
    private int selected;
    @Id
    private Long sellerid;
    private String subhead;
    private String title;
    private boolean cisChecked;
    @Generated(hash = 538358138)
    public ListCartsBean(float bargainPrice, String createtime, String detailUrl,
            String images, int num, int pid, float price, int pscid, int selected,
            Long sellerid, String subhead, String title, boolean cisChecked) {
        this.bargainPrice = bargainPrice;
        this.createtime = createtime;
        this.detailUrl = detailUrl;
        this.images = images;
        this.num = num;
        this.pid = pid;
        this.price = price;
        this.pscid = pscid;
        this.selected = selected;
        this.sellerid = sellerid;
        this.subhead = subhead;
        this.title = title;
        this.cisChecked = cisChecked;
    }
    @Generated(hash = 1103050362)
    public ListCartsBean() {
    }
    public float getBargainPrice() {
        return this.bargainPrice;
    }
    public void setBargainPrice(float bargainPrice) {
        this.bargainPrice = bargainPrice;
    }
    public String getCreatetime() {
        return this.createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getDetailUrl() {
        return this.detailUrl;
    }
    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }
    public String getImages() {
        return this.images;
    }
    public void setImages(String images) {
        this.images = images;
    }
    public int getNum() {
        return this.num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public int getPid() {
        return this.pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public int getPscid() {
        return this.pscid;
    }
    public void setPscid(int pscid) {
        this.pscid = pscid;
    }
    public int getSelected() {
        return this.selected;
    }
    public void setSelected(int selected) {
        this.selected = selected;
    }
    public Long getSellerid() {
        return this.sellerid;
    }
    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }
    public String getSubhead() {
        return this.subhead;
    }
    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean getCisChecked() {
        return this.cisChecked;
    }
    public void setCisChecked(boolean cisChecked) {
        this.cisChecked = cisChecked;
    }

   
}
