package com.six.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @date 2017年11月29日
 * @email 359852326@qq.com
 * @Description: TODO
 */
public class SymbolMatchingTesy {

	static class Record {
		char value;
		Record next;

		Record(char c) {
			Record.this.value = c;
		}
		
		@Override
		public boolean equals(Object ob) {
			if(null!=ob&&ob instanceof Record) {
				Record target=(Record)ob;
				if(('('==this.value&&')'==target.value)||
						('['==this.value&&']'==target.value)||
						('{'==this.value&&'}'==target.value)||
						(')'==this.value&&'('==target.value)||
						(']'==this.value&&'['==target.value)||
						('}'==this.value&&'{'==target.value)) {
					return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
		String text = "{}()[] {}[]";
		System.out.println("字符串匹配结果:"+checkMatching(text));
	}

	public static boolean checkMatching(String text) {
		List<Record> ltRecordLinked = new ArrayList<Record>();
		List<Record> rgRecordLinked = new ArrayList<Record>();
		Record newLtRecord = null;
		for (int i = 0, size = text.length(); i < size; i++) {
			char tempChar = text.charAt(i);
			if (isLeft(tempChar)) {
				newLtRecord = new Record(tempChar);
				ltRecordLinked.add(newLtRecord);
			} else if (isRigth(tempChar)) {
				int tempIndex = rgRecordLinked.size();
				if (tempIndex >= ltRecordLinked.size()) {
					return false;
				} else if (!(newLtRecord = new Record(tempChar)).equals(ltRecordLinked.get(tempIndex))) {
					return false;
				} else {
					rgRecordLinked.add(newLtRecord);
				}
			}

		}
		return true;
	}

	private static boolean isLeft(char c) {
		return '('==c||'{'==c||'['==c;
	}

	private static boolean isRigth(char c) {
		return ')'==c||'}'==c||']'==c;
	}

}
