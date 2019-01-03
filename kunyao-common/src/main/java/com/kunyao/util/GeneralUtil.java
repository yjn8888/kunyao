package com.kunyao.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @category 操作集合框架的工具集
 */
@Slf4j
public class GeneralUtil {

	/**
	 * <p>把传入类型转换为对应的对象<p>
	 * @param fvo 传入类型
	 * @return T 返回转化后的对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T castObject(Class<?> fvo) throws Exception{
    	T obj = null;
    	try {
			obj=(T) fvo.newInstance();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception("ERR:Can not cast object!");
		}
    	return obj;
    }
	
	@SuppressWarnings("unchecked")
	public static <T> T classNameToObject(String className) throws Exception{
		Class<?> c = Class.forName(className);
		return (T) c.newInstance();
	}
	
	/**
	 * <p>转化对象<p>
	 * @param object
	 * @return　V
	 */
	@SuppressWarnings("unchecked")
    public static <V> V cast(Object object) {
        return (V) object;
    }
	
	/**
	 * <p>转化为object数组类型</p>
	 * @param ls List<?>传入参数
	 * @return object[] 返回类型
	 */
	@SuppressWarnings("unchecked")
	public static <A> A[] listToArray(List<?> ls){
		return (A[]) ls.toArray();
	}
	
	public static <A> List<A> arrayToList(A a){
		return Arrays.asList(a);
	}
	
	/**
	 * <p>传入List<String>类型集合，转化为String[]数组类型</p>
	 * @param ls 传入集合参数
	 * @return String[]返回变量类型
	 */
	public static String[] listToString(List<?> ls){
		String[] as = ls.toArray(new String [ls.size()]);
		return as;
	}
	
	/**
	 * <p>判断当前对象T为空</p>
	 * @param t 传入对象类型
	 * @return 返回判断结果
	 */
	public static <T> boolean isEmptyObject(T t){
		if(null == t){
			return true;
		}else if(t instanceof String){
			String str = (String) t;
			return ((str == null) || (str.length() == 0));
		}else if(t instanceof Collection){
			Collection<?> collection=(Collection<?>) t;
			return ((collection == null) || (collection.isEmpty()));
		}else if(t instanceof Map){
			Map<?,?> map=(Map<?, ?>) t;
			return ((map == null) || (map.isEmpty()));
		}else if(t instanceof String){
			String str=(String) t;
			return StringUtils.isEmpty(str);
		}else if(t instanceof Object[]){
			Object[] obj=(Object[]) t;
			return ((obj == null) || (obj.length==0));
		}
		return false;
	}
	
	/**
	 * <p>判断当前对象T不为空</p>
	 * @param t 传入对象类型
	 * @return 返回判断结果
	 */
	public static <T> boolean isNotEmptyObject(T t){
		return (!isEmptyObject(t));
	}
	
	/**
	 * <p>判断容器是否为空，例如：list,collection等容器</p>
	 * @param t 传入参数，容器类型，例如：list,collection...
	 * @return
	 */
	public static <T> boolean isEmptyCollection(T t){
		Collection<?> collection = null;
		if(t instanceof Collection){
			collection=(Collection<?>) t;
			while(collection.contains(StringUtils.EMPTY)){
				collection.remove(StringUtils.EMPTY);
	    	}
		}
		return isEmptyObject(collection);
	}

	/**
	 * <p>判断容器为空，例如：list,collection等容器</p>
	 * @param t 传入参数，容器类型，例如：list,collection...
	 * @return
	 */
	public static <T> boolean isNotEmptyCollection(T t){
		return (!isEmptyCollection(t));
	}
	
	/**
	 * <p>去除掉容器中为空字符串中的值，返回不带空值的容器对象，例如：list,collection等容器</p>
	 * @param t 传入参数，容器类型，例如：list,collection...
	 * @return
	 */
	public static <T> Collection<?> getNotEmptyCollection(T t){
		Collection<?> collection = null;
		if(t instanceof Collection){
			collection=(Collection<?>) t;
			while(collection.contains(StringUtils.EMPTY)){
				collection.remove(StringUtils.EMPTY);
	    	}
		}
		return collection;
	}
	
