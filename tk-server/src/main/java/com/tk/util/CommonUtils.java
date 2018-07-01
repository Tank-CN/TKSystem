package com.tk.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class CommonUtils {

	public static boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static boolean isNull(Integer numb) {
		if (null != numb && numb != 0) {
			return false;
		}
		return true;
	}

	public static boolean isNull(Long numb) {
		if (null != numb && numb != 0) {
			return false;
		}
		return true;
	}

	public static boolean isNull(Double numb) {
		if (null != numb && numb != 0) {
			return false;
		}
		return true;
	}

	public static boolean isNull(Byte numb) {
		if (null != numb) {
			return false;
		}
		return true;
	}

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static boolean isNotNull(Object obj) {
		return !isNull(obj);
	}

	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		return false;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
}
