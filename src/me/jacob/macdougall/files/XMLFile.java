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

	public XMLFile(String location) {
		this.location = location;

		try {
			init();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	private void init() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory builder = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = builder.newDocumentBuilder();
		document = db.parse(this.getClass().getResourceAsStream(location));
	}

	public String getPath() {
		return location;
	}

	public Document asDocument() {
		return document;
	}

}
