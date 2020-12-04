package org.saleslist.web;

import org.saleslist.model.AbstractBaseEntity;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public abstract class MainServlet<T extends AbstractBaseEntity> extends HttpServlet {

    protected ConfigurableApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/spring-db.xml");
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    protected static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    abstract protected T fillModel(HttpServletRequest request);

    abstract protected String getTableName();
}
