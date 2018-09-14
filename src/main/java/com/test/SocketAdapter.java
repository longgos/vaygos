package com.test;

import com.test.interfaces.DBSocketInterface;
import com.test.interfaces.GBSocketInterface;

/**
 * 插头适配器
 * @author ljk
 *
 */
public class SocketAdapter implements DBSocketInterface{
	
	private GBSocketInterface gBSocketInterface;
	
	/**
	 * 在创建适配器对象时，必须传入一个新接口的实现类
	 * @param gBSocketInterface
	 */
	public SocketAdapter(GBSocketInterface gBSocketInterface){
		this.gBSocketInterface = gBSocketInterface;
	}
	
	/**
	 * 将对旧接口的调用适配到新接口
	 */
	@Override
	public void powerWithTwoRound() {
		gBSocketInterface.powerWithTwoRound();
	}

	
}
