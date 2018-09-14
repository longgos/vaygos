package com.tool.base;

import org.apache.ibatis.session.RowBounds;


public class PagerRowBounds extends RowBounds {

	public PagerRowBounds() {
		super();
	}

	public PagerRowBounds(int offset, int limit) {
		super(offset, limit);
	}
	
}
