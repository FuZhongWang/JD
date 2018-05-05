package xudeyang.bawie.com.jd.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Mac on 2018/4/21.
 */
@Entity
public class CartsBean {

    /**
     * list : [{"bargainPrice":1599,"createtime":"2017-10-14T21:48:08","detailUrl":"https://item.m.jd.com/product/1993026402.html?utm#_source=androidapp&utm#_medium=appshare&utm#_campaign=t#_335139774&utm#_term=QQfriends","images":"https://m.360buyimg.com/n0/jfs/t5863/302/8961270302/97126/41feade1/5981c81cNc1b1fbef.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t7003/250/1488538438/195825/53bf31ba/5981c57eN51e95176.jpg!q70.jpg|https://m.360buyimg.com/n0/jfs/t5665/100/8954482513/43454/418611a9/5981c57eNd5fc97ba.jpg!q70.jpg","num":1,"pid":47,"price":111,"pscid":39,"selected":1,"sellerid":3,"subhead":"碳黑色 32GB 全网通 官方标配   1件","title":"锤子 坚果Pro 特别版 巧克力色 酒红色 全网通 移动联通电信4G手机 双卡双待 碳黑色 32GB 全网通"}]
     * sellerName : 商家3
     * sellerid : 3
     */
    private String sellerName;
    @Id
    private Long sellerid;
    @Transient
    private List<ListCartsBean> list;
    private boolean isChecked;

    @Generated(hash = 1470167716)
    public CartsBean(String sellerName, Long sellerid, boolean isChecked) {
        this.sellerName = sellerName;
        this.sellerid = sellerid;
        this.isChecked = isChecked;
    }

    @Generated(hash = 1395963270)
    public CartsBean() {
    }

    public List<ListCartsBean> getList() {
        return list;
    }

    public void setList(List<ListCartsBean> list) {
        this.list = list;
    }

    public String getSellerName() {
        return this.sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Long getSellerid() {
        return this.sellerid;
    }

    public void setSellerid(Long sellerid) {
        this.sellerid = sellerid;
    }

    public boolean getIsChecked() {
        return this.isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