	/**
	 * <p>根据类，获取类名信息</p>
	 * @param clz 传入的类
	 * @return 返回String类型的类名
	 */
	public static String getClassName(Class<?> clz){
		return clz.getSimpleName();
	}
	
	/**
	 * <p>传入Object对象，并检查是否是Collection相对应的类型</p>
	 * @param object 传入对象
	 * @param clz	传入对象的类型
	 * @return 返回Object转化对象的结果
	 */
	private static <C extends Collection<?>> C checkCollectionCast(Object object, Class<C> clz) {
        return clz.cast(object);
    }
	
	/**
	 * <p>传入Object对象，并检查是否是Collection类型</p>
	 * @param object 传入Object对象类型
	 * @return 返回Collection对象
	 */
	@SuppressWarnings("unchecked")
    public static <T> Collection<T> checkCollection(Object object) {
        return (Collection<T>) checkCollectionCast(object, Collection.class);
    }
	
	/**
	 * <p>传入Object对象，并检查是否是List类型</p>
	 * @param object 传入Object对象类型
	 * @return 返回List对象
	 */
	@SuppressWarnings("unchecked")
    public static <T> List<T> checkList(Object object) {
        return (List<T>) checkCollectionCast(object, List.class);
    }
	
	/**
	 * <p>传入Object对象，把Object对象，转化为List类型</p>
	 * @param object 传入Object对象类型
	 * @return 返回List对象
	 */
	@SuppressWarnings("unchecked")
    public static <T> List<T> toList(Object object) {
        if (object != null && !(object instanceof List)) 
        	return null;
        return (List<T>) object;
    }
	
	/**
	 * <p>传入Object对象，并检查是否是Map类型</p>
	 * @param object 传入Object对象类型
	 * @return 返回Map对象
	 */
	@SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> checkMap(Object object) {
        if (null !=object && !(object instanceof Map)) 
        	throw new ClassCastException("ERR:not a map");
        return (Map<K, V>) object;
    }
	
	/**
	 * <p>传入Object对象，把Object对象，转化为Map类型</p>
	 * @param object 传入Object对象类型
	 * @return 返回Map对象
	 */
	@SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toMap(Object object) {
        if (null !=object && !(object instanceof Map)) 
        	return null;
        return (Map<K, V>) object;
    }
	
	/**
	 * <p>用该方法来代替 new HashMap<K, V>()的实例对象</p>
	 * @return 返回 java.util.HashMap<K, V> 实现的新实例
	 */
	public static <K, V> Map<K, V> getMap(){
		return new HashMap<K, V>();
	}
	
	/**
	 * <p>用该方法来代替 new ConcurrentHashMap<K, V>()的实例对象</p>
	 * @return 返回 java.util.ConcurrentHashMap<K, V> 实现的新实例
	 */
	public static <K, V> Map<K, V> getConcurrentMap(){
		return new ConcurrentHashMap<K, V>();
	}
	
	/**
	 * <p>用该方法来代替 new LinkedHashMap<K, V>()的实例对象</p>
	 * @return 返回 java.util.LinkedHashMap<K, V> 实现的新实例
	 */
	public static <K, V> Map<K, V> getLinkHashMap(){
		return new LinkedHashMap<K, V>();
	}
	
	/**
	 * <p>用该方法来代替 new ArrayList<T>()的实例对象</p>
	 * @return 返回 java.util.ArrayList<T> 实现的新实例
	 */
	public static <T> List<T> getList(){
		return new ArrayList<T>();
	}
	
