package com.reddoor.framework.dao.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.jdbc.SQL;
 
public class BaseSqlProvider<T> {
 
	@Options
	public String add(T bean) {
 
		SQL sql = new SQL();
 
		Class clazz = bean.getClass();
 
		String tableName = clazz.getSimpleName();
		
		String realTableName = Tool.humpToLine(tableName).replaceAll("_entity", "").substring(1);
		sql.INSERT_INTO(realTableName);
 
		List<Field> fields = getFields(clazz);
		for (Field field : fields) {
 
			field.setAccessible(true);
 
			String column = field.getName();
 
			System.out.println("column:" + Tool.humpToLine(column));
 
			sql.VALUES(Tool.humpToLine(column), String.format("#{" + column + ",jdbcType=VARCHAR}"));
 
		}
 
		return sql.toString();
	}
 
	public String delete(T bean) {
 
		SQL sql = new SQL();
 
		Class clazz = bean.getClass();
 
		String tableName = clazz.getSimpleName();
 
		String realTableName = Tool.humpToLine(tableName).replaceAll("_entity", "").substring(1);
		sql.DELETE_FROM(realTableName);
 
		List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
 
		if (!primaryKeyField.isEmpty()) {
 
			for (Field pkField : primaryKeyField) {
				pkField.setAccessible(true);
				sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
			}
 
		} else {
 
			sql.WHERE(" 1= 2");
 
			throw new RuntimeException("对象中未包含PrimaryKey属性");
		}
 
		return sql.toString();
	}
 
	private List<Field> getPrimarkKeyFields(Class clazz) {
 
		List<Field> primaryKeyField = new ArrayList<Field>();
		List<Field> fields = getFields(clazz);
		for (Field field : fields) {
			field.setAccessible(true);
			PrimaryKey key = field.getAnnotation(PrimaryKey.class);
			if (key != null) {
				primaryKeyField.add(field);
			}
 
		}
		return primaryKeyField;
	}
 
	private List<Field> getFields(Class clazz) {
 
		List<Field> fieldList = new ArrayList<Field>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Exclude key = field.getAnnotation(Exclude.class);
			if (key == null) {
				fieldList.add(field);
			}
 
		}
		return fieldList;
	}
 
	public String get(T bean) {
 
		SQL sql = new SQL();
 
		Class clazz = bean.getClass();
 
		String tableName = clazz.getSimpleName();
 
		String realTableName = Tool.humpToLine(tableName).replaceAll("_entity", "").substring(1);
		sql.SELECT("*").FROM(realTableName);
 
		List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
 
		if (!primaryKeyField.isEmpty()) {
 
			for (Field pkField : primaryKeyField) {
				pkField.setAccessible(true);
				sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
				
			}
		} else {
 
			sql.WHERE(" 1= 2");
 
			throw new RuntimeException("对象中未包含PrimaryKey属性");
		}
		System.out.println("getSql:"+sql.toString());
		return sql.toString();
	}
 
	public String update(T bean) {
 
		SQL sql = new SQL();
 
		Class clazz = bean.getClass();
 
		String tableName = clazz.getSimpleName();
 
		String realTableName = Tool.humpToLine(tableName).replaceAll("_entity", "").substring(1);
		sql.UPDATE(realTableName);
 
		List<Field> fields = getFields(clazz);
		for (Field field : fields) {
 
			field.setAccessible(true);
 
			String column = field.getName();
 
			if (column.equals("id")) {
				continue;
			}
 
			System.out.println(Tool.humpToLine(column));
 
			sql.SET(Tool.humpToLine(column) + "=" + String.format("#{" + column + ",jdbcType=VARCHAR}"));
		}
 
		List<Field> primaryKeyField = getPrimarkKeyFields(clazz);
 
		if (!primaryKeyField.isEmpty()) {
 
			for (Field pkField : primaryKeyField) {
				pkField.setAccessible(true);
				sql.WHERE(pkField.getName() + "=" + String.format("#{" + pkField.getName() + "}"));
			}
 
		} else {
 
			sql.WHERE(" 1= 2");
 
			throw new RuntimeException("对象中未包含PrimaryKey属性");
		}
		System.out.println("updateSql:"+sql.toString());
		return sql.toString();
 
	}
 
}