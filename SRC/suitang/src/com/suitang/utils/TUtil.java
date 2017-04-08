package com.suitang.utils;

import java.lang.reflect.ParameterizedType;


public class TUtil {
	public static Class getActualType(Class entity){
		ParameterizedType type = (ParameterizedType)entity.getGenericSuperclass();
		Class entityClass = (Class)type.getActualTypeArguments()[0];
		return entityClass;
	}
}
