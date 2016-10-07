package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.Mp3File;

public class ID3v1Handler implements IDTaggable {

	protected Mp3File mp3;
	
	protected ID3v1 tag = null;
	
	protected ID3v1Handler() {
		
	}
	
	protected ID3v1Handler(Mp3File mp3) {
		this.mp3 = mp3;
		if (mp3.hasId3v1Tag()) {
			tag = mp3.getId3v1Tag();
		}
	}
	
	@Override
	public void setMp3File(Mp3File mp3) {
		this.mp3 = mp3;
	}
	
	@Override
	public Mp3File getMp3File() {
		return mp3;
	}
	
	@Override
	public String getTrack() {
		return tag.getTrack();
	}

	@Override
	public void setTrack(String track) {
		tag.setTrack(track);
	}

	@Override
	public String getArtist() {
		return tag.getArtist();
	}

	@Override
	public void setArtist(String artist) {
		tag.setArtist(artist);
		
	}

	@Override
	public String getTitle() {
		return tag.getTitle();
	}

	@Override
	public void setTitle(String title) {
		tag.setTitle(title);
	}

	@Override
	public String getAlbum() {
		return tag.getAlbum();
	}

	@Override
	public void setAlbum(String album) {
		tag.setAlbum(album);
	}

	@Override
	public String getYear() {
		return tag.getYear();
	}

	@Override
	public void setYear(String year) {
		tag.setYear(year);
	}

	@Override
	public String getGenre() {
		return Integer.toString(tag.getGenre());
	}

	@Override
	public void setGenre(int genre) {
		tag.setGenre(genre);
	}

	@Override
	public String getGenreDescription() {
		return tag.getGenreDescription();
	}

	@Override
	public String getComment() {
		return tag.getComment();
	}

	@Override
	public void setComment(String comment) {
		tag.setComment(comment);
		
	}

	

}
