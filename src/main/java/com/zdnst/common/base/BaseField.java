package com.zdnst.common.base;

/**
 * 字段说明 远程调用
 * 
 * @author zhihong.peng
 * 
 */
public class BaseField implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String title;
	private String type;
	private String isPrimaryKey;
	private String format;
	private String value;

	public BaseField() {
	}

	public BaseField(String _name, String _type, String _value) {
		this.name = _name;
		this.type = _type;
		this.value = _value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the isPrimaryKey
	 */
	public boolean isPrimaryKey() {
		if(isPrimaryKey!=null && isPrimaryKey.trim().equals("1")){
			return true;
		}
		
		return isPrimaryKey == null ? false : Boolean
				.parseBoolean(isPrimaryKey);
	}

	/**
	 * @param isPrimaryKey
	 *            the isPrimaryKey to set
	 */
	public void setPrimaryKey(String isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseField [name=" + name + ", title=" + title + ", type="
				+ type + ", isPrimaryKey=" + isPrimaryKey + ", format="
				+ format + ", value=" + value + "]";
	}
	
	
	

}
