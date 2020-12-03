package org.saleslist.web;

import org.saleslist.model.AbstractBaseEntity;
import org.saleslist.repository.jdbc.JdbcMainRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.saleslist.web.SecurityUtil.getAuthUserId;

public abstract class MainServlet<T extends AbstractBaseEntity> extends HttpServlet {

    protected JdbcMainRepository<T> repository;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        T t = fillModel(request);

        if (!StringUtils.isEmpty(request.getParameter("id"))) {
            int modelId = getId(request);
            t.setId(modelId);
        }

        repository.save(t, getAuthUserId());

        response.sendRedirect(getTableName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        String action = request.getParameter("action");
    }

    protected static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    abstract protected T fillModel(HttpServletRequest request);

    abstract protected String getTableName();
}