	/**
	 * <p>用该方法来代替 new ArrayList<T>()的实例对象</p>
	 * @param <T> List<T>中保存的对象
	 * @param c 其中的元素将存放在新的 list中的 collection
	 * @return 返回 List<T> 关于 java.util.ArrayList<T>实现的新实例
	 */
	public static <T> List<T> getList(Collection<? extends T> c){  
		if (isEmptyObject(c))  
            return new ArrayList<T>(c);  
        return new ArrayList<T>(); 
	}
	
	/**
	 * <p>用该方法来代替 new HashSet<T>()的实例对象</p>
	 * @return 返回 Set<T> 关于 java.util.HashSet<T>实现的新实例
	 */
	public static <T> Set<T> getHashSet(){
		return new HashSet<T>();
	}
	
	/**
	 * <p>用该方法来代替 new HashSet<T>()的实例对象</p>
	 * @param <T> Set<T>中保存的对象
	 * @param c 其中的元素将存放在新的 set中的 collection
	 * @return 返回 Set<T> 关于 java.util.HashSet<T>实现的新实例
	 */
	public static <T> Set<T> getHashSet(Collection<? extends T> c){
		if (isEmptyObject(c))  
            return new HashSet<T>();  
        return new HashSet<T>(c);
	}
	
	/**
	 * <p>用该方法来代替 new Queue<E>()的实例对象</p>
	 * @return 返回 Queue<E> 关于 java.util.LinkedList<T>实现的新实例
	 */
	public static <E> Queue<E> getQueue(){
		return new LinkedList<E>();
	}
	
	/**  
     * <p>合并两个有相同元素类型的 java.util.Set</p> 
     * <ul>  
     * <li>{@code setA == null && setB == null} --> 返回 {@link #getHashSet()}。</li>  
     * <li>{@code setA != null && setB == null} --> 返回 {@code setA}。</li>  
     * <li>{@code setA == null && setB != null} --> 返回 {@code setB}。</li>  
     * <li>{@code setA != null && setB != null} --> 返回 {@code setA} 和 {@code setB} 的并集。  
     * </li>  
     * </ul>  
     * @param <T> {@code Set} 中保存的对象。  
     * @param setA 第一个 Set。  
     * @param setB 第二个 Set。  
     * @return <font color=#000000>返回 setA 和 setB 的并集</font>
     */
	public static <T> Set<T> unionHashSet(Set<T> setA, Set<T> setB){
		boolean isEmptySetA = isEmptyObject(setA);  
        boolean isEmptySetB = isEmptyObject(setB);  
        if (isEmptySetA && isEmptySetB)  
            return getHashSet();  
        if (isEmptySetA && !isEmptySetB)  
            return setB;  
        if (!isEmptySetA && isEmptySetB)  
            return setA;  
        Set<T> result = getHashSet(setA);  
        result.addAll(setB);  
        return result;
	}

	/**  
     * <p>取两个有相同元素类型的 {@code java.util.Set} 的交集，即公共部份的新的 {@code java.util.Set}</p>
     * <ul>
     * <li>{@code setA == null && setB == null} --> 返回 {@code null}。</li>  
     * <li>{@code setA != null && setB == null} --> 返回 {@code null}。</li>  
     * <li>{@code setA == null && setB != null} --> 返回 {@code null}。</li>  
     * <li>{@code setA != null && setB != null} --> 返回 {@code setA} 和 {@code setB} 的交集。  
     * </li>  
     * </ul>  
     * @param <T> {@code Set} 中保存的对象。  
     * @param setA 第一个Set  
     * @param setB 第二个 Set
     * @return 返回 setA和 setB 的交集。  
     */  
    public static <T> Set<T> intersectHashSet(Set<T> setA, Set<T> setB) {  
        if (isEmptyObject(setA) || isEmptyObject(setB))  
            return null;  
        Set<T> result = getHashSet(setA);  
        result.retainAll(setB);  
        return result;  
    }  
    
