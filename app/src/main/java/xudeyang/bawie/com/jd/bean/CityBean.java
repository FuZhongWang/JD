package xudeyang.bawie.com.jd.bean;

import java.util.List;

/**
 * Created by Mac on 2018/4/27.
 */

public class CityBean {
    private String Cityname;
    private List<shibean> list;

    public String getCityname() {
        return Cityname;
    }

    public void setCityname(String cityname) {
        Cityname = cityname;
    }

    public List<shibean> getList() {
        return list;
    }

    public void setList(List<shibean> list) {
        this.list = list;
    }

    public static class shibean{
        private String shiname;
        private List<qubean> qulist;

        public String getShiname() {
            return shiname;
        }

        public void setShiname(String shiname) {
            this.shiname = shiname;
        }

        public List<qubean> getQulist() {
            return qulist;
        }

        public void setQulist(List<qubean> qulist) {
            this.qulist = qulist;
        }
        public static class qubean{
            String quname;

            public String getQuname() {
                return quname;
            }

            public void setQuname(String quname) {
                this.quname = quname;
            }
        }
    }
}
