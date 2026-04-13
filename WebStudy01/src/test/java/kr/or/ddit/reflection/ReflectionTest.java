package kr.or.ddit.reflection;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.text.CaseUtils;
import org.junit.jupiter.api.Test;

import kr.or.ddit.member.dto.MemberDto;

public class ReflectionTest {

    @Test
    void test5() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
        Map<String, String> paramerterMap = new LinkedHashMap<>();
        paramerterMap.put("MEM_ID", "a001");
        paramerterMap.put("MEM_PASS", "java");
        paramerterMap.put("MEM_MILEAGE", "2000");
        paramerterMap.put("MEM_BIR", LocalDate.now().toString());

        // 데이터를 옮겨 담을 대상인 MemberDto의 설계도 정보를 가져온다.
        Class<?> resultType = MemberDto.class;
        // instance 생성 후 dataMap, MemberDto 일치하는 값만 입력하기
        Object newInstance = resultType.getDeclaredConstructor().newInstance();
        for(String underScore : paramerterMap.keySet()){
            String camelCase = CaseUtils.toCamelCase(underScore, false, '_');
            PropertyDescriptor pd = new PropertyDescriptor(camelCase, resultType);
            Method setter = pd.getWriteMethod();
            Class<?> propertyType = pd.getPropertyType();
            if(propertyType.equals(Integer.class)){
                Integer value = new Integer(paramerterMap.get(underScore));
                setter.invoke(newInstance, value);
            } else if(propertyType.equals(LocalDate.class)){
                LocalDate value = LocalDate.parse(paramerterMap.get(underScore));
                setter.invoke(newInstance, value);
            } else {
                setter.invoke(newInstance, paramerterMap.get(underScore));
            }
        }
        System.out.println(newInstance);
    }

    @Test
    void test4() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
        Map<String, String> paramerterMap = new LinkedHashMap<>();
        paramerterMap.put("memId", "a001");
        paramerterMap.put("memPass", "java");
        paramerterMap.put("memMileage", "2000");
        paramerterMap.put("memBir", LocalDate.now().toString());

        // 데이터를 옮겨 담을 대상인 MemberDto의 설계도 정보를 가져온다.
        Class<?> resultType = MemberDto.class;

        // 리플렉션을 이용해 MemberDto의 빈 객체(인스턴스)를 생성한다.
        Object newInstance = resultType.getDeclaredConstructor().newInstance();

        // Map에 담긴 데이터의 이름(Key)들을 하나씩 꺼내어 반복한다.
        for(String camelCase : paramerterMap.keySet()){
            // 변수명과 설계도를 대조하여 해당 속성의 상세 정보(PropertyDescriptor)를 파악한다.
            PropertyDescriptor pd = new PropertyDescriptor(camelCase, resultType);

            // 값을 세팅할 때 사용할 메서드(Setter)를 추출한다
            Method setter = pd.getWriteMethod();

            // 해당 속성이 어떤 데이터 타입(Integer, LocalDate 등)인지 확인한다.
            Class<?> propertyType = pd.getPropertyType();

            // 타입에 따른 형변환 로직을 수행한다
            if(propertyType.equals(Integer.class)){
                // 속성 타입이 정수형이면 문자열을 숫자로 변환하여 주입한다
                Integer value = new Integer(paramerterMap.get(camelCase));
                setter.invoke(newInstance, value);
            } else if(propertyType.equals(LocalDate.class)){
                // 속성 타입이 날짜형이면 문자열을 날짜 객체로 변환하여 주입한다
                LocalDate value = LocalDate.parse(paramerterMap.get(camelCase));
                setter.invoke(newInstance, value);
            } else {
                // 그 외(문자열 등)의 경우는 변환 없이 그대로 값을 주입한다
                setter.invoke(newInstance, paramerterMap.get(camelCase));
            }
        }
        // 최종적으로 데이터가 타입에 맞게 잘 들어갔는지 출력하여 확인한다
        System.out.println(newInstance);
    }
    
    @Test
    void test3() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
        Map<String, Object> dataMap = new LinkedHashMap<>();
        // 데이터를 담고 있는 바구니(Map)를 생성한다.
        dataMap.put("memId", "a001");
        dataMap.put("memPass", "java");
        dataMap.put("memMileage", 2000);
        dataMap.put("memBir", LocalDate.now());

        // 어떤 종류의 객체를 만들지 설계도(Class)를 지정한다.
        Class<?> resultType = MemberDto.class;

        // 설계도에 정의된 기본 생성자를 찾아 실게 객체(Instance)를 생성한다.
        Object newInstance = resultType.getDeclaredConstructor().newInstance();

        // Map에 들어있는 데이터의 이름(Key)들을 하나씩 꺼내어 반복한다.
        for(String camelCase : dataMap.keySet()){
            // 변수명(calmelCase)을 바탕으로 해당 클래스의 속성 정보(PropertyDescriptor)를 추출한다.
            PropertyDescriptor pd = new PropertyDescriptor(camelCase, resultType);

            // 해당 속성값을 변경할 수 있는 도구인 Setter 메서드를 가져온다.
            Method setter = pd.getWriteMethod();

            // 생성된 객체의 Setter를 실행하여 Map에서 꺼낸 실제 값을 주입한다.
            setter.invoke(newInstance, dataMap.get(camelCase));
        }
        System.out.println(newInstance);
    }

    @Test
    void test2() {
        Object original = DummyFramWork.getObject();
        Class<?> objType = original.getClass();
        try {
            Constructor<?> defaultConstructor = objType.getConstructor();
            try {
                Object newInstance = defaultConstructor.newInstance();
                System.out.println(newInstance);
                try {
                    BeanInfo beanInfo = Introspector.getBeanInfo(objType);
                    PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                    for(PropertyDescriptor pd : propertyDescriptors){
                        Method getter = pd.getReadMethod();
                        Method setter = pd.getWriteMethod();
                        if(setter==null) continue;
                        // System.out.println(getter.getName()+", "+setter);
                        Object value = getter.invoke(original);
                        setter.invoke(newInstance, value);
                    }
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                System.out.println(newInstance);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    void test1() {
        Object obj = DummyFramWork.getObject();
        Class<?> objType = obj.getClass();
        System.out.println(objType);
        Constructor<?>[] constructors = objType.getConstructors();
        Arrays.stream(constructors).forEach(System.out::println);
        Field[] fields = objType.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if(value!=null){
                    field.set(obj, value+"modified");
                    value = field.get(obj);
                }
                System.out.printf("%s = %s\n", field.getName(), value);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        Method[] methods = objType.getDeclaredMethods();
        Arrays.stream(methods).forEach(System.out::println);

        System.out.println("==================>"+obj.toString());
    }
    
}
