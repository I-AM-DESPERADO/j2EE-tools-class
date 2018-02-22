package com.trace.app.framework.utils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by MEX on 2015/3/27.
 */
public class MySessionContext {
    private static HashMap myMap = new HashMap();

    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            myMap.put(session.getId(), session);
        }
    }

    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            myMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null) {
            return null;
        }
        return (HttpSession) myMap.get(session_id);
    }

}
