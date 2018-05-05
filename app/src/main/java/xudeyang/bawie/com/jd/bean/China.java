package xudeyang.bawie.com.jd.bean;/*
 * @创建者     choe
 * @创建时间   2016/4/18 22:46
 * @描述	      ${TODO}
 *
 * @更新者     $Author$
 * @更新时间   2016/4/18
 * @更新描述   ${TODO}
 */

import java.util.ArrayList;

public class China {
    public ArrayList<Province> citylist;

   public class Province {
        public ArrayList<Area> c	;
        public String p;

       public  class Area{
           public ArrayList<Street> a;
            public String n;
           public class Street{
               public String s;
           }
        }
    }
}
