package com.zdnst.common.utils;

import java.util.Vector;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 
 * <p>描        述：汉字转拼音辅助类</p>
 * <p>项目名称:maps1.0</p>
 * <p>所在位置:com.zdnst.common.utilsCnToPy.java</p>
 * <p>类名:CnToPy.java</p>
 * <p>创  建  人：kui.he</p>
 * <p>创建时间：2014年8月15日上午11:26:39</p>
 */
public class CnToPy {
	public static final int lengthOfStartFullPy = 2;

	/**
	 * 
	 * @describe：获取指定字符串指定长度转换拼音后的返回
	 * @param chineseChar
	 * @param keyLength
	 * @return
	 * @author:kui.he
	 * @time:2014年8月15日上午11:27:29
	 */
	private static Vector[] getPinyin(char chineseChar, int keyLength) {
		Vector[] rs = new Vector[2];
		String[] pys = PinyinHelper.toHanyuPinyinStringArray(chineseChar);
		if ((pys != null) && (pys.length > 0)) {
			Vector firstPy = new Vector();
			Vector fullPy = new Vector();
			for (int i = 0; i < pys.length; ++i) {
				String py = pys[i];

				String p = py.substring(0, 1).toUpperCase();
				if (!firstPy.contains(p)) {
					firstPy.addElement(p);
				}

				if (keyLength >= 2) {
					p = py.substring(0, py.length() - 1).toUpperCase();
					if (!fullPy.contains(p)) {
						fullPy.addElement(p);
					}
				}
			}
			rs[0] = firstPy;
			rs[1] = fullPy;
		}
		return rs;
	}

	
	public static Vector getMultiPinyin(String strText) {
		Vector rs = new Vector();
		if ((strText == null) || (strText.length() == 0))
			return rs;
		int keyLength = strText.length();
		Vector fullRs = new Vector();
		Vector[] pyv = (Vector[]) null;
		for (int i = 0; i < strText.length(); ++i) {
			char vChar = strText.charAt(i);
			pyv = getPinyin(vChar, keyLength);

			if (pyv[0] == null) {
				if (rs.size() == 0)
					rs.addElement(String.valueOf(vChar));
				else {
					for (int j = 0; j < rs.size(); ++j) {
						String py = (String) rs.elementAt(j);
						rs.setElementAt(py + vChar, j);
					}
				}
			} else if (pyv[0].size() == 1) {
				if (rs.size() == 0)
					rs.addElement(pyv[0].elementAt(0));
				else {
					for (int j = 0; j < rs.size(); ++j) {
						String py = (String) rs.elementAt(j);
						rs.setElementAt(py + pyv[0].elementAt(0), j);
					}
				}
			} else if (rs.size() == 0) {
				rs.addAll(pyv[0]);
			} else {
				int count = rs.size();
				Vector temp = new Vector(rs);
				for (int j = 0; j < pyv[0].size() - 1; ++j) {
					rs.addAll(temp);
				}
				int index = 0;
				for (int m = 0; m < pyv[0].size(); ++m) {
					for (int j = index; j < index + count; ++j) {
						String py = (String) rs.elementAt(j);
						rs.setElementAt(py + pyv[0].elementAt(m), j);
					}

					index += count;
				}

			}

			if (keyLength >= 2) {
				if (pyv[1] == null) {
					if (fullRs.size() == 0)
						fullRs.addElement(String.valueOf(vChar));
					else {
						for (int j = 0; j < fullRs.size(); ++j) {
							String py = (String) fullRs.elementAt(j);
							fullRs.setElementAt(py + vChar, j);
						}
					}
				} else if (pyv[1].size() == 1) {
					if (fullRs.size() == 0)
						fullRs.addElement(pyv[1].elementAt(0));
					else {
						for (int j = 0; j < fullRs.size(); ++j) {
							String py = (String) fullRs.elementAt(j);
							fullRs.setElementAt(py + pyv[1].elementAt(0), j);
						}
					}
				} else if (fullRs.size() == 0) {
					fullRs.addAll(pyv[1]);
				} else {
					int count = fullRs.size();
					Vector temp = new Vector(fullRs);
					for (int j = 0; j < pyv[1].size() - 1; ++j) {
						fullRs.addAll(temp);
					}
					int index = 0;
					for (int m = 0; m < pyv[1].size(); ++m) {
						for (int j = index; j < index + count; ++j) {
							String py = (String) fullRs.elementAt(j);
							fullRs.setElementAt(py + pyv[1].elementAt(m), j);
						}

						index += count;
					}
				}
			}

		}

		rs.addAll(fullRs);
		return rs;
	}

	public static String getPingYin(String src) {
		char[] t1 = (char[]) null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; ++i) {
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 = t4 + t2[0];
				} else {
					t4 = t4 + Character.toString(t1[i]);
				}
			}
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	public static String getPinYinHeadChar2(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); ++j) {
			char word = str.charAt(j);
			String py = getPingYin(String.valueOf(word));
			if (!py.equals("")) {
				convert = convert + py.charAt(0);
			}
		}
		return convert;
	}

	/**
	 * 只获取拼音首字母的第一个大写字母,多音字忽略也只取第一个
	 * @describe：TODO
	 * @param str
	 * @return
	 * @author:kui.he
	 * @time:2014年8月15日上午11:17:10
	 */
	public static String getFirstHeadChar(String str) {
		String s=getPinYinHeadChar(str);
		if(s.length()>1){
			return s.substring(1,2);
		}return s;
	}
    
	/**
	 * 获取指定汉字的首字母,如果有多音返回每个多音的第一个首字母。例如:熏[xun1,xun4]返回XX
	 * @describe：TODO
	 * @param str
	 * @return
	 * @author:kui.he
	 * @time:2014年8月15日上午11:18:08
	 */
	public static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); ++j) {
			char word = str.charAt(j);
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				for (int i = 0; i < pinyinArray.length; ++i) {
					convert = convert + pinyinArray[i].charAt(0);
				}
			} else {
				convert = convert + word;
			}
		}
		return convert.toUpperCase();
	}

	public static String getCnASCII(String cnStr) {
		StringBuffer strBuf = new StringBuffer();
		byte[] bGBK = cnStr.getBytes();
		for (int i = 0; i < bGBK.length; ++i) {
			strBuf.append(Integer.toHexString(bGBK[i] & 0xFF));
		}
		return strBuf.toString();
	}

	public static void main(String[] args) {
	}
}