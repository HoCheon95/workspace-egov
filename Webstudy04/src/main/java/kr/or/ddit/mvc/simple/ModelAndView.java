package kr.or.ddit.mvc.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * 낱개의 요청을 처리하는 컨트롤러에서 결정된 Model 과 logical view name 을 모두 가진 객체
 */
public class ModelAndView {
    private Map<String, Object> model = new HashMap<>();
    private String viewName;

    public void addAttribute(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return viewName;
    }
}
