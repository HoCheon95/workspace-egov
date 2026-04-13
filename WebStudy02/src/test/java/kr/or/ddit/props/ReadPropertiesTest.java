package kr.or.ddit.props;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.junit.jupiter.api.Test;

import kr.or.ddit.IndexServlet;

public class ReadPropertiesTest {

    @Test
    void testCase3ReadMessageBundle(){
        String baseName = "kr.or.ddit.Messages";
        ResourceBundle messageBundle = ResourceBundle.getBundle(baseName, Locale.KOREAN);
        System.out.println(messageBundle.getString("hi"));
    }


    @Test
    void testCase2ResourceBundle(){
        String baseName = "kr.or.ddit.SecuredResources";
        ResourceBundle bundle = ResourceBundle.getBundle(baseName);
        Map<String, List<String>> securedResources = new LinkedHashMap<>();
        Enumeration<String> keys = bundle.getKeys();
        while (keys.hasMoreElements()) {
            String code = (String) keys.nextElement();
            String message = bundle.getString(code);
            List<String> roles = Arrays.asList(message.split(","));
            securedResources.put(code, roles);
        }
        System.out.println(securedResources);
    }

    @Test
    void testCase1Properties(){
        String qName = "/kr/or/ddit/SecuredResources.properties";
        
        Properties properties = new Properties();
        Map<String, List<String>> securedResources = new LinkedHashMap<>();
        try(
            InputStream is =IndexServlet.class.getResourceAsStream(qName);
        ){
            properties.load(is);
            System.out.println(properties.size());
            properties.forEach((k,v)->{
                String url = k.toString();
                List<String> roles = Arrays.asList(v.toString().split(","));
                securedResources.put(url, roles);
            });
            System.out.println(securedResources);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    
}
