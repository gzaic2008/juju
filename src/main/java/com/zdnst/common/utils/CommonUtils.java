package com.zdnst.common.utils;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.BreakIterator;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;



public class CommonUtils {
	
	private static Logger logger = Logger.getLogger(CommonUtils.class);

	// Constants used by escapeHTMLTags
	private static final char[] QUOTE_ENCODE = "&quot;".toCharArray();
	private static final char[] AMP_ENCODE = "&amp;".toCharArray();
	private static final char[] LT_ENCODE = "&lt;".toCharArray();
	private static final char[] GT_ENCODE = "&gt;".toCharArray();
	private static final char[] APOS_ENCODE = "&apos;".toCharArray();
	private static final char[] BR_TAG = "<BR>".toCharArray();

	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	private static String imgType = "bmp,jpg,png,jpeg,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw";
	
	private static String mediaType = "3gp,aac,mp3,acc,mp4,aif,rm,wmv";

	private static String pingYin = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";
	public static Map<String, String> pinYinMap = new HashMap<String, String>();

	static {
		String[] pinYins = pingYin.split(",");
		for (int i = 0; i < pinYins.length; i++) {
			pinYinMap.put(pinYins[i], pinYins[i]);
		}

	}

	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/**
	 * 字符串替换 another string.
	 * 
	 * @param inString
	 *            要处理的字符串
	 * @param oldPattern
	 *            原替换符
	 * @param newPattern
	 *            新替换符
	 * @return a String 处理后的字符串
	 */
	public static String replace(String inString, String oldPattern,
			String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // Our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * 
	 * @param line
	 *            String
	 * @param oldString
	 *            String
	 * @param newString
	 *            String
	 * @return String
	 */
	public static final String replaceIgnoreCase(String line, String oldString,
			String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * 
	 * @param in
	 *            String
	 * @return String
	 */
	public static final String escapeHTMLTags(String in) {
		if (in == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = in.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '>') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(GT_ENCODE);
			}
		}
		if (last == 0) {
			return in;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	public synchronized static final String hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {

			}
		}
		// Now, compute hash.
		try {
			digest.update(data.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {

		}
		return encodeHex(digest.digest());
	}

	public static final String encodeHex(byte[] bytes) {
		StringBuffer buf = new StringBuffer(bytes.length * 2);
		int i;

		for (i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * 
	 * @param hex
	 *            String
	 * @return byte[]
	 */
	public static final byte[] decodeHex(String hex) {
		char[] chars = hex.toCharArray();
		byte[] bytes = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			int newByte = 0x00;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = (byte) newByte;
			byteCount++;
		}
		return bytes;
	}

	private static final byte hexCharToByte(char ch) {
		switch (ch) {
		case '0':
			return 0x00;
		case '1':
			return 0x01;
		case '2':
			return 0x02;
		case '3':
			return 0x03;
		case '4':
			return 0x04;
		case '5':
			return 0x05;
		case '6':
			return 0x06;
		case '7':
			return 0x07;
		case '8':
			return 0x08;
		case '9':
			return 0x09;
		case 'a':
			return 0x0A;
		case 'b':
			return 0x0B;
		case 'c':
			return 0x0C;
		case 'd':
			return 0x0D;
		case 'e':
			return 0x0E;
		case 'f':
			return 0x0F;
		}
		return 0x00;
	}

	private static final int fillchar = '=';
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz" + "0123456789+/";

	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0) {
			return new String[0];
		}

		ArrayList wordList = new ArrayList();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);
		int start = 0;

		for (int end = boundary.next(); end != BreakIterator.DONE; start = end, end = boundary
				.next()) {
			String tmp = text.substring(start, end).trim();
			// Remove characters that are not needed.
			tmp = replace(tmp, "+", "");
			tmp = replace(tmp, "/", "");
			tmp = replace(tmp, "\\", "");
			tmp = replace(tmp, "#", "");
			tmp = replace(tmp, "*", "");
			tmp = replace(tmp, ")", "");
			tmp = replace(tmp, "(", "");
			tmp = replace(tmp, "&", "");
			if (tmp.length() > 0) {
				wordList.add(tmp);
			}
		}
		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	private static Random randGen = new Random();

	private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
			+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * 
	 * @param string
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static final String chopAtWord(String string, int length) {
		if (string == null || string.length() == 0) {
			return string;
		}

		char[] charArray = string.toCharArray();
		int sLength = string.length();
		if (length < sLength) {
			sLength = length;
		}

		// First check if there is a newline character before length; if so,
		// chop word there.
		for (int i = 0; i < sLength - 1; i++) {
			// Windows
			if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
				return string.substring(0, i + 1);
			}
			// Unix
			else if (charArray[i] == '\n') {
				return string.substring(0, i);
			}
		}
		// Also check boundary case of Unix newline
		if (charArray[sLength - 1] == '\n') {
			return string.substring(0, sLength - 1);
		}

		// Done checking for newline, now see if the total string is less than
		// the specified chop point.
		if (string.length() < length) {
			return string;
		}

		// No newline, so chop at the first whitespace.
		for (int i = length - 1; i > 0; i--) {
			if (charArray[i] == ' ') {
				return string.substring(0, i).trim();
			}
		}

		// Did not find word boundary so return original String chopped at
		// specified length.
		return string.substring(0, length);
	}

	public static final String escapeForSQL(String string) {
		if (string == null) {
			return null;
		} else if (string.length() == 0) {
			return string;
		}

		char ch;
		char[] input = string.toCharArray();
		int i = 0;
		int last = 0;
		int len = input.length;
		StringBuffer out = null;
		for (; i < len; i++) {
			ch = input[i];

			if (ch == '\'') {
				if (out == null) {
					out = new StringBuffer(len + 2);
				}
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append('\'').append('\'');
			}
		}

		if (out == null) {
			return string;
		} else if (i > last) {
			out.append(input, last, i - last);
		}

		return out.toString();
	}

