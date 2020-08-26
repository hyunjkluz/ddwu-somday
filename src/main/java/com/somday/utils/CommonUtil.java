/**
 * 
 */
package com.somday.utils;

import java.util.List;
import java.util.Map;

/**
 * @Since : Aug 25, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 25, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class CommonUtil {
	
	/**
	 * 공백 또는 null 체크
	 */
	public static boolean isNotNull(Object obj) {

		if (obj == null)
			return true;

		if ((obj instanceof String) && (((String) obj).trim().length() == 0)) {
			return true;
		}

		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}

		if (obj instanceof Map) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		if (obj instanceof List) {
			return ((List<?>) obj).isEmpty();
		}
		if (obj instanceof Object[]) {
			return (((Object[]) obj).length == 0);
		}

		return false;

	}
}
