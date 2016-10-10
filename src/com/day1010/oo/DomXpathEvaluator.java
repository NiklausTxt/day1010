package com.day1010.oo;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;






public class DomXpathEvaluator {
	public static void main(String[] args){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("sample.xml");
			
			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression xPathExpression = xpath.compile("//student[./gender='Male']");
			NodeList list = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
			for(int index = 0; index < list.getLength(); index++){
				Node node = list.item(index);
				String id = node.getAttributes().getNamedItem("id").getNodeValue();
				System.out.println("Student id:"+id);
				NodeList childNodes = node.getChildNodes();
				for(int i=0;i<childNodes.getLength();i++){
					Node childNode = childNodes.item(i);
					if(childNode.getNodeType() != Node.ELEMENT_NODE){
						continue;
					}
					String nodeName = childNode.getNodeName();
					String nodeValue = childNode.getFirstChild().getNodeValue();
					System.out.println(" node: "+ nodeName+" = "+nodeValue);
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		
	}
}
