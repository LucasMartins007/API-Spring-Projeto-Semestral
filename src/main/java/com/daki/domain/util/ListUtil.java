package com.daki.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ListUtil {

    public synchronized static <E> E first(Collection<E> list) {
        if (isNotNullOrEmpty(list)) {
            return list.iterator().next();
        }

        return null;
    }

    public synchronized static <E> E last(Collection<E> list) {
        E retorno = null;

        if (isNotNullOrEmpty(list)) {
            Iterator<E> iterator = list.iterator();
            while (iterator.hasNext()) {
                retorno = iterator.next();
            }
        }

        return retorno;
    }

    public synchronized static boolean isNotNullOrEmpty(Collection<?> list) {
        return !isNullOrEmpty(list);
    }

    public synchronized static boolean isNotNullOrEmpty(Object[] array) {
        return !isNullOrEmpty(array);
    }

    public synchronized static boolean isNullOrEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public synchronized static boolean isNullOrEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }

    public synchronized static String toStringWithoutParentheses(Collection<?> list) {
        if (isNullOrEmpty(list)) {
            return null;
        }

        Iterator<?> i = list.iterator();
        if (!i.hasNext()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (;;) {
            Object e = i.next();
            sb.append(e);
            if (!i.hasNext()) {
                return sb.toString();
            }
            sb.append(", ");
        }
    }

    @SafeVarargs
    public synchronized static <T> List<T> toList(T... values) {
        return Arrays.asList(values);
    }

    public synchronized static <T> List<T> toListNotNull(Collection<T> collection) {
        return collection != null ? new ArrayList<>(collection) : Collections.<T>emptyList();
    }

    public synchronized static <T> List<T> toListNotNullOrEmpty(Collection<T> collection) {
        return isNotNullOrEmpty(collection) ? new ArrayList<>(collection) : Collections.<T>emptyList();
    }

    public synchronized static <T> List<T> toListNull(Collection<T> collection) {
        return isNotNullOrEmpty(collection) ? new ArrayList<>(collection) : null;
    }

    public synchronized static <T> boolean addIfNotNull(Collection<T> collection, T value) {
        if (value != null) {
            return collection.add(value);
        }
        return false;
    }

    public synchronized static <T> void addIfNotExists(Collection<T> collection, T... values) {
        List<T> toList = toList(values);
        toList.forEach(value -> {
            if (value != null && !collection.contains(value)) {
                collection.add(value);
            }
        });
    }

    public synchronized static <T> void addAllIfNotNull(Collection<T> collection, Collection<T> values) {
        if (isNotNullOrEmpty(values)) {
            for (T value : values) {
                addIfNotNull(collection, value);
            }
        }
    }

    public synchronized static <T> void addAllIfNotExists(Collection<T> collection, Collection<T> values) {
        if (isNotNullOrEmpty(values)) {
            for (T value : values) {
                addIfNotExists(collection, value);
            }
        }
    }

    public static Integer size(Collection<?> items) {
        return items == null ? 0 : items.size();
    }

    public static boolean hasValue(Collection<String> collection, String value) {
        if (isNotNullOrEmpty(collection)) {
            if (collection.contains(value)) {
                return true;
            }
            return first(collection).contains(value);
        }
        return true;
    }

    public static <T> Stream<T> stream(Collection<T> lista) {
        return isNotNullOrEmpty(lista) ? Collections.unmodifiableList(new ArrayList(lista)).stream() : new ArrayList().stream();
    }

}
