package com.test.fanshe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Fanshe_01 {

	public static void main(String[] args) {
		Example_01 example = new Example_01("10","20","30");
		Class<? extends Example_01> exampleC = example.getClass();
		Constructor[] declaredConstructors = exampleC.getDeclaredConstructors();
		Constructor[]  c = exampleC.getConstructors();
		for (int j = 0; j < c.length; j++) {
			Constructor constructor = c[j];
			System.out.println("构造方法名："+constructor.getName());
			
		}
		return;
		/*Method [] method = exampleC.getMethods();
		for (int i = 0; i < method.length; i++) {
			System.out.println("方法名："+method[i].getName());
		}*/
	}
}
