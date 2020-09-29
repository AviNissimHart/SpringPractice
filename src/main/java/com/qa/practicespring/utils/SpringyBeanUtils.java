package com.qa.practicespring.utils;

import org.springframework.beans.BeanUtils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SpringyBeanUtils {
	
	public static void mergeObject(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}
}
