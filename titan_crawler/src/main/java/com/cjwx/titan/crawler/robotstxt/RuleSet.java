package com.cjwx.titan.crawler.robotstxt;

import java.util.SortedSet;
import java.util.TreeSet;

public class RuleSet extends TreeSet<String> {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean add(String str) {
		//返回排序在str之前的集合
		SortedSet<String> sub = headSet(str);
		if (!sub.isEmpty() && str.startsWith(sub.last())) {
			return false;
		}
		boolean retVal = super.add(str);
		//返回排序在str之后的集合
		sub = tailSet(str + "\0");
		while (!sub.isEmpty() && sub.first().startsWith(str)) {
			sub.remove(sub.first());
		}
		return retVal;
	}

	//判断是否有字符串是s的前缀
	public boolean containsPrefixOf(String s) {
		SortedSet<String> sub = headSet(s);
		if (!sub.isEmpty() && s.startsWith(sub.last())) {
			return true;
		}
		return contains(s);
	}
	
}
