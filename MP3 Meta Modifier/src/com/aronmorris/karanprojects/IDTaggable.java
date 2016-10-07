package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.Mp3File;


/**
 * Defines the methods used to access ID3v1 tags
 * @author Aron
 *
 */
public interface IDTaggable {
	
	public Mp3File getMp3File();
	public void setMp3File(Mp3File mp3);

	public String getTrack();
	public void setTrack(String track);
	
	public String getArtist();
	public void setArtist(String artist);
	
	public String getTitle();
	public void setTitle(String title);
	
	public String getAlbum();
	public void setAlbum(String album);
	
	public String getYear();
	public void setYear(String year);
	
	public String getGenre();
	public void setGenre(int genre);
	
	public String getGenreDescription();
	
	public String getComment();
	public void setComment(String comment);
	
}
