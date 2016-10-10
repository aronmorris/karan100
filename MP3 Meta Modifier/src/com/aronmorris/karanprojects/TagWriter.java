package com.aronmorris.karanprojects;

import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class TagWriter {

	public static void main(String[] args) {
		
		try {
			Mp3File mp3 = new Mp3File(
					"D:\\Programming\\Java\\JavaWorkspace\\Projects\\MP3 Meta Modifier\\Resources\\bonetrousle.mp3");
			
			IDTaggable id3v1 = ID3Factory.getHandler(TagVersion.v1, mp3);
			
			ID3v2Handler id3v2 = new ID3v2Handler(ID3Factory.getHandler(TagVersion.v2, mp3));
			
			if (id3v1 != null) {
				System.out.println(id3v1.getAlbum());
			}
			else {
				System.out.println("No id3v1 tag found.");
			}
			
			if (id3v2 != null) { 
				System.out.println("Composer: " + id3v2.getComposer());
				id3v2.setComposer("Toby Faux");
				System.out.println("Composer: " + id3v2.getComposer());
			}
			else {
				System.out.println("No id3v2 tag found.");
			}
			
			
			
		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
