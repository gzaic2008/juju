package com.zdnst.common.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.zdnst.common.base.BaseField;
import com.zdnst.common.base.BaseRemoteCaller;



public class XMLUtils {

	private List fieldList = new ArrayList();
	private BaseRemoteCaller brc = new BaseRemoteCaller();

	/**
	 * 遍历
	 * 
	 * @param rootDoc
	 * @throws Exception
	 */
	private void treeWalk(Document rootDoc) throws Exception {
		// getRootElement()

		treeWalk(rootDoc);
	}

	private void treeWalk(Element element) throws Exception {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
				Element e = (Element) node;

				// cell信息
				if (e.getName().equals("field")) {
					BaseField field = new BaseField();
					field.setValue(e.getText());
					for (Iterator it = e.attributeIterator(); it.hasNext();) {
						Attribute attribute = (Attribute) it.next();

						if (attribute.getName().equalsIgnoreCase("name")) {
							field.setName(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase("type")) {
							field.setType(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"format")) {
							field.setFormat(attribute.getText());
						} else if (attribute.getName()
								.equalsIgnoreCase("title")) {
							field.setTitle(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase("key")) {
							field.setPrimaryKey(attribute.getText());
						}
					}

					fieldList.add(field);
				} else if (e.getName().equals("action")) {
					// action rpc

					for (Iterator it = e.attributeIterator(); it.hasNext();) {
						Attribute attribute = (Attribute) it.next();

						if (attribute.getName().equalsIgnoreCase("name")) {
							brc.setName(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"method")) {
							brc.setMethod(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"application")) {
							brc.setApplication(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase("host")) {
							brc.setHost(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"company")) {
							brc.setCompany(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"senduserid")) {
							brc.setSendUserID(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"sendusername")) {
							brc.setSendUserName(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"sendtime")) {
							brc.setSendTime(attribute.getText());
						} else if (attribute.getName().equalsIgnoreCase(
								"subject")) {
							brc.setSubject(attribute.getText());
						}

					}

				}

			} else {

			}
		}
	}

	public BaseRemoteCaller parseXml(String xmldata_str) throws Exception {
		if(xmldata_str==null ){
			return null;
		}

		Document doc = DocumentHelper.parseText(xmldata_str);

		treeWalk(doc.getRootElement());
		brc.setFieldList(fieldList);

		return brc;

	}

}
