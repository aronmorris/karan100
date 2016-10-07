package com.aronmorris.karanprojects;


/**
 * Defines the methods used to access ID3v1 tags
 * @author Aron
 *
 */
public interface IDTaggable {

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
	public void setGenre(String genre);
	
	public String getGenreDescription();
	public void setGenreDescription(String description);
	
	public String getComment();
	public void setComment(String comment);
	
}
