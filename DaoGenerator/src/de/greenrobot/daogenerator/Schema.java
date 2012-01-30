/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * This file is part of greenDAO Generator.
 * 
 * greenDAO Generator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * greenDAO Generator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with greenDAO Generator.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.greenrobot.daogenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The "root" model class to which you can add entities to.
 * 
 * @see <a href="http://greendao-orm.com/documentation/modelling-entities/">Modelling Entities (Documentation page)</a>
 */
public class Schema {
	private final int					version;
	private final String				defaultJavaPackage;
	private String						defaultJavaPackageDao;
	private String						defaultJavaPackageTest;
	private final List<Entity>			entities;
	private Map<PropertyType, String>	propertyToDbType;
	private Map<PropertyType, String>	propertyToJavaTypeNotNull;
	private Map<PropertyType, String>	propertyToJavaTypeNullable;
	private boolean						hasKeepSectionsByDefault;
	private boolean						useActiveEntitiesByDefault;
	private boolean						hasEasyDatastoreIntegration;

	public Schema(int version, String defaultJavaPackage) {
		this.version = version;
		this.defaultJavaPackage = defaultJavaPackage;
		this.entities = new ArrayList<Entity>();
		initTypeMappings();
	}

	public void enableKeepSectionsByDefault() {
		this.hasKeepSectionsByDefault = true;
	}

	public void enableActiveEntitiesByDefault() {
		this.useActiveEntitiesByDefault = true;
	}

	public void hasEasyDatastoreIntegration() {
		this.hasEasyDatastoreIntegration = true;
	}

	private void initTypeMappings() {
		this.propertyToDbType = new HashMap<PropertyType, String>();
		this.propertyToDbType.put(PropertyType.Boolean, "INTEGER");
		this.propertyToDbType.put(PropertyType.Byte, "INTEGER");
		this.propertyToDbType.put(PropertyType.Short, "INTEGER");
		this.propertyToDbType.put(PropertyType.Int, "INTEGER");
		this.propertyToDbType.put(PropertyType.Long, "INTEGER");
		this.propertyToDbType.put(PropertyType.Float, "REAL");
		this.propertyToDbType.put(PropertyType.Double, "REAL");
		this.propertyToDbType.put(PropertyType.String, "TEXT");
		this.propertyToDbType.put(PropertyType.ByteArray, "BLOB");
		this.propertyToDbType.put(PropertyType.Date, "INTEGER");

		this.propertyToJavaTypeNotNull = new HashMap<PropertyType, String>();
		this.propertyToJavaTypeNotNull.put(PropertyType.Boolean, "boolean");
		this.propertyToJavaTypeNotNull.put(PropertyType.Byte, "byte");
		this.propertyToJavaTypeNotNull.put(PropertyType.Short, "short");
		this.propertyToJavaTypeNotNull.put(PropertyType.Int, "int");
		this.propertyToJavaTypeNotNull.put(PropertyType.Long, "long");
		this.propertyToJavaTypeNotNull.put(PropertyType.Float, "float");
		this.propertyToJavaTypeNotNull.put(PropertyType.Double, "double");
		this.propertyToJavaTypeNotNull.put(PropertyType.String, "String");
		this.propertyToJavaTypeNotNull.put(PropertyType.ByteArray, "byte[]");
		this.propertyToJavaTypeNotNull.put(PropertyType.Date, "java.util.Date");

		this.propertyToJavaTypeNullable = new HashMap<PropertyType, String>();
		this.propertyToJavaTypeNullable.put(PropertyType.Boolean, "Boolean");
		this.propertyToJavaTypeNullable.put(PropertyType.Byte, "Byte");
		this.propertyToJavaTypeNullable.put(PropertyType.Short, "Short");
		this.propertyToJavaTypeNullable.put(PropertyType.Int, "Integer");
		this.propertyToJavaTypeNullable.put(PropertyType.Long, "Long");
		this.propertyToJavaTypeNullable.put(PropertyType.Float, "Float");
		this.propertyToJavaTypeNullable.put(PropertyType.Double, "Double");
		this.propertyToJavaTypeNullable.put(PropertyType.String, "String");
		this.propertyToJavaTypeNullable.put(PropertyType.ByteArray, "byte[]");
		this.propertyToJavaTypeNullable.put(PropertyType.Date, "java.util.Date");
	}

	/**
	 * Adds a new entity to the schema. There can be multiple entities per table, but only one may be the primary entity
	 * per table to create table scripts, etc.
	 */
	public Entity addEntity(String className) {
		Entity entity = new Entity(this, className);
		this.entities.add(entity);
		return entity;
	}

	/**
	 * Adds a new protocol buffers entity to the schema. There can be multiple entities per table, but only one may be
	 * the primary entity per table to create table scripts, etc.
	 */
	public Entity addProtobufEntity(String className) {
		Entity entity = addEntity(className);
		entity.useProtobuf();
		return entity;
	}

	public String mapToDbType(PropertyType propertyType) {
		return mapType(this.propertyToDbType, propertyType);
	}

	public String mapToJavaTypeNullable(PropertyType propertyType) {
		return mapType(this.propertyToJavaTypeNullable, propertyType);
	}

	public String mapToJavaTypeNotNull(PropertyType propertyType) {
		return mapType(this.propertyToJavaTypeNotNull, propertyType);
	}

	private String mapType(Map<PropertyType, String> map, PropertyType propertyType) {
		String dbType = map.get(propertyType);
		if (dbType == null) {
			throw new IllegalStateException("No mapping for " + propertyType);
		}
		return dbType;
	}

	public int getVersion() {
		return this.version;
	}

	public String getDefaultJavaPackage() {
		return this.defaultJavaPackage;
	}

	public String getDefaultJavaPackageDao() {
		return this.defaultJavaPackageDao;
	}

	public void setDefaultJavaPackageDao(String defaultJavaPackageDao) {
		this.defaultJavaPackageDao = defaultJavaPackageDao;
	}

	public String getDefaultJavaPackageTest() {
		return this.defaultJavaPackageTest;
	}

	public void setDefaultJavaPackageTest(String defaultJavaPackageTest) {
		this.defaultJavaPackageTest = defaultJavaPackageTest;
	}

	public List<Entity> getEntities() {
		return this.entities;
	}

	public boolean isHasKeepSectionsByDefault() {
		return this.hasKeepSectionsByDefault;
	}

	public boolean isUseActiveEntitiesByDefault() {
		return this.useActiveEntitiesByDefault;
	}

	public boolean isHasEasyDatastoreIntegration() {
		return this.hasEasyDatastoreIntegration;
	}

	void init2ndPass() {
		if (this.defaultJavaPackageDao == null) {
			this.defaultJavaPackageDao = this.defaultJavaPackage;
		}
		if (this.defaultJavaPackageTest == null) {
			this.defaultJavaPackageTest = this.defaultJavaPackageDao;
		}
		for (Entity entity : this.entities) {
			entity.init2ndPass();
		}
	}

	void init3ndPass() {
		for (Entity entity : this.entities) {
			entity.init3ndPass();
		}
	}

}
