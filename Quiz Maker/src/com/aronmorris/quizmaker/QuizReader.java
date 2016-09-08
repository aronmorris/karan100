package com.aronmorris.quizmaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.yaml.snakeyaml.Yaml;

import com.google.common.collect.Lists;




public class QuizReader {
	
	private final static String QUIZ_XML_PATH = "resources\\QuizReader\\quizzes.xml"; //win mode, UNIX not set yet (do with ternary?)
	
	private SAXParser parser;
	
	QuizReader(File quizFile) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		factory.setValidating(true);
	
		SAXParser sParser;
		
		try {
			sParser = factory.newSAXParser();
			
			sParser.parse(quizFile, new DefaultHandler());
			
			parser = sParser;
			
		} catch (ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	//TODO switch from YML to XML, since XML is easier to retrieve specifics from
	
	
	public static void main(String[] args) {
		
		QuizReader qr = new QuizReader(new File(QUIZ_XML_PATH));

	}
	
}
