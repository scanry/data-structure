package com.six.data_structure;

/**
 * @author sixliu
 * @date 2017年12月23日
 * @email 359852326@qq.com
 * @Description
 */
public class StringTest {

	public static void main(String[] args) {
		String text = "abssssa";
		String str = "bs";
		System.out.println(indexOf(text,str));
	}

	public static int indexOf(String str, String findStr, int fromIndex) {
		return indexOf(str.toCharArray(), 0, str.length(), findStr.toCharArray(), 0, findStr.length(), fromIndex);
	}

	public static int indexOf(String str, String findStr) {
		return indexOf(str, findStr, 0);
	}

	static int indexOf(char[] source, int sourceOffset, int sourceCount, char[] target, int targetOffset,
			int targetCount, int fromIndex) {
		if (fromIndex >= sourceCount) {
			return (targetCount == 0 ? sourceCount : -1);
		}
		if (fromIndex < 0) {
			fromIndex = 0;
		}
		if (targetCount == 0) {
			return fromIndex;
		}

		char first = target[targetOffset];
		int max = sourceOffset + (sourceCount - targetCount);

		for (int i = sourceOffset + fromIndex; i <= max; i++) {
			/* Look for first character. */
			if (source[i] != first) {
				while (++i <= max && source[i] != first)
					;
			}

			/* Found first character, now look at the rest of v2 */
			if (i <= max) {
				int j = i + 1;
				int end = j + targetCount - 1;
				for (int k = targetOffset + 1; j < end && source[j] == target[k]; j++, k++)
					;

				if (j == end) {
					/* Found whole string. */
					return i - sourceOffset;
				}
			}
		}
		return -1;
	}

}
