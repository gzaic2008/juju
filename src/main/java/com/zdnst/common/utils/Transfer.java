package com.zdnst.common.utils;


import java.util.HashMap;
import java.util.Stack;

/**
 * 数字转汉字工具类
 * <p>描        述：</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.common.utilsTransfer.java</p>
 * <p>类名:Transfer.java</p>
 * <p>创  建  人：kui.he</p>
 * <p>创建时间：2014年6月18日下午3:51:08</p>
 */
public class Transfer {

	private static Stack<Integer> transfer(int n) {
		Stack<Integer> st = new Stack<Integer>();
		int division = 0; // 余数
		while (n >= 10) {
			division = n % 10;
			st.push(division);
			n = n / 10;
		}

		st.push(n); // 将最高位压栈

		return st;
	}
	
	public static String converInt(int n){
		Stack<Integer> s = transfer(n);		/*
		 * while(!s.empty()){ System.out.print(s.pop()); //测试语句 }
		 */
		HashMap<Integer, String> hp1 = new HashMap<Integer, String>(); // 第一个映射表
		hp1.put(0, "零"); // 根据所在位的数值与中文对应
		hp1.put(1, "一");
		hp1.put(2, "二");
		hp1.put(3, "三");
		hp1.put(4, "四");
		hp1.put(5, "五");
		hp1.put(6, "六");
		hp1.put(7, "七");
		hp1.put(8, "八");
		hp1.put(9, "九");

		HashMap<Integer, String> hp2 = new HashMap<Integer, String>(); // 第二个映射表
		hp2.put(2, "十"); // 根据所在位数，与中文对应
		hp2.put(3, "百");
		hp2.put(4, "千");
		hp2.put(5, "万");
		hp2.put(6, "十万");
		hp2.put(7, "百万");
		hp2.put(8, "千万");
		hp2.put(9, "亿");

		// System.out.println(s.size());
		String out = "";
		while (!s.isEmpty()) {
			int temp = s.pop();

			if (s.size() == 0) {
				if (temp != 0) {
					out = out + hp1.get(temp);
				}
			} else {
				if (temp == 0) {
					out = out + hp1.get(temp);
				} else {
					out = out + hp1.get(temp) + hp2.get(s.size() + 1);
				}
			}
		}
		//System.out.println(out);
		return out;
	}

	public static void main(String[] args) {
		//System.out.println(converInt(22));
	}

}