	public static final String escapeForXML(String string) {
		if (string == null) {
			return null;
		}
		char ch;
		int i = 0;
		int last = 0;
		char[] input = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) (len * 1.3));
		for (; i < len; i++) {
			ch = input[i];
			if (ch > '>') {
				continue;
			} else if (ch == '<') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(LT_ENCODE);
			} else if (ch == '&') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(AMP_ENCODE);
			} else if (ch == '"') {
				if (i > last) {
					out.append(input, last, i - last);
				}
				last = i + 1;
				out.append(QUOTE_ENCODE);
			}
		}
		if (last == 0) {
			return string;
		}
		if (i > last) {
			out.append(input, last, i - last);
		}
		return out.toString();
	}

	/**
	 * 
	 * @param string
	 *            String
	 * @return String
	 */
	public static final String unescapeFromXML(String string) {
		string = replace(string, "&lt;", "<");
		string = replace(string, "&gt;", ">");
		string = replace(string, "&quot;", "\"");
		return replace(string, "&amp;", "&");
	}

	private static final char[] zeroArray = "0000000000000000000000000000000000000000000000000000000000000000"
			.toCharArray();

	/**
	 * 
	 * @param string
	 *            String
	 * @param length
	 *            int
	 * @return String
	 */
	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		}
		StringBuffer buf = new StringBuffer(length);
		buf.append(zeroArray, 0, length - string.length()).append(string);
		return buf.toString();
	}

	/**
	 * Formats a Date as a fifteen character long String made up of the Date's
	 * padded millisecond value.
	 * 
	 * @return a Date encoded as a String.
	 */
	public static final String dateToMillis(Date date) {
		return zeroPadString(Long.toString(date.getTime()), 15);
	}

	/**
	 * get the length gb2312 str
	 * 
	 * @param src
	 *            String
	 * @return int
	 */
	public static final int strLength(String src) {
		if (src == null) {

			return 0;
		}

		char[] ca = src.toCharArray();
		int i = 0;
		for (int k = 0; k < ca.length; k++) {
			if (ca[k] > 256) {
				i = i + 2;
			} else {
				i = i + 1;
			}
		}
		return i;

	}

	public static String convertNewlines(String input) {
		char[] chars = input.toCharArray();
		int cur = 0;
		int len = chars.length;
		StringBuffer buf = new StringBuffer(len);
		// Loop through each character lookin for newlines.
		for (int i = 0; i < len; i++) {
			// If we've found a Unix newline, add BR tag.
			if (chars[i] == '\n') {
				buf.append(chars, cur, i - cur).append(BR_TAG);
				cur = i + 1;
			}
			// If we've found a Windows newline, add BR tag.
			else if (chars[i] == '\r' && i < len - 1 && chars[i + 1] == '\n') {
				buf.append(chars, cur, i - cur).append(BR_TAG);
				i++;
				cur = i + 1;
			}
		}
		// Add whatever chars are left to buffer.
		buf.append(chars, cur, len - cur);
		return buf.toString();
	}

	/**
	 * convert to gbk encode
	 * 
	 * @param src
	 *            String
	 * @return String
	 */
	public static final String convert(String src) {
		if (src == null) {
			return "";
		}
		String tar = "";
		try {
			tar = new String(src.getBytes("iso-8859-1"), "GBK");
			return tar;
		} catch (UnsupportedEncodingException ex) {
			return src;
		}
	}

	/**
	 * 将回车制换成空格
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String formatReturnKey2HtmlTag(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			if (c == '\n') {
				stringbuffer.append(" ");
			} else {
				stringbuffer.append(c);
			}
		}
		return new String(stringbuffer.toString());
	}

	/**
	 * 转化为HTML编码
	 * 
	 * @param s
	 *            String
	 * @return String
	 */
	public static String htmlEncode(String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int j = s.length();
		for (int i = 0; i < j; i++) {
			char c = s.charAt(i);
			switch (c) {
			case 60:
				stringbuffer.append("&lt;");
				break;
			case 62:
				stringbuffer.append("&gt;");
				break;
			case 38:
				stringbuffer.append("&amp;");
				break;
			case 34:
				stringbuffer.append("&quot;");
				break;

			case 169:
				stringbuffer.append("&copy;");
				break;
			case 174:
				stringbuffer.append("&reg;");
				break;
			case 165:
				stringbuffer.append("&yen;");
				break;
			case 8364:
				stringbuffer.append("&euro;");
				break;
			case 8482:
				stringbuffer.append("&#153;");
				break;
			case 13:
				if (i < j - 1 && s.charAt(i + 1) == 10) {
					stringbuffer.append("<br/>");
					i++;
				}
				break;
			case 32:
				stringbuffer.append("&nbsp");
				break;

			default:
				stringbuffer.append(c);
				break;
			}
		}
		return new String(stringbuffer.toString());
	}

	public static int getStrAllocateLen(String str) {

		int iLen = 0;
		char iUnicode;

		for (int i = 0; i < str.length(); i++) {
			iUnicode = str.charAt(i);
			if (iUnicode <= 128) { // 英文字符
				iLen += 1;
			} else {
				if (iUnicode > 128 && iUnicode <= 255) {
					iLen += 2;
				} else {
					if (iUnicode >= 4112) { // 中文字符
						iLen += 2;
					} else {
						iLen += 1; // 其他字符
					}
				}
			}
		}
		return (iLen);

		// return str.getBytes().length;
	}

	/**
	 * Convert a String to int.
	 * 
	 * @param intString
	 *            A String contains an int value.
	 * @return int The int value parsed from the string as parameter, 0 is
	 *         returned if cannot parse an int value from the given string.
	 */
	public static int toInt(String intString) {
		try {
			return Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public static int getInt(String intString){
		return toInt(intString);
	}
	/**
	 * Convert a String to Date.
	 * 
	 * @return Date The Date value parsed from the string as parameter.
	 * @param dateString
	 *            A String contains date information.
	 */
	public static Timestamp parseDate(String dateString) {
		SimpleDateFormat df;
		String[] formatList = {
		/*
		 * If any new format is needed, it can be added here. To sort according
		 * to priority is preferred.
		 */
		"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd hh:mm a", "yyyy-MM-dd", "hh:mm a" };
		Date d = null;
		if (dateString.equals("")) {
			return null;
		}
		for (int i = 0; i < formatList.length; i++) {
			df = new SimpleDateFormat(formatList[i]);
			try {
				d = df.parse(dateString);
				break;
			} catch (Exception e) {
			}
		}
		if (d != null) {
			return new Timestamp(d.getTime());
		} else {
			return null;
		}
	}

	/**
	 * Convert a Date to String with standard format.
	 * 
	 * @return The String which contains date information.
	 * @param date
	 *            date to be formatted
	 */
	public static String formatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * Replace the certain part in target string with a new string
	 * 
	 * @param oldStr
	 *            String
	 * @param searchStr
	 *            String
	 * @param replaceStr
	 *            String
	 * @return result String
	 */
	public static String stringReplace(String oldStr, String searchStr,
			String replaceStr) {
		String outStr = "";
		int iPos = 0;
		int iLen = searchStr.length();

		if (oldStr == null || oldStr == "" || searchStr == null
				|| replaceStr == null) {
			return oldStr;
		}

		iPos = oldStr.indexOf(searchStr);

		while (iPos != -1) {
			outStr += oldStr.substring(0, iPos) + replaceStr;
			iPos += iLen;
			if (oldStr.length() >= iPos) {
				oldStr = oldStr.substring(iPos);
				iPos = oldStr.indexOf(searchStr);
			}
		}
		outStr += oldStr;
		return outStr;
	}

	/**
	 * Localize the specified string
	 * 
	 * @param s
	 *            string to be localized
	 * @return result String
	 */
	public static String localString(String s) {
		String s2 = s;
		if (s == null) {
			return "";
		}
		try {
			s2 = new String(s.getBytes("iso-8859-1"), "GBK");
		} catch (Exception e) {

		} finally {
			return s2;
		}
	}

	/**
	 * Convert string to iso string
	 * 
	 * @param s
	 *            string to be globalized
	 * @return result String
	 */
	public static String isoString(String s) {
		String s2 = s;
		try {
			s2 = new String(s.getBytes("GB2312"), "iso-8859-1");
		} catch (Exception e) {
		} finally {
			return s2;
		}
	}

	/**
	 * Encode string to sql friendly format
	 * 
	 * @return result String
	 * @param inStr
	 *            string to be encoded
	 */
	public static String sqlEncode(String inStr) {
		return inStr;
		// return stringReplace(nullToString(inStr), "'", "''");
	}

	/**
	 * Encode string to sql friendly format
	 * 
	 * @return result String
	 * @param inStr
	 *            string to be encoded
	 */
	public static String ToSqlEncode(String inStr) {
		return stringReplace(nullToString(inStr), "'", "''");
	}

	/**
	 * Encode return key to "\n"
	 * 
	 * @return result String
	 * @param inStr
	 *            string to be encoded
	 */
	public static String formatReturnKey(String inStr) {
		if (inStr == null || "".equals(inStr)) {
			return "";
		}
		int i = 0;
		StringBuffer sb = new StringBuffer();
		for (i = 0; i < inStr.length() - 1; i++) {

			if ((int) inStr.charAt(i) != 13 && (int) inStr.charAt(i + 1) != 10) {
				if ((int) inStr.charAt(i) != 13 && (int) inStr.charAt(i) != 10) {
					sb.append(inStr.charAt(i));
				} else {
					sb.append(" ");
				}
			} else {
				sb.append("\\n");
				i++;
			}
		}

		if ((int) inStr.charAt(i) != 13 && (int) inStr.charAt(i) != 10) {
			sb.append(inStr.charAt(i));
		}

		return new String(sb);
	}

	/**
	 * Format specified number to a certain length
	 * 
	 * @param iNumber
	 *            the number to be converted
	 * @param iLength
	 *            the wanted length
	 * @return result String
	 */
	public static String formatFixNumber(int iNumber, int iLength) {
		DecimalFormat decFormat;
		String sFormat = "";
		String sNumber = "";

		try {
			for (int i = 0; i < iLength; i++) {
				sFormat += "0";
			}
			decFormat = new DecimalFormat(sFormat);
			sNumber = decFormat.format(iNumber);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	/**
	 * Convert a string to a non-null value
	 * 
	 * @param inString
	 *            string to be converted
	 * @return result String
	 */
	public static String nullToString(String inString) {
		return (inString == null ? "" : inString.trim());
	}

	public static String nullToString(String inString, String nulltag) {
		return (inString == null ? nulltag : inString);
	}

	/**
	 * Convert a string to a non-null value
	 * 
	 * @param inObject
	 *            to be converted
	 * @return result String
	 */
	public static String nullToString(Object inObject) {
		return (inObject == null ? "" : inObject.toString());
	}

	/**
	 * get the start day of the month. 1 - Sunday, 2- Monday,...,7 - Saturday
	 * 
	 * @param iYear
	 *            year
	 * @param iMonth
	 *            month
	 * @return start day
	 */
	public static int getMonthStartDay(int iYear, int iMonth) {
		Calendar cDate = Calendar.getInstance();
		cDate.set(iYear, iMonth - 1, 1);
		return cDate.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * get the day count of the month. 1 - 31, 2 -28/29,3 - 31,4 - 20 ...
	 * 
	 * @param iYear
	 *            year
	 * @param iMonth
	 *            month
	 * @return the day count of the month
	 */
	public static int getDaysInMonth(int iYear, int iMonth) {
		Calendar cDate = Calendar.getInstance();
		cDate.set(iYear, iMonth, 1);
		cDate.add(Calendar.DATE, -1);
		return cDate.get(Calendar.DATE);
	}

	/**
	 * get the double format.
	 * 
	 * @param iNumber
	 *            @
	 * 
	 * @return String
	 */
	public static String formatDouble(String iNumber) {
		DecimalFormat decFormat;
		String sFormat = "#,##0.00";
		String sNumber = "";
		try {
			decFormat = new DecimalFormat(sFormat);
			sNumber = decFormat.format(Double.parseDouble(iNumber));
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	/**
	 * Convert a Date to String with standard format.
	 * 
	 * @param date
	 *            date to be formatted
	 * @param pattern
	 *            date format pattern, such as yyyy-MM-dd
	 * @return The String which contains date information.
	 */
	public static String formatDate(Date date, String pattern) {
		if (date == null || pattern == null) {
			return new String("");
		}
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * Convert a String to Date.
	 * 
	 * @return Date The Date value parsed from the string as parameter.
	 * @param dateString
	 *            A String contains date information.
	 */
	public static Timestamp parseDate(String dateString, String pattern) {
		if (dateString == null || dateString.trim().equals("")) {
			return null;
		}
		Date d = null;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			d = df.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (d != null) {
			return new Timestamp(d.getTime());
		} else {
			return null;
		}
	}

	public static long parseDateToLong(String dateString, String pattern) {
		long temp = 0;
		if (CommonUtils.isNotEmpty(dateString)) {
			Date d = null;
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			try {
				d = df.parse(dateString);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (d != null) {
				return d.getTime();
			}
		}
		return temp;
	}

	public static String parseLongToDate(long date, String pattern) {
		String sDateTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			// 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
			java.util.Date dt = new Date(date);
			sDateTime = sdf.format(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sDateTime;
	}

	/**
	 * get the double format.
	 * 
	 * @param iNumber
	 *            @ Add by Gwu
	 * 
	 * @return String
	 */
	public static String formatMoney(String iNumber) {
		DecimalFormat decFormat;
		String sFormat = "####.00";
		String sNumber = "";
		try {
			decFormat = new DecimalFormat(sFormat);
			sNumber = decFormat.format(Double.parseDouble(iNumber));
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
		return sNumber;
	}

	public static String locatSubString(String str, int startIndex, int endIndex) {
		int iLen = 0;
		char iUnicode;
		StringBuffer buf = new StringBuffer();

		for (int i = startIndex; iLen < endIndex; i++) {
			iUnicode = str.charAt(i);
			buf.append(iUnicode);
			if (iUnicode <= 128) { // 英文字符
				iLen += 1;
			} else {
				if (iUnicode > 128 && iUnicode <= 255) {
					iLen += 2;
				} else {
					if (iUnicode >= 4112) { // 中文字符
						iLen += 2;
					} else {
						iLen += 1; // 其他字符
					}
				}
			}
		}
		return buf.toString();
		// return str.getBytes().length;
	}

	/**
	 * 生产GUID
	 * 
	 * @return
	 */
	public static String getGUID() {
		return java.util.UUID.randomUUID().toString().toUpperCase();
	}

	public static Long getCurTimestamp() {
		return System.currentTimeMillis();
	}
	
	
	public static Long getCurDateYMD() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr= df.format(new Date());
		try {
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			Date dt2 = df2.parse(dateStr);
			//继续转换得到秒数的long型
			return dt2.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前时间之前N天时间
	 * 
	 * @describe：TODO
	 * @param dates
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20141:32:15 PM
	 */
	public static Long getBeforeDateTimestamp(int dates) {
		return System.currentTimeMillis() - dates * 24 * 60 * 60 * 1000;
	}

	/**
	 * 获取当前时间之前N小时时间
	 * 
	 * @describe：TODO
	 * @param dates
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20141:32:15 PM
	 */
	public static Long getBeforeHourTimestamp(int hours) {
		return System.currentTimeMillis() - hours * 60 * 60 * 1000;
	}

	/**
	 * 获取当前时间之后N小时时间
	 * 
	 * @describe：TODO
	 * @param dates
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20141:32:15 PM
	 */
	public static Long getAfterHourTimestamp(int hours) {
		return System.currentTimeMillis() + hours * 60 * 60 * 1000;
	}

	/**
	 * 获取当前时间之后N小时时间
	 * 
	 * @describe：TODO
	 * @param dates
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20141:32:15 PM
	 */
	public static Long getAfterMinTimestamp(int minutes) {
		return System.currentTimeMillis() + minutes * 60 * 1000;
	}

	/**
	 * 获取当前时间之前N分钟时间
	 * 
	 * @describe：TODO
	 * @param dates
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 12, 20141:32:15 PM
	 */
	public static Long getBeforeMinTimestamp(int minutes) {
		return System.currentTimeMillis() - minutes * 60 * 1000;
	}

	public static Character formatCharacterSingle(String s) {
		if (s != null) {
			return Character.valueOf(Character.valueOf(s.toCharArray()[0]));
		}
		return null;

	}

	/**
	 * 
	 * 从request转化为JSON object
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static JSONObject readJson(HttpServletRequest request)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		String line = null;
		try {
			BufferedReader reader = request.getReader();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.fromObject(sb.toString());
	}

	public static String getParameter(HttpServletRequest request, String key) {
		String[] keys = request.getParameterValues("key");
		String[] values = request.getParameterValues("val");

		if (keys != null && values != null && keys.length == values.length) {
			int j = -1;
			for (int i = 0; i < keys.length; i++) {
				if (keys[i].equals(key)) {
					j = i;
					break;
				}
			}
			if (j >= 0) {
				// return values[j];
				return CommonUtils.fromDecodeUTF8(values[j]);
			}
		}
		// return request.getParameter(key);
		return CommonUtils.fromDecodeUTF8(request.getParameter(key));
	}

	public static boolean existRequestKey(HttpServletRequest request, String key) {
		String[] keys = request.getParameterValues("key");
		String[] values = request.getParameterValues("val");

		if (keys != null && values != null && keys.length == values.length) {
			int j = -1;
			for (int i = 0; i < keys.length; i++) {
				if (keys[i].equals(key)) {
					j = i;
					break;
				}
			}
			if (j >= 0) {
				return true;
			}
		}
		return request.getParameter(key) != null;
	}

	public static Long parseLong(String str) {

		return str == null ? null : Long.parseLong(str);

	}

	public static String getSysDate() {
		String temp = formatDate(new java.util.Date(), "yyyyMMdd HH:mm:ss");
		return temp;
	}

	/**
	 * 判断一个字符串是否为空，如果是true则空，否则非空。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		boolean temp = true;
		if (null != str && !"".equals(str) && !"".equals(str.trim())
				&& !"null".equals(str.trim())) {
			temp = false;
		}
		return temp;
	}
	
	/***
	 * 
	 * @describe：判断一个集合中的项是否有空
	 * @param str
	 * @return
	 * @author:kui.he
	 * @time:2014年9月12日上午10:05:21
	 */
	public static boolean isEmpty(String[] str) {
		boolean temp = true;
		for (String s : str) {
			temp=isEmpty(s);
			if (temp) {
				break;
			}else{
				continue;
			}
		}
		return temp;
	}
	
	

	/**
	 * 判断一个字符串是否为空，如果是true则非空，否则空。
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		boolean temp = false;
		if (null != str && !"".equals(str) && !"".equals(str.trim())
				&& !"null".equals(str.trim())) {
			temp = true;
		}
		return temp;
	}

	/**
	 * 将字符串去掉空格
	 * 
	 * @param str
	 * @return
	 */
	public static String trim(String str) {
		String trimStr = "";
		if (null != str && !"".equals(str)) {
			trimStr = str.trim();
		}
		return trimStr;
	}

	/**
	 * 邮箱正则表达式true匹配
	 * 
	 * @param yx
	 * @return
	 */
	public static boolean matchYx(String yx) {
		boolean temp = false;
		if (CommonUtils.isNotEmpty(yx))
			temp = yx
					.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		return temp;
	}
	
	//用户名正则表达式true匹配
	public static boolean matchYhm(String yhm){
		boolean temp=false;
		temp=yhm.matches("^[a-zA-Z0-9_\u4e00-\u9fa5]+$");
		if(!temp){
			temp=CommonUtils.matchSjhm(yhm);
			if(!temp){
				temp=CommonUtils.matchYx(yhm);
			}
		}
		return temp;
	}

	/**
	 * 手机号码正则表达式true匹配
	 * 
	 * @param sjhm
	 * @return
	 */
	public static boolean matchSjhm(String sjhm) {
		boolean temp = false;
		if (CommonUtils.isNotEmpty(sjhm)) {
			if (sjhm.startsWith("86+")) {// 内地
				sjhm = sjhm.substring("86+".length(), sjhm.length());
			} else if (sjhm.startsWith("+886")) {// 台湾
				sjhm = sjhm.substring("+886".length(), sjhm.length());
			} else if (sjhm.startsWith("+852")) {// 香港
				sjhm = sjhm.substring("+852".length(), sjhm.length());
			} else if (sjhm.startsWith("+853")) {// 澳门
				sjhm = sjhm.substring("+853".length(), sjhm.length());
			}
			temp = sjhm.matches("^[1][3-8]\\d{9}$");// ^1[3|4|5|8][0-9]\\d{4,8}$
			if (!temp) {// 台湾手机10位数，皆以09起头
				temp = sjhm.matches("^[0][9]\\d{8}$");//
			} else if (!temp) {// +853******** 加上去好 刚好11位 澳门手机和固定电话都是8位 手机是6开头
								// 固话是2开头
				temp = sjhm.matches("^[5]\\d{7}$");//
			} else if (!temp) {// +853******** 加上去好 刚好11位 澳门手机和固定电话都是8位 手机是6开头
								// 固话是2开头
				temp = sjhm.matches("^[6]\\d{7}$");//
			} else if (!temp) {// +853******** 加上去好 刚好11位 澳门手机和固定电话都是8位 手机是6开头
								// 固话是2开头
				temp = sjhm.matches("^[9]\\d{7}$");//
			}
		}

		return temp;
	}

	/**
	 * 
	 * @describe：比较两个字符串是否相同(为空返回false)
	 * @param str
	 *            第一个字符串
	 * @param str2
	 *            第二个字符串
	 * @return 返回 true|false
	 * @author:he.kui
	 * @time:2014年5月29日下午7:31:54
	 */
	public static boolean equals(String str, String str2) {
		if (isEmpty(str2) || isEmpty(str)) {
			return false;
		}
		return str.equals(str2);
	}
	
	/**
	 * 
	 * @describe：比较两个字符串,不区分大小写(实现是:先去空,然后都转为大写比较)
	 * @param str
	 * @param str2
	 * @return
	 * @author:kui.he
	 * @time:2014年9月22日下午8:53:08
	 */
	public static boolean equalsCaseInsensitive(String str, String str2) {
		if (isEmpty(str2) || isEmpty(str)) {
			return false;
		}
		return str.trim().toUpperCase().equals(str2.trim().toUpperCase());
	}


	/**
	 * 随机产生一个4位数的数字密码
	 * 
	 * @return
	 */
	public static String generateRamdomNum() {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			// System.out.print("&"+list.get(i));
		}
		// System.out.println("");
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(3, 7);
		return result;
	}
	
	/**
	 * 随机产生一个4位数的数字密码
	 * 
	 * @return
	 */
	public static String generate6RamdomNum() {
		String[] beforeShuffle = new String[] { "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			// System.out.print("&"+list.get(i));
		}
		// System.out.println("");
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(1, 7);
		return result;
	}

	/**
	 * 随机产生一个4位数的密码
	 * 
	 * @return
	 */
	public static String generateWordContantChar() {
		String[] beforeShuffle = new String[] { "2", "3", "4", "5", "6", "7",
				"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K",
				"M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y",
				"Z", "a", "b", "c", "d", "e", "f", "g", "h", "j", "k", "m",
				"n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
		List list = Arrays.asList(beforeShuffle);
		Collections.shuffle(list);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			// System.out.print("&"+list.get(i));
		}
		// System.out.println("");
		String afterShuffle = sb.toString();
		String result = afterShuffle.substring(3, 7);
		return result;
	}

	/**
	 * 获取汉字拼音首字母
	 * 
	 * @describe：汉字拼音首字母类
	 * @param strChinese
	 *            中文字符
	 * @param bUpCase
	 *            返回结果是否转为大写
	 * @return 字符(A-Z字母);
	 * @author:kylin.woo
	 * @time:2014年6月3日下午2:40:15
	 */
	public static char getPYIndexChar(char strChinese, boolean bUpCase) {

		int charGBK = strChinese;

		char result;

		if (charGBK >= 45217 && charGBK <= 45252)

			result = 'A';

		else

		if (charGBK >= 45253 && charGBK <= 45760)

			result = 'B';

		else

		if (charGBK >= 45761 && charGBK <= 46317)

			result = 'C';

		else

		if (charGBK >= 46318 && charGBK <= 46825)

			result = 'D';

		else

		if (charGBK >= 46826 && charGBK <= 47009)

			result = 'E';

		else

		if (charGBK >= 47010 && charGBK <= 47296)

			result = 'F';

		else

		if (charGBK >= 47297 && charGBK <= 47613)

			result = 'G';

		else

		if (charGBK >= 47614 && charGBK <= 48118)

			result = 'H';

		else

		if (charGBK >= 48119 && charGBK <= 49061)

			result = 'J';

		else

		if (charGBK >= 49062 && charGBK <= 49323)

			result = 'K';

		else

		if (charGBK >= 49324 && charGBK <= 49895)

			result = 'L';

		else

		if (charGBK >= 49896 && charGBK <= 50370)

			result = 'M';

		else

		if (charGBK >= 50371 && charGBK <= 50613)

			result = 'N';

		else

		if (charGBK >= 50614 && charGBK <= 50621)

			result = 'O';

		else

		if (charGBK >= 50622 && charGBK <= 50905)

			result = 'P';

		else

		if (charGBK >= 50906 && charGBK <= 51386)

			result = 'Q';

		else

		if (charGBK >= 51387 && charGBK <= 51445)

			result = 'R';

		else

		if (charGBK >= 51446 && charGBK <= 52217)

			result = 'S';

		else

		if (charGBK >= 52218 && charGBK <= 52697)

			result = 'T';

		else

		if (charGBK >= 52698 && charGBK <= 52979)

			result = 'W';

		else

		if (charGBK >= 52980 && charGBK <= 53688)

			result = 'X';

		else

		if (charGBK >= 53689 && charGBK <= 54480)

			result = 'Y';

		else

		if (charGBK >= 54481 && charGBK <= 55289)

			result = 'Z';

		else

			result = (char) (65 + (new Random()).nextInt(25));

		if (!bUpCase)

			result = Character.toUpperCase(result);

		return result;

	}

	/**
	 * 获取汉字拼音首字母
	 * 
	 * @describe：汉字拼音首字母类
	 * @param strChinese
	 *            中文字符
	 * @param bUpCase
	 *            返回结果是否转为大写
	 * @return 字符串(A-Z字母);
	 * @author:kylin.woo
	 * @time:2014年6月3日下午2:40:15
	 */
	public static String getPYIndexStr(String strChinese, boolean bUpCase) {
		String firstPY = "#";
		try {
			String first = CnToPy.getFirstHeadChar(strChinese);
			first = pinYinMap.get(first);
			if (CommonUtils.isNotEmpty(first)) {
				firstPY = first;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstPY;

	}

	/**
	 * 获取汉字拼音首字母
	 * 
	 * @describe：汉字拼音首字母类
	 * @param strChinese
	 *            中文字符
	 * @param bUpCase
	 *            返回结果是否转为大写
	 * @return 字符串(A-Z字母);
	 * @author:kylin.woo
	 * @time:2014年6月3日下午2:40:15
	 */
	public static String getPYIndexStr_bank(String strChinese, boolean bUpCase) {

		try {

			StringBuffer buffer = new StringBuffer();

			byte b[] = strChinese.getBytes("GBK");// 把中文转化成byte数组

			for (int i = 0; i < b.length; i++) {

				if ((b[i] & 255) > 128) {

					int char1 = b[i++] & 255;

					char1 <<= 8;// 左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方

					int chart = char1 + (b[i] & 255);

					buffer.append(getPYIndexChar((char) chart, bUpCase));

					continue;

				}

				char c = (char) b[i];

				if (!Character.isJavaIdentifierPart(c))// 确定指定字符是否可以是 Java
														// 标识符中首字符以外的部分。

					c = '#';

				buffer.append(c);

			}

			return buffer.toString();

		} catch (Exception e) {

			logger.error((new StringBuilder())
					.append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519")
					.append(e.getMessage()).toString());

		}

		return null;

	}

	/**
	 * @describe：获取list<map>对象里key字段的拼音首字母，并添加到map集合中，map中键为firstWord，值为A-Z的key中文第一个字拼音的首字母
	 * @param list
	 * @param key
	 * @return
	 * @throws Exception
	 * @author:kylin.woo
	 * @time:2014年6月5日上午4:51:31
	 */
	public static List<Map> addPY(List<Map> list, String key) {
		for (Map contact : list) {
			String name = (String) contact.get(key);
			if (CommonUtils.isEmpty(name)) {
				contact.put("firstWord", "#");
			} else {
				name = name.substring(0, 1);
				String firstWord = CommonUtils.getPYIndexStr(name, true);
				contact.put("firstWord", firstWord.toUpperCase());
			}
		}
		return list;
	}

	public static List<Map> reSortList(List<Map> resList, int startCount) {
		List<Map> targetList = new ArrayList<Map>();
		if (resList != null && resList.size() > 0) {
			if (startCount >= resList.size()) {
				targetList = resList;
			} else {
				int[] intRet = new int[resList.size() - startCount];

				for (int i = 0; i < resList.size(); i++) {// 获取前startCount位的值
					Map map = resList.get(i);
					if (i < startCount) {
						targetList.add(map);
					} else {
						intRet = getRomdomByInt(resList.size() - startCount);
						break;
					}
				}
				for (int i = 0; i < intRet.length; i++) {// 随机获取startCount之后的值
					Map map = resList.get(intRet[i] + startCount);
					targetList.add(map);
				}
			}
		}
		return targetList;
	}

	/**
	 * 获取0到value的随机数组值
	 * 
	 * @describe：TODO
	 * @param value
	 * @return
	 * @author:yongqin.zhong
	 * @time:Jun 14, 201411:31:51 AM
	 */
	public static int[] getRomdomByInt(int value) {
		int[] intRet = new int[value];
		int intRd = 0; // 存放随机数
		int count = 0; // 记录生成的随机数个数
		int flag = 0; // 是否已经生成过标志
		while (count < value) {
			Random rdm = new Random();
			intRd = Math.abs(rdm.nextInt()) % value;// 获得的随机数有正有负的，用Math.abs使获取数据范围为非负数
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				count++;
			}
		}
		return intRet;
	}

	/**
	 * 将字符串编码成 Unicode 形式的字符串. 如 "黄" to "\u9EC4" Converts unicodes to encoded
	 * \\uxxxx and escapes special characters with a preceding slash
	 * 
	 * @param theString
	 *            待转换成Unicode编码的字符串。
	 * @param escapeSpace
	 *            是否忽略空格，为true时在空格后面是否加个反斜杠。
	 * @return 返回转换后Unicode编码的字符串。
	 */
	public static String toEncodedUnicode(String theString, boolean escapeSpace) {
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}

			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace)
					outBuffer.append('\\');
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=': // Fall through
			case ':': // Fall through
			case '#': // Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					// 每个unicode有16位，每四位对应的16进制从高位保存到低位
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}

		return outBuffer.toString();
	}

	public static String fromEncodeUnicode(String s) {
		String result = "";
		if (CommonUtils.isNotEmpty(s)) {
			result = fromEncodedUnicode(s.toCharArray(), 0, s.length());
		}

		return result;
	}

	public static String fromDecodeUTF8(String s) {
		String result = "";
		if (CommonUtils.isNotEmpty(s)) {
			try {
				result = java.net.URLDecoder.decode(s, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public static String encodeUTF8(String s) {
		String result = "";
		if (CommonUtils.isNotEmpty(s)) {
			try {
				result = java.net.URLEncoder.encode(s, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄". Converts encoded
	 * \\uxxxx to unicode chars and changes special saved chars to their
	 * original forms
	 * 
	 * @param in
	 *            Unicode编码的字符数组。
	 * @param off
	 *            转换的起始偏移量。
	 * @param len
	 *            转换的字符长度。
	 * @param convtBuf
	 *            转换的缓存字符数组。
	 * @return 完成转换，返回编码前的特殊字符串。
	 */
	public static String fromEncodedUnicode(char[] in, int off, int len) {
		char aChar;
		char[] out = new char[len]; // 只短不长
		int outLen = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = (char) aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	public static String toString(Object obj) {
		if (obj == null) {
			return "";
		}
		return obj.toString();
	}

	public static String formatStrToStrDate(String strDate) {
		String format = "yyyy-MM-dd HH:mm:ss";
		Date date = CommonUtils.parse(strDate, format);
		return CommonUtils.formatDateTime(date, format);
	}

	public static String formatDateToStr(Date date) {
		String format = "yyyy-MM-dd HH:mm:ss";
		return CommonUtils.formatDateTime(date, format);
	}

	/**
	 * 获取年月日时分秒，格式：2012-03-14-111203
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateYMDHMSoStr(Date date) {
		String format = "yyyy-MM-dd-HHmmss";
		return CommonUtils.formatDateTime(date, format);
	}

	/**
	 * 获取日期的年月日，格式：2012-03-14
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateYMDToStr(Date date) {
		String format = "yyyy-MM-dd";
		return CommonUtils.formatDateTime(date, format);
	}

	/**
	 * 根据格式获取日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDateToStr(Date date, String format) {
		return CommonUtils.formatDateTime(date, format);
	}

	/**
	 * 获取日期的年月日，格式：2012-03-14
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatDateYMDToDate(Date date) {
		String format = "yyyy-MM-dd";
		String dateStr = CommonUtils.formatDateTime(date, format);
		return parse(dateStr, format);
	}

	/**
	 * 获取当前日期的年月日，格式：2012-03-14
	 * 
	 * @param date
	 * @return
	 */
	public static Date getCurrentDateYMD() {
		String format = "yyyy-MM-dd";
		String date = CommonUtils.formatDateTime(new Date(), format);
		return parse(date, format);
	}

	/**
	 * 把固定格式的日期字符串转换成日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date2 = null;
		if (date != null && !"".equals(date.trim())) {
			try {
				date2 = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date2;
	}

	/**
	 * 把固定格式的日期转换成字符串日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parse(Date date, String format) {
		String temp = "";
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			try {
				temp = sdf.format(date);
			} catch (Exception e) {
				logger.error(date + "不是【" + format + "】格式的日期！");
			}
		}
		return temp;
	}

	/**
	 * 增加天数方法(传入日期格式：2012-03-14)
	 * 
	 * @param date
	 * @param n
	 * @return 返回格式：2012-03-14
	 */
	public static String getAfterStrDay(Date date, int n) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, n);// 增加一天
			// cd.add(Calendar.MONTH, n);//增加一个月

			return sdf.format(cd.getTime());

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 按照自己设置的格式获取Data字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateTime(Date date, String format) {
		SimpleDateFormat timeFormator = new SimpleDateFormat(format);
		return timeFormator.format(date);
	}

	/**
	 * @describe：获取一个令牌
	 * @param request
	 * @return
	 * @author:kylin.woo
	 * @time:2014年7月1日上午9:21:37
	 */
	protected String generateToken(HttpServletRequest request) {
		HttpSession session = request.getSession();
		try {
			byte id[] = session.getId().getBytes();
			byte now[] = new Long(System.currentTimeMillis()).toString()
					.getBytes();
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(id);
			md.update(now);
			return md.digest().toString();
		} catch (IllegalStateException e) {
			return (null);
		} catch (NoSuchAlgorithmException e) {
			return (null);
		}
	}

	public static String getWeekByDay(String date) {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdw = new SimpleDateFormat("E");
		Date d = null;
		try {
			d = sd.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sdw.format(d);

	}

	public static String getXqByDate(String dateStr) {
		Date date = parse(dateStr, "yyyy-MM-dd");
		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (week_index < 0) {
			week_index = 0;
		}
		return weeks[week_index];
	}

	/**
	 * 判断文件类型是否正确
	 * 
	 * @param fileEnd
	 * @param fileType
	 * @return
	 */
	public static boolean checkImgFileType(String fileEnd, String fileType) {
		boolean isRealType = false;
		if (CommonUtils.isEmpty(fileType)) {
			fileType = CommonUtils.imgType;
		}
		if (fileType.indexOf(",") != -1) {
			String[] arrType = fileType.split(",");
			for (String str : arrType) {
				if (fileEnd.equals(str.toLowerCase())) {
					isRealType = true;
					break;
				}
			}
		} else {
			if (fileEnd.equals(fileType.toLowerCase())) {
				isRealType = true;
			}
		}
		return isRealType;
	}
	
	
	/**
	 * 判断录音文件类型是否正确
	 * 
	 * @param fileEnd
	 * @param fileType
	 * @return
	 */
	public static boolean checkMediaFileType(String fileEnd, String fileType) {
		boolean isRealType = false;
		if (CommonUtils.isEmpty(fileType)) {
			fileType = CommonUtils.mediaType;
		}
		if (fileType.indexOf(",") != -1) {
			String[] arrType = fileType.split(",");
			for (String str : arrType) {
				if (fileEnd.equals(str.toLowerCase())) {
					isRealType = true;
					break;
				}
			}
		} else {
			if (fileEnd.equals(fileType.toLowerCase())) {
				isRealType = true;
			}
		}
		return isRealType;
	}

	public static BigDecimal formatStrToBigDecimal(String income) {
		double incomeDouble = Double.valueOf(income);
		DecimalFormat df = new DecimalFormat("#.00");
		BigDecimal resBigDecimal = new BigDecimal(df.format(incomeDouble));
		return resBigDecimal;
	}

	/**
	 * 判断两个日期时间的大小(接受的日期时间格式为 yyyy-MM-dd)
	 * 
	 * @param dateTime1
	 *            日期
	 * @param dateTime2
	 *            日期
	 * @return int date1大于date2返回1，返回-1则date1小于date2，返回0则相同
	 * 
	 */
	public static int compareDateYMD(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"解析日期时间格式出错，期望的字符串格式为[yyyyMMdd HH:mm:ss]");
		}
	}

	/**
	 * 判断两个日期时间的大小(接受的日期时间格式为 yyyy-MM-dd)
	 * 
	 * @param dateTime1
	 *            日期
	 * @param dateTime2
	 *            日期
	 * @return int date1大于date2返回1，返回-1则date1小于date2，返回0则相同
	 * 
	 */
	public static int compareDateYMD(long date1, long date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(df.format(date1));
			Date dt2 = df.parse(df.format(date2));
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"解析日期时间格式出错，期望的字符串格式为[yyyyMMdd HH:mm:ss]");
		}
	}

	/**
	 * 判断两个日期时间的大小(接受的日期时间格式为 yyyy-MM-dd)
	 * 
	 * @param dateTime1
	 *            日期
	 * @param dateTime2
	 *            日期
	 * @return int date1大于date2返回1，返回-1则date1小于date2，返回0则相同
	 * 
	 */
	public static int compareDateYMDHMS(long date1, long date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(df.format(date1));
			Date dt2 = df.parse(df.format(date2));
			if (dt1.getTime() > dt2.getTime()) {
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new RuntimeException(
					"解析日期时间格式出错，期望的字符串格式为[yyyyMMdd HH:mm:ss]");
		}
	}

	/**
	 * 更加毫米级时间获取规定时间日期（yy/mm/dd hh:mm）
	 * 
	 * @param date
	 * @param n
	 * @return 返回格式：2012-03-14
	 */
	public static String getStrDayByTime(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
			Date date = new Date(time);
			return sdf.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 更加毫米级时间获取规定时间日期（yy/mm/dd hh:mm）
	 * 
	 * @param date
	 * @param n
	 * @return 返回格式：2012-03-14
	 */
	public static String getStrDateByTime(long time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
			Date date = new Date(time);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 将字符串进行BASE64编码
	 * @describe：TODO
	 * @param s
	 * @return
	 * @author:kui.he
	 * @time:2014年7月22日下午4:47:21
	 */
	public static String getBASE64(String s) {
			if (s == null)
				return null;
			String str="";
			try {
				byte[] b = Base64.encodeBase64(s.getBytes("UTF8"), true);
				str = new String(b,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-zdnst catch block
				e.printStackTrace();
			}
			
			//String str= (new sun.misc.BASE64Encoder()).encode(s.getBytes());
		//String str=Base64.encodeBase64(s);
		logger.info("*********************getBASE64:"+str+"*****************");
		return str;
	}
		
	/**
	 * 将BASE64编码的字符串进行编码	
	 * @describe：TODO
	 * @param s
	 * @return
	 * @author:kui.he
	 * @time:2014年7月22日下午4:47:46
	 */
	public static String getFromBASE64(String s) {
		String str="";
		//String str= Base64.decodeBase64(s);
		try {
			if(CommonUtils.isNotEmpty(s)){
				byte[] b = Base64.decodeBase64(s.getBytes("UTF8"));  
				str=new String(b,"UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**
		byte[] b = decoder.decodeBuffer(s);
		String str=new String(b);
		*/
		logger.info("*********************getFromBASE64:"+str+"*****************");
		return str;
	}

	/**
	 * @describe：在需要防范SQL注入的JavaBean的字段上调用此方法过滤
	 * @param sql
	 *            传递给SQL作为参数的字段值等
	 * @return
	 * @author:huazhou.yang
	 * @time:2014年7月30日下午7:54:19
	 */
	public static String avoidSQLInjection(String sql) {
		return sql.replaceAll(".*([';]+|(--)+).*", " ");
	}

	/**
	 * iso8859转为utf8
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String getStringUtf8(String src) throws Exception {
		if (src == null) {
			return src;
		}
		return new String(src.getBytes("ISO-8859-1"), "UTF-8");
	}

	/**
	 * 
	 * @describe：获取指定Map指定Key的值,当没有的时候返回给定的默认值
	 * @param map
	 * @param key
	 * @param defaultValue
	 * @return
	 * @throws Exception
	 * @author:kui.he
	 * @time:2014年9月12日上午11:37:28
	 */
	public static String getString(Map map,String key,String defaultValue){
		if (map.containsKey(key)) {
			Object obj=map.get(key);
			if (obj==null) {
				return defaultValue;
			}else{
				return obj.toString();
			}
		}
		return defaultValue;
	}

	/**
	 * 将一个对象转换为字符串，如果字符串为空直接设定的返回默认字符串
	 * @describe：TODO
	 * @param obj
	 * @param defaultValue
	 * @return
	 * @author:kui.he
	 * @time:2014年9月13日下午12:31:19
	 */
	public static String getString(Object obj,String defaultValue){
		if (obj==null) {
			return defaultValue;
		}
		if (isEmpty(obj.toString())) {
			return defaultValue;
		}else{
			return obj.toString();
		}
	}
	/**
	 * @describe：对要在WEB页面显示的内容进行转义，以避免JS注入
	 * @param content
	 *            内容
	 * @return
	 * @author:huazhou.yang
	 * @time:2014年7月30日下午7:36:42
	 */
	public static String avoidJSInjection(String content) {
		if (content == null) {
			return content;
		}
		String temp = content.toLowerCase();
		int start = StringUtils.indexOfIgnoreCase(temp, "<script");
		int end = StringUtils.indexOfIgnoreCase(temp, "</script>");
		if (start >= 0 && end >= 0) {
			temp = content.replaceAll("<", "&lt;");
			return temp;
		}
		return content;
	}

	/**
	 * @describe：获取IP地址
	 * @param request
	 * @return
	 * @author:kylin.woo
	 * @time:2014年9月1日上午8:15:34
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	/**
	 * 判断第一个字符串是否包含后面字符数组中的任意一个字符串
	 * @describe：TODO
	 * @param str
	 * @param strs
	 * @return
	 * @author:kui.he
	 * @time:2014年9月5日上午10:53:57
	 */
	public static boolean isContains(String str,String[] strs){
		for (String s : strs) {
			if(str.indexOf(s)>-1)return true;
		}
		return false;
	}
	
	/**
	 * 将带双引号的字符串格式，去掉双引号
	 * @describe：TODO
	 * @param str
	 * @return
	 * @author:yongqin.zhong
	 * @time:Oct 11, 20146:44:43 PM
	 */
	public static String removeStartAndEnd(String str){
		if (CommonUtils.isEmpty(str)) {
			return "";
		}
		if (str.length()<=2) {
			return str;
		}
		
		if(str.startsWith("\"")){
			String s=str.substring(1,str.length()-1);
			return s;
		}
		return str;
	}
	

	/**
	 * @describe：TODO
	 * @param args
	 * @author:kui.he
	 * @time:2014年9月29日上午11:12:58
	 */
	public static void main(String[] args) {

		String s = "http://192.168.1.151/maps1.0/api/action/NewActivity/postNewAction&hash=4265CCFB-54CE-4F9&uploadData={\"voteCondition\":\"30\",\"addressDetail\":\"\",\"timeVoteEndTime\":\"\",\"addressVoteEndTime\":\"\",\"timeVoteList\":[],\"img\":\"\",\"beginTime\":\"2014-07-11 20:38:19\",\"managerUserIds\":[{\"username\":\"孔兆祥\",\"userId\":\"25B95D77-C092-45E4-A94A-2369BE46DBEF\"}],\"taskSettings\":[{\"nodename\":\"媒体合作\",\"nodecode\":\"100100\",\"state\":\"0\"},{\"nodename\":\"线下推广\",\"nodecode\":\"100101\",\"state\":\"0\"},{\"nodename\":\"场地查看\",\"nodecode\":\"101100\",\"state\":\"1\"},{\"nodename\":\"场地预定\",\"nodecode\":\"101101\",\"state\":\"0\"},{\"nodename\":\"场地布置\",\"nodecode\":\"101102\",\"state\":\"0\"},{\"nodename\":\"座位排布\",\"nodecode\":\"101103\",\"state\":\"0\"},{\"nodename\":\"采购清单\",\"nodecode\":\"102100\",\"state\":\"0\"},{\"nodename\":\"采购实施\",\"nodecode\":\"102101\",\"state\":\"0\"},{\"nodename\":\"预算制定\",\"nodecode\":\"103100\",\"state\":\"0\"},{\"nodename\":\"费用记录\",\"nodecode\":\"103101\",\"state\":\"0\"},{\"nodename\":\"抽签\",\"nodecode\":\"104100\",\"state\":\"0\"},{\"nodename\":\"抢票\",\"nodecode\":\"104101\",\"state\":\"0\"},{\"nodename\":\"售票\",\"nodecode\":\"104102\",\"state\":\"0\"},{\"nodename\":\"报名\",\"nodecode\":\"104103\",\"state\":\"0\"},{\"nodename\":\"嘉宾\",\"nodecode\":\"104104\",\"state\":\"0\"},{\"nodename\":\"资料管理\",\"nodecode\":\"105100\",\"state\":\"0\"}],\"addressVoteList\":[],\"latitude\":\"\",\"tag\":\"夏令营\",\"cost\":\"1\",\"theme\":\"主题\",\"projectFlag\":\"1\",\"taskList\":[{\"taskDesc\":\"\",\"itemtypeid\":\"1\",\"beginTime\":\"\",\"operatorIds\":[{\"username\":\"葛晓波\",\"userId\":\"08376B42-BFAD-49B0-A162-94CB532BEBFB\"}],\"auditorIds\":[{\"username\":\"坚洪\",\"userId\":\"A55AA493-5E27-4B54-982E-E0C9D9DA31B9\"}],\"tagname\":\"\",\"address\":\"\",\"nodecode\":\"101100\",\"nodename\":\"场地查看\",\"important\":\"\",\"activityIndentaionLevel\":\"1\",\"parentnodeid\":\"101\",\"taskPigeonhole\":\"\",\"endTime\":\"\",\"fileNo\":\"\",\"taskcardtype\":\"0\"},{\"taskDesc\":\"这是任务描述\",\"itemtypeid\":\"\",\"beginTime\":\"2014-07-11 20:39:23\",\"operatorIds\":[{\"username\":\"葛晓波\",\"userId\":\"08376B42-BFAD-49B0-A162-94CB532BEBFB\"}],\"auditorIds\":[{\"username\":\"坚洪\",\"userId\":\"A55AA493-5E27-4B54-982E-E0C9D9DA31B9\"}],\"tagname\":\"\",\"address\":\"\",\"nodecode\":\"\",\"nodename\":\"新任务\",\"important\":\"3\",\"activityIndentaionLevel\":\"2\",\"parentnodeid\":\"101100\",\"taskPigeonhole\":\"\",\"endTime\":\"2014-07-12 20:39:23\",\"fileNo\":\"\",\"taskcardtype\":\"\"}],\"address\":\"天源广场\",\"longitude\":\"\",\"creatorName\":\"雷纯锋\",\"tagType\":\"6\",\"groupId\":\"0270FEF5-7F22-4457-B8B5-3A604E1003B5\",\"groupName\":\"支点网络\",\"member\":\"\",\"actionId\":\"\",\"workUserIds\":[{\"username\":\"雷纯锋\",\"userId\":\"66FD1ED1-9E08-48AE-8B82-A8D00531FD9A\"}],\"endTime\":\"2014-07-12 20:38:19\",\"description\":\"描述\",\"activityProperty\":\"1\",\"timeVoteFlag\":\"0\",\"addressVoteFlag\":\"0\"}";

		// System.out.println(encodeBase64("zdios"));
		// System.out.println(encodeBase64("x415312"));
		String mobile = "61337306";

		boolean isMobile = matchSjhm(mobile);

		// 获取图片的原长、宽 ssss*400x200.jpg
		String fileName = "9B46E304-6F6B-4D91-8B39-C0E00C54A5A6_288x220_TRUE_120x232.png";
		String str = "";
		if (fileName.indexOf("*") != -1) {
			str = fileName.substring(fileName.indexOf("*") + 1,
					fileName.indexOf("_"));
		} else {
			String subFileName = fileName.substring(fileName.indexOf("_") + 1,
					fileName.length());
			str = subFileName.substring(0, subFileName.indexOf("_"));
		}

		//System.out.println(str);

		// String[] t = s.split("\n");
		// System.out.println(locatSubString(s, 0, getStrAllocateLen(s)));
		// getRomdomByInt(20);
		/**
		 * List<Map> targetList = new ArrayList<Map>(); Map map=new HashMap();
		 * for (int i = 0; i < 20; i++) { map=new HashMap(); map.put("a"+i,
		 * "a"+i); targetList.add(map); }
		 * 
		 * System.out.println(reSortList(targetList,4));
		 */

		
		System.out.println(getStrDateByTime(CommonUtils.getCurTimestamp()));
		
		

	}
	
	
	

}