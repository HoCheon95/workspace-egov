package kr.or.ddit.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class PopulateUtils {
   
   static
   {
      Converter timeConverter = new Converter() {
         @Override
         public <T> T convert(Class<T> type, Object value) {
            if(value==null||value.toString().isEmpty()) return null;            
            if(!Temporal.class.isAssignableFrom(type)) {
               throw new IllegalArgumentException("현재 컨버터는 시간 데이터 변환만 지원함.");
            }
            
            if(!(value instanceof CharSequence)) {
               throw new IllegalArgumentException("현재 컨버터는 문자열 파싱만 지원함.");
            }
            
             String targetValue = value.toString();
             try {
               Method parseMth = type.getDeclaredMethod("parse", CharSequence.class);
               return (T) parseMth.invoke(null, targetValue);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
               throw new ConversionException(e);
            }
//             LocalDate.parse(targetValue);
//            return (T) LocalDateTime.parse(targetValue);
          }   
      };
      
      ConvertUtils.register(timeConverter, LocalDateTime.class);
      ConvertUtils.register(timeConverter, LocalDate.class);
   }
   
   public static <T> T populate(Map<String, String[]> parameterMap, Class<T> beanType) {
      try {
         T commandObject = beanType.getConstructor().newInstance();      
         BeanUtils.populate(commandObject, parameterMap);
         return commandObject;
      }catch(Exception e) {
         throw new RuntimeException(e);
      }
   }
}
