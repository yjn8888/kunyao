/**
 * 文件名：JSONUtil.java
 *
 * 版本信息：
 * 创建日期：2013-3-18
 @author: liuwenlong@hanhua.com
 * Copyright (c) 2012-2013 hanhua.com,Inc. All Rights Reserved.
 *
 */
package com.epik.util.json.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.epik.util.DateTimeUtil;
import com.epik.util.GeneralUtil;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * 
 * 此类描述的是：操作Json转换的工具类
 * @author: liuwenlong@hanhua.com
 * @version: 2013-3-18 下午8:56:13
 */
public class JSONUtil {

    private static final Log log = LogFactory.getLog(JSONUtil.class);
    
    private static final String FILTER_INDEX = "Filter";
    
    public static final String FILTEROUTALLEXCEPT = "filterOutAllExcept";
    
    public static final String SERIALIZEALLEXCEPT = "serializeAllExcept";

    private static ObjectMapper objectMapper;
    
    private JSONUtil() {
    }

    public static void initMapper(final BeanSerializerModifier beanSerializerModifier) {
        if (objectMapper != null)
            return;

        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.DATE_TIME_FORMAT));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        if (null != beanSerializerModifier) {
            objectMapper.registerModule(new SimpleModule() {

                /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void setupModule(SetupContext context) {
                    super.setupModule(context);

                    context.addBeanSerializerModifier(beanSerializerModifier);
                }
            });
        }
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }
    
    /**
     * 
     * 此方法描述的是：<h1>对象转换为json格式的数据结构<h1>
     * @author: liuwenlong@hanhua.com
     * @version: 2013-3-18 下午8:56:39
     * @param obj: 传入的对象，需要转换成json数据结构
     * @throws Exception 
     */
    public static <T> String toJson(T obj) throws Exception {
        return toJson(obj,new FilterConfigurations());
    }

    /**
     * <h1>对象转换为json格式的数据结构</h1>
     * TODO:(此方法的描述)
     * @category
     * @author: liuwenlong
     * @since: 2013-5-16
     * @param obj 传入的对象，需要转换成json数据结构
     * @param filterConfigurations 过滤对象
     * @return
     * @throws Exception 
     */
    public static <T> String toJson(T obj, FilterConfigurations filterConfigurations) throws Exception {
        String tempExcept = null;
        String json = null;
        if(obj==null){
            return null;
        }
        if(filterConfigurations==null){
            filterConfigurations = new FilterConfigurations();
        }
        Map<String,String> map = GeneralUtil.getMap();
        List<String> filterIds = new ArrayList<String>();
        JsonSimpleFilterProvider jsonSimpleFilterProvider = null;
        MapperSimpleFilterProvider mapperSimpleFilterProvider = null;
        try{
            Configuration[] configurations = filterConfigurations.getConfigurations();
            mapperSimpleFilterProvider = (MapperSimpleFilterProvider) JsonFilterConfig.pool.borrowObject();
            jsonSimpleFilterProvider = mapperSimpleFilterProvider.getJsonSimpleFilterProvider();
            ObjectMapper objectMapperAR = mapperSimpleFilterProvider.getObjectMapper();
            
            for (Configuration configuration : configurations) {
                if (configuration instanceof FilterInConfiguration) {
                    FilterInConfiguration filterInConfiguration = (FilterInConfiguration) configuration;
                    JsonFilter jsonFilter = (JsonFilter) filterInConfiguration.getClz().getAnnotation(JsonFilter.class);
                    if (jsonFilter != null) {
                        String[] newString = null;
                        if (filterInConfiguration.getClassProperties() != null
                                && filterInConfiguration.getClassProperties().length > 0) {
                            if(filterInConfiguration.getIgnoreAudit().booleanValue()){
                                newString=(String[]) ArrayUtils.addAll(filterInConfiguration.getClassProperties(), Configuration.ignoreAudit);
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }else{
                                newString = filterInConfiguration.getClassProperties();
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }
                        } else if (filterInConfiguration.getTargetClass() != null) {
                            if(filterInConfiguration.getIgnoreAudit().booleanValue()){
                                newString=(String[]) ArrayUtils.addAll(getProperties(filterInConfiguration.getTargetClass()), Configuration.ignoreAudit);
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }else{
                                newString = getProperties(filterInConfiguration.getTargetClass());
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }
                        }
                        jsonSimpleFilterProvider.addFilter(jsonFilter.value(), SimpleBeanPropertyFilter
                                .filterOutAllExcept(newString));
                        filterIds.add(jsonFilter.value());
                        tempExcept = FILTEROUTALLEXCEPT;
                        map.put(jsonFilter.value(), tempExcept);
                    }
                } else if (configuration instanceof FilterOutConfiguration) {
                    FilterOutConfiguration filterOutConfiguration = (FilterOutConfiguration) configuration;
                    JsonFilter jsonFilter = (JsonFilter) filterOutConfiguration.getClz()
                            .getAnnotation(JsonFilter.class);
                    if (jsonFilter != null) {
                        String[] newString = null;
                        if (filterOutConfiguration.getClassProperties() != null
                                && filterOutConfiguration.getClassProperties().length > 0) {
                            if(filterOutConfiguration.getIgnoreAudit().booleanValue()){
                                newString=(String[]) ArrayUtils.addAll(filterOutConfiguration.getClassProperties(), Configuration.ignoreAudit);
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }else{
                                newString = filterOutConfiguration.getClassProperties();
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }
                        } else if (filterOutConfiguration.getTargetClass() != null) {
                            if(filterOutConfiguration.getIgnoreAudit().booleanValue()){
                                newString=(String[]) ArrayUtils.addAll(getProperties(filterOutConfiguration.getTargetClass()), Configuration.ignoreAudit);
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }else{
                                newString = getProperties(filterOutConfiguration.getTargetClass());
                                newString=(String[]) ArrayUtils.addAll(newString, Configuration.jId);
                            }
                        }
                        jsonSimpleFilterProvider.addFilter(jsonFilter.value(), SimpleBeanPropertyFilter
                                .serializeAllExcept(newString));
                        filterIds.add(jsonFilter.value());
                        tempExcept = SERIALIZEALLEXCEPT;
                        map.put(jsonFilter.value(), tempExcept);
                    }
                }
            }
            json = objectMapperAR.writer(jsonSimpleFilterProvider).writeValueAsString(obj);
        }catch(Exception e){
            throw e;
        }finally{
            JsonFilterConfig.resetWith(filterIds, jsonSimpleFilterProvider,mapperSimpleFilterProvider,tempExcept,map);
            filterIds.clear();
        }
        return json;
    }

    /**
     * <h1>此方法描述的是：过滤对象中的属性，并序列化成Json数据结构</h1>
     * @param obj 传入要序列化的对象
     * @param propertyArray 过滤对象中的属性，并要转换为Json格式的数据结构
     * @return 返回Json格式的数据
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static <T> String toJson(T obj, String... propertyArray) throws IOException {
        Class<?> clasz = obj.getClass();
        JsonFilter jsonFilter = (JsonFilter) clasz.getAnnotation(JsonFilter.class);
        SimpleFilterProvider filters = null;
        String json = null;
        if (StringUtils.isNotEmpty(jsonFilter.value())) {
            filters = new SimpleFilterProvider().addFilter(jsonFilter.value(),
                    SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray));
            try {
                json = objectMapper.writer(filters).writeValueAsString(obj);
            } catch (JsonGenerationException e) {
                log.error(e.getMessage(), e);
            } catch (JsonMappingException e) {
                log.error(e.getMessage(), e);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw e;
            } finally {
                filters.removeFilter(jsonFilter.value());
            }
        }
        return json;
    }

    /**
     * <h1>此方法描述的是：过滤对象中的属性，并序列化成Json数据结构</h1>
     * @param obj 传入要序列化的对象
     * @param clasz 传入过滤目标类
     * @return
     * @throws IOException
     */
    public static <T> String toJson(T obj, Class<?> clasz) throws IOException {
        String[] fields = new String[clasz.getDeclaredFields().length];
        int i = 0;
        for (Field field : clasz.getDeclaredFields()) {
            fields[i] = field.getName();
            i++;
        }
        return toJson(obj, fields);
    }

    /**
     * <h1>给定一个class类型的类，获取类中声明的所有属性</h1>
     * TODO:(此方法的描述)
     * @category
     * @author: liuwenlong
     * @since: 2013-5-16
     * @param clasz
     * @return
     * @throws IOException
     */
    public static <T> String[] getProperties(Class<?> clasz) throws IOException {
        String[] fields = new String[clasz.getDeclaredFields().length];
        int i = 0;
        for (Field field : clasz.getDeclaredFields()) {
            fields[i] = field.getName();
            i++;
        }
        return fields;
    }

    /**
     * <h1>json格式的数据结构形式，转换为指定类型的实体</h1>
     * @param json
     * @param cls
     * @return
     * @throws IOException
     */
    public static <T> T fromJson(String json, Class<T> cls) throws IOException {
        try {
            return converJsonEntity(json, cls);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * <h1>流格式的数据结构转换为对象</h1>
     * @param is 输入要转换的输入流
     * @param obj 转换的对象类型
     * @return 返回需要转换对象的类型
     * @throws IOException
     */
    public static <T> T fromFileEntity(InputStream is, Class<T> obj) throws IOException {
        try {
            return objectMapper.readValue(is, obj);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * <h1>对象转换为json</h1>
     * @param obj
     * @return
     * @throws IOException
     */
    public static <T> String convertEntityToJson(T obj) throws IOException {
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return jsonString;
    }

    /**
     * <h1>字符串形式的json数据，转换为指定类型的实体</h1>
     * @param json
     * @param clz
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    private static <T> T converJsonEntity(String json, Class<T> clz) throws JsonParseException, JsonMappingException,
            IOException {
        T obj = objectMapper.readValue(json, clz);
        return obj;
    }

    /**
     * <h1>把json转换为Map对象</h1>
     * @param json json数据结构形式的对象
     * @return 返回Map对象，以键值对呈现
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> fromJsonMap(String json) throws IOException {
        Map<String, Object> map = null;
        try {
            map = converJsonEntity(json, Map.class);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return map;
    }

    /**
     * <h1>把json格式的文件，转换为指定类型的对象</h1>
     * @param json json数据结构形式的对象
     * @param cls 指定转换对象形式的类型
     * @return 返回T类型的对象，以键值对呈现
     * @throws IOException 
     */
    public static <T> T fromJsonEntity(String json, Class<T> cls) throws IOException {
        try {
            return converJsonEntity(json, cls);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * <h1>把json格式的字符串转换为List集合</h1>
     * @param json
     * @return
     * @throws IOException 
     */
    public static <T> Object[] fromJsonArray(String json) throws IOException {
        try {
            return converJsonEntity(json, Object[].class);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> fromJsonArray(String json, Class<T> cls) throws IOException {
        try {
            return (List<T>) converJsonEntity(json, cls);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    /**
     * <h1>字符串形式的json数据转换输入流程</h1>
     * @param json
     * @return
     * @throws IOException
     */
    public static InputStream fromJsonInputStream(String json) throws IOException {
        ByteArrayOutputStream os = null;
        InputStream is = null;
        try {
            os = new ByteArrayOutputStream(1024);
            objectMapper.writeValue(os, json);
            is = new ByteArrayInputStream(os.toByteArray());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            if (os != null) {
                os.close();
            }
        }
        return is;
    }

    /**
     * <h1>字符串形式的json数据转换输出流程</h1>
     * @param json
     * @return
     * @throws IOException
     */
    public static OutputStream fromJsonOutputStream(String json) throws IOException {
        OutputStream os = null;
        try {
            os = new ByteArrayOutputStream(1024);
            objectMapper.writeValue(os, json);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return os;
    }

    /**
     * <h1>把json格式的字符串转换为指定类型的List集合</h1>
     * @param json
     * @param cls
     * @return
     * @throws IOException 
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> fromJsonList(String json) throws IOException {
        try {
            return converJsonEntity(json, List.class);
        } catch (JsonParseException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JsonMappingException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    class JsonFilteringIntrospector extends JacksonAnnotationIntrospector{
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Object findFilterId(AnnotatedClass ac) {
            Object id = super.findFilterId(ac);
            if (id == null) {
              String name = ac.getName();
              if (name.indexOf(FILTER_INDEX) >= 0) {
                id = name;
              }
            }
            return id;
        }
    }
}
