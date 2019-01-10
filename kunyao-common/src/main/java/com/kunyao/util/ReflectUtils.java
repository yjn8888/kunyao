package com.kunyao.util;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 反射工具类
 * @author
 */
public final class ReflectUtils {
	
	/**
	 * 得到没有访问权限属性的值
	 * @param obj 
	 * @param fieldname
	 * @return
	 * @throws Exception
	 */
	public static <T> T getFieldValue(Object obj, String fieldname) throws Exception{
		Class<?> clazz = obj.getClass();
		Field field = clazz.getField(fieldname);
		field.setAccessible(true);
		return (T)(field.get(obj));
	}
	
	/**
	 * 判断此属性是否是静态
	 * @param field
	 * @return
	 */
	public static boolean isStaticField(Field field){
		return Modifier.isStatic(field.getModifiers());
	}
	
	/**
	 * 得到setter名称
	 * @param field
	 * @return
	 */
	public static String getSetterName(Field field){
		String fieldName = field.getName();
		return "set" + fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
	}
	

	/**
	 * 反射调用target方法名为methodName参数列表为paramters的方法并返回调用结果
	 * @param target
	 * @param methodName
	 * @param paramters
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static <T> T invokeMethod(Object target,String methodName,Object... paramters) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class<?> targetClass = target.getClass();
		Class<?>[] paramterTypes = new Class<?>[paramters.length];
		for (int i = 0; i < paramters.length; i++) {
			paramterTypes[i] = paramters[i].getClass();
		}
		Method method = targetClass.getMethod(methodName,paramterTypes);
		return (T)method.invoke(target, paramters);
	}
	
	/**
	 * 根据方法名得到参数类型列表（方法不能有重载）
	 * @param target
	 * @param methodName
	 * @return
	 */
	public Class<?>[] getMethodParamterTypes(Object target,String methodName){
		Class<?>[] parameterTypes = null;
		Class<?> clazz = target.getClass();
		Method[] methods = clazz.getMethods(); 
		for(Method method : methods) { 
			String name = method.getName(); 
			if(name.equals(methodName)) { 
				parameterTypes = method.getParameterTypes();
				return parameterTypes;
			}
		}
		return parameterTypes;
	}
}
