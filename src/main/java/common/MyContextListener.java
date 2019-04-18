package common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sv) {
        ServletContext servletContext = sv.getServletContext();
        String path=servletContext.getContextPath();
        servletContext.setAttribute("PATH", path);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
