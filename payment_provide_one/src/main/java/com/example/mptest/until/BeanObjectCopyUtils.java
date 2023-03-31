package com.example.mptest.until;

import java.util.ArrayList;
import java.util.List;

public class BeanObjectCopyUtils {
    private BeanObjectCopyUtils() {}

    public static <T, S> T copyObject(T desObj, S origObj) {
        if (origObj != null && desObj != null) {
            try {
                org.springframework.cglib.beans.BeanCopier bc = org.springframework.cglib.beans.BeanCopier.create(origObj.getClass(), desObj.getClass(), false);
                bc.copy(origObj, desObj, null);
            } catch (Exception e) {
                throw new RuntimeException("object copy error", e);
            }
        }

        return desObj;
    }

    public static <T, S> List<T> copyList(Class<T> desClass, List<S> sourceList) {
        List<T> desList = new ArrayList<>();
        if (sourceList != null && sourceList.size() > 0) {
            try {
                org.springframework.cglib.beans.BeanCopier bc = org.springframework.cglib.beans.BeanCopier.create(sourceList.get(0).getClass(), desClass, false);
                for (S s : sourceList) {
                    T desObj = desClass.newInstance();
                    bc.copy(s, desObj, null);
                    desList.add(desObj);
                }
            } catch (Exception e) {
                throw new RuntimeException("list copy error", e);
            }
        }
        return desList;
    }
}
