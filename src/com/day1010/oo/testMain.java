package com.day1010.oo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class testMain {
	public static void main(String[] args) {
		try {
			InputStream input = new FileInputStream("sample.xml");
			List<Map<String, String>> list = saxXmlParse.ReadXML(input,"student");
			for(Map<String, String> map:list){
				for(Entry<String, String> entry:map.entrySet()){
					System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
