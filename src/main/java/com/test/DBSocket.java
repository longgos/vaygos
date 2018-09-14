package com.test;

import com.test.interfaces.DBSocketInterface;

public class DBSocket implements DBSocketInterface{

	/**
	 * 德标插座
	 */
	@Override
	public void powerWithTwoRound() {
		System.out.println("使用两项圆头的插孔供电");
		
	}

}
