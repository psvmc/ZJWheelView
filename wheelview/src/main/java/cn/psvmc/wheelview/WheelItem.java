package cn.psvmc.wheelview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PSVMC on 16/4/27.
 */
public class WheelItem {
    public String value;
    public String text;
    public WheelItem(String value,String text){
        this.value = value;
        this.text = text;
    }

    /**
     * 根据数组获取List
     * @param arr
     * @return
     */
    public static List<WheelItem> listFromStringArray(String[] arr){
        List<WheelItem> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(new WheelItem(arr[i],arr[i]));
        }
        return list;
    }

    /**
     * 根据数字获取List
     * @param minNum 最小值
     * @param maxNum 最大值
     * @return
     */
    public static List<WheelItem> listFromNum(int minNum,int maxNum){
        List<WheelItem> list = new ArrayList<>();
        for (int i = minNum; i <= maxNum; i++) {
            list.add(new WheelItem(""+i,""+i));
        }
        return list;
    }

}
