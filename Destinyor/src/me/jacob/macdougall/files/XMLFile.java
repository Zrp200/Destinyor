package me.jacob.macdougall.files;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XMLFile {
	
	private final String location;
	
	private Document document;
	private Document doc;
	
	
	public XMLFile(String location) {
		this.location = location;
		
		try {
		init();
                //init2();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
                }
	}
	
	private void init() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = builder.newDocumentBuilder();
		document = db.parse(this.getClass().getResourceAsStream(location));
                DocumentBuilderFactory builder2 = DocumentBuilderFactory.newInstance();
		DocumentBuilder db2 = builder2.newDocumentBuilder();
		doc = db2.parse(this.getClass().getResourceAsStream(location));
		}
	
//	private void init2() throws ParserConfigurationException, SAXException, IOException {
//		DocumentBuilderFactory builder2 = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db2 = builder2.newDocumentBuilder();
//		doc = db2.parse(this.getClass().getResourceAsStream(location));
//	}
	
	public String getPath() {
		return location;
	}
	
	public Document asDocument() {
		return document;
	}
	
	public Document asDocument2() {
		return doc;
	}

}
