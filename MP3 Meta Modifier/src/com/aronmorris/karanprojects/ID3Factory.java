package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.Mp3File;

public class ID3Factory {

	public static IDTaggable getHandler(TagVersion version, Mp3File mp3) {
		return getHandlerInstance(version, mp3);
	}
	
	private static IDTaggable getHandlerInstance(TagVersion version, Mp3File file) {
		
		IDTaggable handler;
		
		switch (version) {
		case v1: if (file.hasId3v1Tag()) handler = new ID3v1Handler(file);
			     else { 
			    	 handler = null; 
			    	 System.out.println("No ID3v1 tag!");
			     }
			break;
		case v2: if (file.hasId3v2Tag() )handler = new ID3v2Handler(file);
			     else { 
			    	 handler = null; 
			    	 System.out.println("No ID3v2 tag!");
			     }
			break;
		case custom: handler = new ID3vCustomHandler(file);
			break;
		default: handler = null; //this shouldnt happen
			break;
		}
		
		return handler;
		
	}
	
}
