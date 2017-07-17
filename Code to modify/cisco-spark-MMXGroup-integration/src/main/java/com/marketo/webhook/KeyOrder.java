package com.marketo.webhook;

import java.util.Comparator;

public class KeyOrder implements Comparator<Object>{
  public int keyIndex;
  public String keys;
  


public KeyOrder() {
	super();
	// TODO Auto-generated constructor stub
}



public KeyOrder(int keyIndex, String keys) {
	super();
	this.keyIndex = keyIndex;
	this.keys = keys;
}



@Override
public int compare(Object o1, Object o2) {
	KeyOrder k1 = (KeyOrder) o1;
	KeyOrder k2 = (KeyOrder) o2;
	
	
	if(k1.keyIndex==k2.keyIndex)  
		return 0;  
	
		else if(k1.keyIndex>k2.keyIndex)  
		return 1;  
		else  
		return -1;  
		 
	
}

	
	
  
}
