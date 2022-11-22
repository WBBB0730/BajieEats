package cn.edu.szu.Bajie.util;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Whitence
 * @description: TODO
 * @date 2022/10/27 20:14
 */

@Slf4j
public class DistanceUtil {

    /**
     * 地球半径,单位 km
     */
    private static final double EARTH_RADIUS = 6378137;

    /**
     * 根据经纬度，计算两点间的距离
     *
     * @param longitude1 第一个点的经度
     * @param latitude1  第一个点的纬度
     * @param longitude2 第二个点的经度
     * @param latitude2  第二个点的纬度
     * @return 返回距离 单位米
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 纬度
        double lat1 = Math.toRadians(latitude1);
        double lat2 = Math.toRadians(latitude2);
        // 经度
        double lng1 = Math.toRadians(longitude1);
        double lng2 = Math.toRadians(longitude2);
        // 纬度之差
        double a = lat1 - lat2;
        // 经度之差
        double b = lng1 - lng2;
        // 计算两点距离的公式
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));
        // 弧长乘地球半径, 返回单位: 米
        s =  s * EARTH_RADIUS;
//        return Math.round(s * 10000) / 10000;
        return Math.round(s * 10000) / 10000;
    }

    public static void main(String[] args) {

        double a = 113.001;
        double b = 23.001;

        double x = 113.002;
        double y = 23.002;

        System.out.println(getDistance(a,b,x,y));
    }

}


