package kr.or.ddit.mvc.annotation.stereotype;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import kr.or.ddit.di.stereotype.Component;

@Target(TYPE)
@Retention(RUNTIME)
@Component
public @interface Controller {

}