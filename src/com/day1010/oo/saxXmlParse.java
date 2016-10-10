package com.day1010.oo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class saxXmlParse extends DefaultHandler {
	Map<String, String> map = null;
	static List<Map<String, String>> list = null;
	// ��ǰ��ǩ
	String currentTag = null;
	// ��ǰ��Ԫ��ֵ
	String currentValue = null;
	// ��ǰ��Ԫ����
	String currentName = null;
	// ��ʼ������Ԫ��
	String nodeName;

	public saxXmlParse(String nodeName) {
		this.nodeName = nodeName;
	}
	
	public List<Map<String, String>> getList(){
		return list;
	}
	
	public static List<Map<String,String>> ReadXML(InputStream uri,String NodeName) throws Exception {
       
            SAXParserFactory parserFactory=SAXParserFactory.newInstance();
            SAXParser parser=parserFactory.newSAXParser();
            saxXmlParse myhandler=new saxXmlParse(NodeName);
            parser.parse(uri, myhandler);
            return myhandler.getList();
       
    }

	/**
	 * ��ʼ������ʱ�����
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("��ʼ����");
		list = new ArrayList<>();
	}

	/**
	 * ÿ��Ԫ�ؿ�ʼ������ʱ�����
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// �жϵ�ǰ��Ԫ���ǲ�����Ҫ��ʼ������Ԫ��
		if (qName.equals(nodeName)) {
			map = new HashMap<>();
		}
		//��ȡ����
		if (attributes != null && map != null) {
			for(int i=0;i<attributes.getLength();i++){
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;
	}

	/**
	 * ������ÿ��Ԫ�ص����ݵ���
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(currentTag!=null && map!=null){
			currentValue = new String(ch, start, length);
			//�ո���ͻ��з�ȥ��
			if(currentValue!=null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")){
				map.put(currentTag, currentValue);
			}
			//�ÿ� ��ʼ��һ��
			currentTag=null;
			currentValue=null;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals(nodeName)){
			list.add(map);
			map=null;
		}
		
	}

	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	

}
