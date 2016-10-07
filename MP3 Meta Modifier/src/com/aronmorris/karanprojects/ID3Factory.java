package com.aronmorris.karanprojects;

public class ID3Factory {

	private IDTaggable handler;
	
	public IDTaggable getHandler(TagVersion version) {
		return getHandlerInstance(version);
	}
	
	public boolean setMp3(Mp3File mp3) {
		
	}
	
	private IDTaggable getHandlerInstance(TagVersion version) {
		
		switch (version) {
		case v1: handler = new ID3v1Handler();
			break;
		case v2: handler = new ID3v2Handler();
			break;
		case custom: handler = new ID3vCustomHandler();
			break;
		default: handler = null; //this shouldnt happen
			break;
		}
		
		return handler;
		
	}
	
}
