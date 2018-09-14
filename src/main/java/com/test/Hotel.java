package com.test;

import com.test.interfaces.DBSocketInterface;

/**
 * 德国宾馆
 * @author zhouzhenyi
 *
 */
public class Hotel {
	
	//旅馆提供德标接口
	private DBSocketInterface dBSocketInterface;
	
	public Hotel(){
		
	}
	
	public Hotel(DBSocketInterface dBSocketInterface){
		this.dBSocketInterface = dBSocketInterface;
	}

	public void setdBSocketInterface(DBSocketInterface dBSocketInterface) {
		this.dBSocketInterface = dBSocketInterface;
	}
	
	/** 旅馆有一个充电功能 */
	public void charge(){
		dBSocketInterface.powerWithTwoRound();
	}
}