    /**  
     * <p>移除 {@code setA} 中那些包含在 {@code setB} 中的元素</p><br />
     * 此方法不会修改 {@code setA}，只是复制一份作相应操作，返回的是全新的 {@code Set} 对象。  
     * <ul>  
     * <li>{@code setA == null} --> 返回 {@code null}。</li>  
     * <li>{@code setB == null} --> 返回 {@code setA}。</li>  
     * <li>{@code setA != null && setB != null} --> 返回 {@code setA} 和 {@code setB}  
     * 的不对称差集。</li>  
     * </ul>  
     * @param <T> {@code Set} 中保存的对象。  
     * @param setA 第一个 {@link java.util.Set}
     * @param setB 第二个 Set{@link java.util.Set}  
     * @return 返回 setA 和 setB的不对称差集。  
     */  
    public static <T> Set<T> differenceHashSet(Set<T> setA, Set<T> setB) {  
        if (isEmptyObject(setA))  
            return null;  
        if (isEmptyObject(setB))  
            return setA;  
        Set<T> result = getHashSet(setA);  
        result.removeAll(setB);  
        return result;  
    }  
  
    /**  
     * <p>取两个有相同元素类型的 {@code java.util.Set} 的补集</p>
     * @param <T> {@code Set} 中保存的对象。  
     * @param setA 第一个 {@code Set}。  
     * @param setB 第二个 {@code Set}。  
     * @return 返回 {@code setA} 和 {@code setB} 的补集。  
     */  
    public static <T> Set<T> complementHashSet(Set<T> setA, Set<T> setB) {  
        return differenceHashSet(unionHashSet(setA, setB), intersectHashSet(setA, setB));  
    }
    
	/**
	 * <p>由文件名，读取文件并写入流中</p>
	 * @param fileName 传入参数文件路径及名称
	 */
	public static void loadFile(String fileName){
		FileUtils.loadFile(fileName);
	}
	/**
	 * <p>根据读取文件，加载到内存中，并根据相应的Key取出对应的值</p>
	 * @param key 传入参数 key
	 * @return 返回value
	 * @throws IOException
	 */
	public static String getValueFromFile(String key) throws IOException{
		return FileUtils.getPropertiesMapValue(key);
	}
	
	/**
	 * <p>根据读取文件，加载到内存中，把数据读取到内存中，转化为List<String>类型</p>
	 * @return 返回List<String>类型
	 * @throws IOException
	 */
	public static List<String> getListFromFile() throws IOException{
		return FileUtils.fileToList();
	}
	
	/**
	 * <p>根据读取文件，加载到内存中，把数据读取到内存中，转化为String类型</p>
	 * @return 返回String类型
	 * @throws IOException
	 */
	public static String getStringFromFile() throws IOException{
		return FileUtils.fileToString();
	}
	/**
	 * <h1>加载文件后，返回流数据</h1>
	 * @return
	 */
	public static InputStream getInputStream(){
		return FileUtils.is;
	}
	
	/**
	 * <p></p>
	 * @param content
	 * @param output
	 * @throws IOException
	 */
	public static void writeStringToOS(String content,OutputStream output) throws IOException{
		FileUtils.writeStringToOS(content, output);
	}
	
	public static void writeStringToOS(String content,Writer write) throws IOException{
		FileUtils.writeStringToOS(content, write);
	}
	
	public static OutputStream getFileOutputStream(String fileName) throws FileNotFoundException{
		return new FileOutputStream(fileName);
	}
	
	/**
	 * <p>读取配置文件的公共类</p>
	 * @author liuwenlong
	 *
	 */
	private static class FileUtils{
		
		private static InputStream is = null;
		
		/**
		 * <p>加载文件，根据文件路径及文件名称</p>
		 * @param name 文件路径及名称
		 */
		public static void loadFile(String name){
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
		}
		
