package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class ID3v2Handler extends ID3v1Handler implements IDFrameable {

	ID3v2 tag = null;
	
	public ID3v2Handler (IDTaggable taggable) {
		this(taggable.getMp3File());
	}
	
	protected ID3v2Handler(Mp3File mp3) {
		
		super(mp3);
		
		if (mp3.hasId3v2Tag()) {
			tag = mp3.getId3v2Tag();
		}
	}

	@Override
	public String getComment() {
		return tag.getComment();
	}

	@Override
	public void setComment(String comment) {
		tag.setComment(comment);
	}

	@Override
	public String getComposer() {
		return tag.getComposer();
	}

	@Override
	public void setComposer(String composer) {
		tag.setComposer(composer);
	}

	@Override
	public String getPublisher() {
		return tag.getPublisher();
	}

	@Override
	public void setPublisher(String publisher) {
		tag.setPublisher(publisher);
		
	}

	@Override
	public String getOriginalArtist() {
		return tag.getOriginalArtist();
	}

	@Override
	public void setOriginalArtist(String artist) {
		tag.setOriginalArtist(artist);
	}

	@Override
	public String getAlbumArtist() {
		return tag.getAlbumArtist();
	}

	@Override
	public void setAlbumArtist(String artist) {
		tag.setAlbumArtist(artist);
	}

	@Override
	public String getCopyright() {
		return tag.getCopyright();
	}

	@Override
	public void setCopyright(String copyright) {
		tag.setCopyright(copyright);
	}

	@Override
	public String getUrl() {
		return tag.getUrl();
	}

	@Override
	public void setUrl(String url) {
		tag.setUrl(url);
	}

	@Override
	public String getEncoder() {
		return tag.getEncoder();
	}

	@Override
	public void setEncoder(String encoder) {
		tag.setEncoder(encoder);
	}

	@Override
	public byte[] getAlbumImageData() {
		return tag.getAlbumImage();
	}

	@Override
	public void setAlbumImageData(byte[] imageData) {
		tag.setAlbumImage(imageData, tag.getAlbumImageMimeType());
		
	}

	
}
