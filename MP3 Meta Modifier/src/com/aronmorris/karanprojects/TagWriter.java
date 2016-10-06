package com.aronmorris.karanprojects;

import com.mpatric.mp3agic.Mp3File;

public class TagWriter {

	enum TagVersion {
		v1,
		v2,
		custom
	}
	
	private Mp3File mp3;
	
	private TagVersion tagType;
	
	public TagWriter(Mp3File mp3) {
		
		this.mp3 = mp3;
		
	}
	
	/**
	 * returns whether or not the file has the specified IDv3 tag type
	 * @param version {@link TagVersion}
	 * @return if the version exists in this mp3
	 */
	public boolean setIDVersion(TagVersion version) {
		
		if (mp3.hasId3v1Tag() && version == TagVersion.v1) {
			tagType = version;
			return true;
		}
		if (mp3.hasId3v2Tag() && version == TagVersion.v2) {
			tagType = version;
			return true;
		}
		if (mp3.hasCustomTag() && version == TagVersion.custom) {
			tagType = version;
			return true;
		}
		
		return false;
	}
	
	
	
}
