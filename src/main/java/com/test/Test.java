package com.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.test.interfaces.DBSocketInterface;
import com.test.interfaces.GBSocketInterface;
import com.test.interfaces.UseStrategy;

public class Test {

	public static int SIZE = 111111;

	
//		List<Integer> arrayList = new ArrayList<Integer>();
//		List<Integer> linkedList = new LinkedList<Integer>();
//
//		for (int i = 0; i < SIZE; i++) {
//			arrayList.add(i);
//			linkedList.add(i);
//		}
//		loopList(arrayList);
//		loopList(linkedList);
	public static void main(String args[]) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		T1 t1 = new T1(list);
		T2 t2 = new T2(list);
		t1.start();
	    t2.start();
	    
	    Map<String,String> map = new LinkedHashMap<String,String>();
	}

	private static void loopList(List<Integer> list) {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < list.size(); i++) {
			list.get(i);
		}
		System.out.println(
				list.getClass().getSimpleName() + "ʹ����ͨforѭ������ʱ��Ϊ" + (System.currentTimeMillis() - startTime) + "ms");

		startTime = System.currentTimeMillis();
		for (Integer i : list) {
		}
		System.out.println(
				list.getClass().getSimpleName() + "ʹ��foreachѭ������ʱ��Ϊ" + (System.currentTimeMillis() - startTime) + "ms");
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("111","111");
		
		Map<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>();
		
		TreeMap<Integer,String> treeMap = new TreeMap<Integer,String>();
		treeMap.put(10, "10");
		treeMap.put(85, "85");
		treeMap.put(15, "15");
		treeMap.put(70, "70");
		treeMap.put(20, "20");
		treeMap.put(60, "60");
		treeMap.put(30, "30");
		treeMap.put(50, "50");
	}

	public Object find(){
		return null;
	}
	
	public void print(int... str){
		int []s = str;
	}
	
}

class T1 extends Thread{
	private List<Integer> list;
	
	public T1(List<Integer> list){
		this.list = list;
	}
	
	public void run(){
		for (Integer integer : list) {
			
		}
	}
}

class T2 extends Thread{
	private List<Integer> list;
	
	public T2(List<Integer> list){
		this.list = list;
	}
	
	public void run(){

		for (int i = 0; i < list.size(); i++) {
			list.remove(i);
		}

	}
	


}
/*********************************策略模式**********************************/
/** 策略角色  */
class StrategyA implements UseStrategy{

	@Override
	public void useStrategy() {
		System.out.println("StrategyA.useStrategy()");
	}
	
}
/** 策略角色  */
class StrategyB implements UseStrategy{
	
	@Override
    public void useStrategy() {
        System.out.println("StrategyB.useStrategy()");
    }
}

class Context{
    private UseStrategy testInterface;
    
    public Context(UseStrategy testInterface)
    {
        this.testInterface = testInterface;
    }
    
    public void strategyMethod()
    {
    	testInterface.useStrategy();
    }
}

class TestMain{
    public static void main(String[] args)
    {
    	UseStrategy strategyA = new StrategyA();
    	UseStrategy strategyB = new StrategyB();
        
        Context context = new Context(strategyA);
        context.strategyMethod();
        context = new Context(strategyB);
        context.strategyMethod();
    }
}
/*********************************策略模式**********************************/

/*********************************适配器模式**********************************/
class TestSocket{
	public static void main(String[] args) {
		//我去德国旅游，带去的充电器是国标的（可以将这里的GBSocket看成是充电器）
		GBSocketInterface gb = new GBSocket();
	    //来到德国后， 找到一家德国宾馆住下
		Hotel hotel = new Hotel();
		//由于没法充电，我拿出随身带去的适配器，并且将我带来的充电器插在适配器的上端插孔中。这个上端插孔是符合国标的，我的充电器完全可以插进去
		SocketAdapter socketAdapter = new SocketAdapter(gb);
		//再将适配器的下端插入宾馆里的插座上
		hotel.setdBSocketInterface(socketAdapter);
		//可以在宾馆中使用适配器进行充电了
		hotel.charge();
	}
}
/*********************************适配器模式**********************************/


/*********************************HashMap~HashTable**********************************/
class HashTest{
	public static void main(String[] args) {
		Map<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("","");
	}
}
/*********************************HashMap~HashTable**********************************/

