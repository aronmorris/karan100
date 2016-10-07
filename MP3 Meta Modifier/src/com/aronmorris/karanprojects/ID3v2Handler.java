package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class ID3v2Handler extends ID3v1Handler implements IDFrameable {

	ID3v2 tag = null;
	
	protected ID3v2Handler(Mp3File mp3) {
		
		super(mp3);
		
		if (mp3.hasId3v2Tag()) {
			tag = mp3.getId3v2Tag();
		}
	}

	@Override
	public String getComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComment(String comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getComposer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setComposer(String composer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPublisher() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPublisher(String publisher) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getOriginalArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOriginalArtist(String artist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAlbumArtist() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAlbumArtist(String artist) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCopyright() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCopyright(String copyright) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUrl(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEncoder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEncoder(String encoder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getAlbumImageData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAlbumImageData(byte[] imageData) {
		// TODO Auto-generated method stub
		
	}

	
}
