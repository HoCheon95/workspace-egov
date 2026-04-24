package kr.or.ddit.mvc;

import java.util.LinkedHashMap;
import java.util.Map;

public class Model {
    private Map<String, Object> modelMap;

    public Model() {
        modelMap = new LinkedHashMap<>();
    }
    public void addAttribute(String attributeName, Object attributeValue) {
        modelMap.put(attributeName, attributeValue);
    }

    public Object getAttribute(String attributeName) {
        return modelMap.get(attributeName);
    }

    public boolean containsAttribute(String attributeName) {
        return modelMap.containsKey(attributeName);
    }

    public Map<String,Object> asMap() {
        return modelMap;
    }
}
