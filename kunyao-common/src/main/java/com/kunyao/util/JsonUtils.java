package com.kunyao.util;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



public final class JsonUtils {

	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	/**
	 * 将byte[]转化成以id, formNode的map
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static Map<String,JsonNode> change2FormNode(byte[] bytes) throws Exception{
		Map<String,JsonNode> formMap = new LinkedHashMap<String, JsonNode>();
		JsonNode root = objectMapper.readTree(bytes);
		Iterator<String> iterator = root.fieldNames();
		while(iterator.hasNext()){
		    String fieldName = iterator.next();
			JsonNode form = root.get(fieldName);
			formMap.put(fieldName, form);
		}
		return formMap;
	}
	
	/**
	 * 将json转化成以id, formNode的map
	 * @param json
	 * @return Map<String,JsonNode>
	 * @throws Exception
	 */
	public static Map<String,JsonNode> change2FormNode(String json) throws Exception{
		Map<String,JsonNode> formMap = new LinkedHashMap<String, JsonNode>();
		JsonNode root = objectMapper.readTree(json);
		Iterator<String> iterator = root.fieldNames();
		while(iterator.hasNext()){
		    String fieldName = iterator.next();
			JsonNode form = root.get(fieldName);
			formMap .put(fieldName, form);
		}
		return formMap;
	}
	
	/**
	 * 将json node转成object
	 * @param jsonNode
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object jsonNode2Object(JsonNode jsonNode,Class<?> clazz) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.treeToValue(jsonNode, clazz);
	}
	
	/**
	 * 将json转成object
	 * @param json
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Object json2Object(String json,Class<?> clazz) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(json, clazz);
	}
	
	/**
	 * 将json转成object
	 * @param json
	 * @param clazz
	 * @return List<?>
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<?> json2ListObject(String json,Class<?> clazz) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		JavaType javaType = getCollectionType(ArrayList.class, clazz); 
		List<?> lst =  (List<?>)mapper.readValue(json, javaType); 
		return lst;
	}
	
	private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	}  
	
	/**
     * 将Object转成json
     * @param obj
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public static String object2Json(Object obj) throws JsonParseException, JsonMappingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    
    /**
     * 指定的节点信息
     * TODO:(此方法的描述)
     * @category
     * @author: yuanjianing@hanhua.com
     * @since: 2015年6月26日
     * @param bytes
     * @return
     * @throws IOException 
     * @throws JsonProcessingException 
     */
    public static Map<String, JsonNode> change2FormNode(byte[] bytes,String...fieldNames) throws JsonProcessingException, IOException {
        Map<String,JsonNode> formMap = new LinkedHashMap<String, JsonNode>();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(bytes);
        for (String fieldName : fieldNames) {
            JsonNode field = root.get(fieldName);
            if(field!=null){
                formMap.put(fieldName, field);
            }
        }
        return formMap;
    }
    
    /**
     * 
     * TODO:从序列化byte[]得到相应的json节点，
     * @category
     * @author: yuanjianing@hanhua.com
     * @since: 2015年6月30日
     * @param bytes
     * @param fieldName
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static JsonNode getNode(byte[] bytes,String fieldName) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(bytes);
        return root.get(fieldName);
    }
    
    /**
     * 
     * TODO:从序列化json得到相应的json节点，
     * @author: yuanjianing@hanhua.com
     * @since: 2015年6月30日
     * @param jsonContent
     * @param fieldName
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
    public static JsonNode getNode(String jsonContent,String fieldName) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonContent);
        return root.get(fieldName);
    }
    
    /**
     * 
     * TODO:从json中取出一个节点的json
     * @author: yuanjianing@hanhua.com
     * @since: 2015年7月2日
     * @param jsonContent
     * @param fieldName
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     */
	public static String getNodeJson(String jsonContent,String fieldName) throws JsonProcessingException, IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonContent);
        JsonNode jsonNode = root.get(fieldName);
        if (jsonNode.isArray()) {
            return root.get(fieldName).toString();
        } else {
            return root.get(fieldName).asText();
        }
    }
	
}
