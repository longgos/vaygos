package com.test;

import com.test.interfaces.GBSocketInterface;


public class GBSocket implements GBSocketInterface{

	/**
	 * 国标插座
	 */
	@Override
	public void powerWithTwoRound() {
		System.out.println("使用三项扁头的插孔供电");
		
	}

}
