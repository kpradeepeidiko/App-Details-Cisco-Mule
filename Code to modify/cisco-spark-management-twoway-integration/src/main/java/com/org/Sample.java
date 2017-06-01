package com.org;

import org.apache.commons.codec.binary.Base64;
public class Sample {

	public String  sampleDecode(String a) {
		// TODO Auto-generated method stubpackage com.org;
		
			
			Base64 decoder = new Base64();
			byte[] decodedBytes = decoder.decode(a);
			 String str = new String(decodedBytes);
			System.out.println("decodere"+str);
			return str;
			
		}


			
}
