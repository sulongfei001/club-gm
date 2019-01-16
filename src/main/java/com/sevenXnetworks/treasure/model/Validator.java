package com.sevenXnetworks.treasure.model;


import org.apache.commons.lang.Validate;

import java.util.Collection;

public class Validator extends Validate {


    public static void isTrue(boolean expression, RuntimeException re) {
        if (expression == false) {
            throw re;
        }
    }

    public static void notTrue(boolean expression, RuntimeException re) {
        if (expression == true) {
            throw re;
        }
    }


    public static void notEmpty(String str, RuntimeException re) {
        if (str == null || str.isEmpty()) {
            throw re;
        }
    }


    public static void notEmpty(Collection collection, RuntimeException re) {
        if (collection == null || collection.isEmpty()) {
            throw re;
        }
    }


    public static void notNull(Object obj, RuntimeException re) {
        if (obj == null) {
            throw re;
        }
    }


    public static void isNull(Object obj, RuntimeException re) {
        if (obj != null) {
            throw re;
        }
    }


    public static void isZero(int i, RuntimeException re) {
        if (i != 0) {
            throw re;
        }
    }

}
