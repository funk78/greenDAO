package com.droidastic.telljokes.db;

public interface EasyMapping {
	EasyMapping initFromMap(java.util.Map<String, Object> map);

	java.util.Map<String, Object> convertToMap();
}