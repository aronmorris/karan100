package com.aronmorris.karanprojects;

/**
 * Defines the methods used to set ID3v2 tags
 * @author Aron
 *
 */
public interface IDFrameable extends IDTaggable {

	public String getComposer();
	public void setComposer(String composer);
	
	public String getPublisher();
	public void setPublisher(String publisher);
	
	public String getOriginalArtist();
	public void setOriginalArtist(String artist);
	
	public String getAlbumArtist();
	public void setAlbumArtist(String artist);
	
	public String getCopyright();
	public void setCopyright(String copyright);
	
	public String getUrl();
	public void setUrl(String url);
	
	public String getEncoder();
	public void setEncoder(String encoder);
	
	public byte[] getAlbumImageData();
	public void setAlbumImageData(byte[] imageData);
	
	
}