		/**
		 * <p>读取流到Properties文件中</p>
		 * @return
		 * @throws IOException
		 */
		private static Properties parseMapProperties() throws IOException{
			Properties prop = new Properties();
			try{
				prop.load(is);
			}finally{
				if(is!=null)
					is.close();
			}
			return prop;
		}
		/**
		 * <p>根据key，读取文件值</p>
		 * @param key 传入参数key
		 * @return 获取value值
		 * @throws IOException
		 */
		public static String getPropertiesMapValue(String key) throws IOException{
			String value=new String();
			Properties prop = parseMapProperties();		
			if(prop.containsKey(key)) {
			    value=new String(prop.getProperty(key).getBytes(CommonConstants.ISO_CHARSET),CommonConstants.UTF8_CHARSET);
			}
			if(log.isDebugEnabled()){
				printDebugLog(key,value);
			}
			return value;
		}
		
		/**
		 * <p>读取文件转化为List<String>类型</p>
		 * @return 返回list<String>
		 * @throws IOException
		 */
		public static List<String> fileToList() throws IOException{
			List<String> ls = null;
			try{
				ls=IOUtils.readLines(is, CommonConstants.UTF8_CHARSET);
			}finally{
				if(is!=null)
					is.close();
			}
			if(log.isDebugEnabled()){
				printDebugLog(ls.toString());
			}
			return ls;
		}
		
		/**
		 * <p>读取文件转化为String类型</p>
		 * @return 返回String类型值
		 * @throws IOException
		 */
		public static String fileToString() throws IOException{
			String fileToString = null;
			try{
				fileToString = IOUtils.toString(is, Charset.defaultCharset());
			}finally{
				if(is!=null)
					is.close();
			}
			if(log.isDebugEnabled()){
				printDebugLog(fileToString);
			}
			return fileToString;
		}
		
		public static void writeStringToOS(String content,OutputStream output) throws IOException{
			try {
				IOUtils.write(content, output, CommonConstants.UTF8_CHARSET);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}finally{
				if(output!=null)
					output.close();
			}
		}
		
		public static void writeStringToOS(String content,Writer write) throws IOException{
			try {
				IOUtils.write(content, write);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}finally{
				if(write!=null)
					write.close();
			}
		}
	}
	
	
	/**
	 * <p>判断当前启动模式，来显示调试并打印记录信息</p>
	 * @param strs
	 */
	protected static void printDebugLog(String... strs) {
		if (log.isDebugEnabled()) {
			for (String str : strs) {
				log.debug("Class:" + GeneralUtil.class + " parameter:" + str);
			}
		}
	}
	
	/**
     * 将String集合转成 'str1','str2','str3' 形式的字符串
     * @param coll
     * @return
     */
    public static  String arraytoSql(Collection<String> coll){
    	String result = StringUtils.EMPTY;
		StringBuilder costcenterStr = new StringBuilder(StringUtils.EMPTY);
		for(String str : coll){
			costcenterStr.append(",'").append(str).append("'");
		}if(!costcenterStr.toString().equals(StringUtils.EMPTY)){
			result = costcenterStr.substring(1);
		}
		return result;
	}
    
    public static String getClazzName(Class<?> c){
		if(c==null){
			return null;
		}
		return c.getCanonicalName();
	}
    
    public static <T> void iteratorMap(Map<String,T> map){
    	if(GeneralUtil.isEmptyObject(map)){
    		return;
    	}
    	for (Map.Entry<String, T> m : map.entrySet()) {
    		System.out.println("key:"+m.getKey() + "  value: "+m.getValue());
    		log.info("key:"+m.getKey() + "  value: "+m.getValue());
    	}
    }
    
    public static void iteratorList(List<?> ls){
    	if(GeneralUtil.isEmptyCollection(ls)){
    		return;
    	}
    	for(Object o:ls){
    		log.info("key:"+o);
    	}
    }
	
	public static boolean isChinese(String str){
		String regEx = "[\\u4e00-\\u9fa5]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find()) 
			return true;
		else
			return false;
	}

	public static boolean hasEnglish(String str){
		String regEx = "[a-zA-Z]+";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		if(m.find()) 
			return true;
		else
			return false;
	}
}