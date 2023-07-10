package com.kCalControl.service.impl;

import org.springframework.stereotype.Component;
import com.kCalControl.service.Filters;

@Component
public class FiltersImpl implements Filters{

	@Override
	public String toLike(String filter) {
		String like = "%";
		if (filter != null) {
			like = "%" + filter.replace("\\", "\\\\").replace("%", "\\%").replace("_", "\\_") + "%";
		}
		return like;
	}

}
