package com.blocksports.assignment.util;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.util.Collection;

public class ObjectUtil {

    private ObjectUtil() {

    }

    public static boolean isStringEmptyOrNull(String string) {
        return string == null || string.isEmpty() || string.equalsIgnoreCase(" ") || string.trim().equalsIgnoreCase(" ") || string.trim().equalsIgnoreCase("");
    }

    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    public static <X> X getNonProxyObj(X model) {
        try {
            Hibernate.initialize(model);
            if (model instanceof HibernateProxy) {
                return (X) ((HibernateProxy) model).getHibernateLazyInitializer().getImplementation();
            }
            return model;
        } catch (Exception e) {
            return model;
        }
    }


    public static boolean isNullOrEmpty(Collection obj) {

        return null == obj || obj.isEmpty();
    }

}

