/**
 * 
 */   
package com.kunyao.util;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
* @author
* @version 1.0   
* 文件名称：HessianSerializerUtils.java  <br/>
* 类说明：  hessian序列化工具，比jdk自带的序列化速度快
*/
@Slf4j
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
            log.error(e.getMessage(),e);
        } finally{
            closeIn(is);
            closeIn(hi);
        }
        return serializable;
    }

    public static byte[] serialize(Serializable target) {
        if(target==null){
            return (byte[])target;
        }
        if(target instanceof String){
            return ((String) target).getBytes(StandardCharsets.UTF_8);
        }
        if(target instanceof Byte[]){
            return ArrayUtils.toPrimitive((Byte[])target);
        }
        if(target instanceof byte[]){
            return (byte[])target;
        }
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
            log.error(e.getMessage(),e);
        } finally {
            closeOut(os);
            closeOut(ho);
        }
        return arrayOfByte;
    }

    private static void closeIn(InputStream in){
        try {
            if (in != null) {
                in.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    private static void closeOut(OutputStream out){
        try {
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    private static void closeOut(Hessian2Output out){
        try {
            if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    private static void closeIn(Hessian2Input hi){
        try {
            if (hi != null) {
                hi.close();
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

}
  