package org.saleslist.web;

import org.saleslist.model.AbstractBaseEntity;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public abstract class MainServlet<T extends AbstractBaseEntity> extends HttpServlet {

    protected static int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    abstract protected T fillModel(HttpServletRequest request);

    abstract protected String getTableName();
}
