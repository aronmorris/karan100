package com.aronmorris.karanprojects;

import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TagWriter {

	public static void main(String[] args) {
		
		try {
			Mp3File mp3 = new Mp3File("24 Bonetrousle.mp3");
			
			IDTaggable id3v1 = ID3Factory.getHandler(TagVersion.v1, mp3);
			
			ID3v2Handler id3v2 = new ID3v2Handler(ID3Factory.getHandler(TagVersion.v2, mp3));
			
			
			
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
