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
	// 当前标签
	String currentTag = null;
	// 当前的元素值
	String currentValue = null;
	// 当前的元素名
	String currentName = null;
	// 开始解析的元素
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
	 * 开始解析的时候调用
	 */
	@Override
	public void startDocument() throws SAXException {
		System.out.println("开始解析");
		list = new ArrayList<>();
	}

	/**
	 * 每个元素开始解析的时候调用
	 */
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// 判断当前的元素是不是我要开始解析的元素
		if (qName.equals(nodeName)) {
			map = new HashMap<>();
		}
		//读取属性
		if (attributes != null && map != null) {
			for(int i=0;i<attributes.getLength();i++){
				map.put(attributes.getQName(i), attributes.getValue(i));
			}
		}
		currentTag = qName;
	}

	/**
	 * 解析到每个元素的内容调用
	 */
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if(currentTag!=null && map!=null){
			currentValue = new String(ch, start, length);
			//空格符和换行符去掉
			if(currentValue!=null && !currentValue.trim().equals("") && !currentValue.trim().equals("\n")){
				map.put(currentTag, currentValue);
			}
			//置空 开始下一个
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
