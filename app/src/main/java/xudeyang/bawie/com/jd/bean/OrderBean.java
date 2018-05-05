package xudeyang.bawie.com.jd.bean;

/**
 * Created by Mac on 2018/4/26.
 */

public class OrderBean {

    /**
     * createtime : 2018-04-26T09:35:32
     * orderid : 10419
     * price : 118
     * status : 0
     * title : 订单测试标题12652
     * uid : 12652
     */

    private String createtime;
    private int orderid;
    private int price;
    private int status;
    private String title;
    private int uid;

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
