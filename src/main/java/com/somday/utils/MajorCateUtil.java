/**
 * 
 */
package com.somday.utils;

/**
 * @Since : Aug 27, 2020
 * @Author kimhyunjin
 * 
 *         <pre>
 * -----------------
 * 개정이력
 * Aug 27, 2020 kimhyunjin : 최초작성
 *         </pre>
 *
 */
public class MajorCateUtil {
	public static String format(Integer majorId) {
		if (majorId.equals(1)) {
			return "CT01";
		}
		if (majorId.equals(2)) {
			return "CT05";
		}
		if (majorId.equals(3)) {
			return "CT06";
		}
		return "CT07";
	}

}
