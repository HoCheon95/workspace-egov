package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ClassUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.or.ddit.mvc.Model;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.stereotype.ModelAttribute;
import kr.or.ddit.mvc.annotation.stereotype.Validated;
import kr.or.ddit.mvc.exception.CommandObjectBindException;
import kr.or.ddit.utils.PopulateUtils;
import kr.or.ddit.validate.ValidateUtils;

public class ModelAttributeMethodProcessor implements HandlerMethodArgumentResolver {

    @Override
    public Boolean supportParameter(Parameter parameter) {
        ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
        Class<?> parameterType = parameter.getType();
        return annotation != null && !(String.class.isAssignableFrom(parameterType)
                || ClassUtils.isPrimitiveOrWrapper(parameterType));
    }

    @Override
    public Object resolveArgument(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
        Validated validateAnnotation = parameter.getAnnotation(Validated.class);

        Class<?> parameterType = parameter.getType();
        String attributeName = annotation.value();
        Model model = (Model) req.getAttribute(RequestMappingHandlerAdapter.MODELATTRIBUTENAME);
        
        Map<String, String[]> parameterMap = req.getParameterMap();
        Object commandObject = PopulateUtils.populate(parameterMap, parameterType);
        model.addAttribute(attributeName, commandObject);
        
        Class<?>[] groupHint = Optional.ofNullable(validateAnnotation)
                                    .map(va->va.value())
                                    .orElse(null);
        
        Map<String, List<String>> errors = null;
        if(groupHint != null) {
            errors = ValidateUtils.validate(commandObject, groupHint);
        } else {
            errors = ValidateUtils.validate(commandObject);
        }

        model.addAttribute("errors", errors);
        if(!errors.isEmpty()) {
            throw new CommandObjectBindException(commandObject, errors);
        }

        return commandObject;
    }
}
