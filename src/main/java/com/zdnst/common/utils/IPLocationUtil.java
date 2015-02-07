package com.zdnst.common.utils;



/**
 * 
 * @author zhongyq
 * @date 2013-04-12
 */
public class IPLocationUtil {
	/**
	 * ip地址转成整数.
	 * 
	 * @param ip
	 * @return
	 */
	public static long ip2long(String ip) {
		if (CommonUtils.isEmpty(ip)) return 0;
		String[] ips = ip.split("[.]");
		long num = 16777216L * Long.parseLong(ips[0]) + 65536L
				* Long.parseLong(ips[1]) + 256 * Long.parseLong(ips[2])
				+ Long.parseLong(ips[3]);
		return num;
	}

	/**
	 * 整数转成ip地址.
	 * 
	 * @param ipLong
	 * @return
	 */
	public static String long2ip(long ipLong) {
		long mask[] = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
		long num = 0;
		StringBuffer ipInfo = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			num = (ipLong & mask[i]) >> (i * 8);
			if (i > 0)
				ipInfo.insert(0, ".");
			ipInfo.insert(0, Long.toString(num, 10));
		}
		return ipInfo.toString();
	}

	private final static double PI = 3.14159265354;
	private final static double D2R = 0.017453;
	private final static double a2 = 6378137.0;
	private final static double e2 = 0.006739496742337;

	public static double DistanceByMeter(FCDPoint pt1, FCDPoint pt2) {
		if (pt1.lng == pt2.lng && pt1.lat == pt2.lat) {
			return 0.0;
		} else {
			double fdLambda = (pt1.lng - pt2.lng) * D2R;
			double fdPhi = (pt1.lat - pt2.lat) * D2R;
			double fPhimean = ((pt1.lat + pt2.lat) / 2.0) * D2R;
			double fTemp = 1 - e2 * (Math.pow(Math.sin(fPhimean), 2));
			double fRho = (a2 * (1 - e2)) / Math.pow(fTemp, 1.5);
			double fNu = a2
					/ (Math.sqrt(1 - e2
							* (Math.sin(fPhimean) * Math.sin(fPhimean))));
			double fz = Math.sqrt(Math.pow(Math.sin(fdPhi / 2.0), 2)
					+ Math.cos(pt2.lat * D2R) * Math.cos(pt1.lat * D2R)
					* Math.pow(Math.sin(fdLambda / 2.0), 2));
			fz = 2 * Math.asin(fz);
			double fAlpha = Math.cos(pt2.lat * D2R) * Math.sin(fdLambda) * 1
					/ Math.sin(fz);
			fAlpha = Math.asin(fAlpha);
			double fR = (fRho * fNu)
					/ ((fRho * Math.pow(Math.sin(fAlpha), 2)) + (fNu * Math
							.pow(Math.cos(fAlpha), 2)));
			return fz * fR;
		}
	}
	
	public static double DistanceByKiloMeter(FCDPoint pt1, FCDPoint pt2) {
		return DistanceByMeter(pt1,pt2)/1000;
	}
	/**
	 * 获取最终的经纬度对象
	 * 如果有城市的经纬度，则用城市的经纬度构造对象
	 * 如果没有城市的经纬度，有地区的经纬度，则用地区的经纬度构造对象
	 * 如果没有地区的经纬度，有国家的经纬度，则用国家的经纬度构造对象
	 * 如果连国家的经纬度都没有，则返回null
	 * @param countryLng
	 * @param countryLat
	 * @param regionLng
	 * @param regionLat
	 * @param cityLng
	 * @param cityLat
	 * @return
	 */
	public static FCDPoint createByAll(String countryLng, String countryLat,
			String regionLng, String regionLat, String cityLng, String cityLat) {
		if (CommonUtils.isEmpty(cityLng)) {
			if (CommonUtils.isEmpty(regionLng)) {
				if (CommonUtils.isEmpty(countryLng)) {
					return null;
				} else {
					return new FCDPoint(Double.parseDouble(countryLng), Double
							.parseDouble(countryLat));
				}
			} else {
				return new FCDPoint(Double.parseDouble(regionLng), Double
						.parseDouble(regionLat));
			}
		} else {
			return new FCDPoint(Double.parseDouble(cityLng), Double
					.parseDouble(cityLat));
		}
	}

	public static class FCDPoint{
		//经度
		private double lng;
		//纬度
		private double lat;
		
		public FCDPoint(double lng,double lat){
			this.lng = lng;
			this.lat = lat;
		}
	}
	public static void main(String[] args) {
//		FCDPoint pt1 = new FCDPoint(116.24,39.55);
//		FCDPoint pt2 = new FCDPoint(116.24,39.55);
		//FCDPoint pt2 = new FCDPoint(121.29,31.14);
//		System.out.println(NumberUtil.doubleFormat(DistanceByKiloMeter(pt1,pt2),NumberUtil.FORMAT1));
//		String ip = "24.192.0.0";
//		System.out.println(ip2long(ip));
//		long i = 2147614718L;
//		System.out.println(long2ip(i));
//		
//		byte[] bs = ip.getBytes();
//		for(byte b:bs){
//			System.out.println(b);
//		}
		
	}
}
   