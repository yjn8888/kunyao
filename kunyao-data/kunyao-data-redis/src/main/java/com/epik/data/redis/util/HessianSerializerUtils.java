/**
 * 
 */   
package com.epik.data.redis.util;   

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

/**  
* 创建时间：2014年7月16日 下午4:27:33  
* 项目名称：redis  
* @author yangjuanying  
* @version 1.0   
* @since JDK 1.6.0_21  <br/>
* 文件名称：HessianSerializerUtils.java  <br/>
* 类说明：  hessian序列化工具，比jdk自带的序列化速度快
*/
public class HessianSerializerUtils {
    
    private static final SerializerFactory DEFAULT_SERIALIZER_FACTORY = new SerializerFactory();
    
    public static Serializable deSerialize(byte bytes[]){
        ByteArrayInputStream is = null;
        Hessian2Input hi = null;
        Serializable serializable = null;
        try {
            if (bytes == null){
                return serializable;
            }
            is = new ByteArrayInputStream(bytes);
            hi = new Hessian2Input(is);
            hi.setSerializerFactory(DEFAULT_SERIALIZER_FACTORY);
            Object target = hi.readObject();
            serializable = (Serializable) target;
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            try{
                if(hi != null){
                    hi.close();
                }
                if(is != null){
                    is.close();
                }  
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return serializable;
    }

    public static byte[] serialize(Serializable target) {
        ByteArrayOutputStream os = null;
        Hessian2Output ho = null;
        byte arrayOfByte[] = null;
        try {
            os = new ByteArrayOutputStream();
            ho = new Hessian2Output(os);
            ho.setSerializerFactory(DEFAULT_SERIALIZER_FACTORY);
            ho.writeObject(target);
            ho.flush();
            arrayOfByte = os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ho != null) {
                    ho.close();
                }
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayOfByte;
    }
}
  