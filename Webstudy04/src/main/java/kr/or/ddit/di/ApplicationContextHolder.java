package kr.or.ddit.di;

public class ApplicationContextHolder {
    private static ApplicationContext context;
    public static void setContext(ApplicationContext context) {
        ApplicationContextHolder.context = context;
    }
    public static ApplicationContext getContext() {
        return context;
    }
}
