package com.ontology.utilization.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Benchmark {
	
	public static void main(String[] args){
		Integer testCounter = 0;
		String test = "test"+testCounter;
		File f = new File(test);
		
		FileWriter fw = null;
		BufferedWriter bW = null;
		try {
			fw = new FileWriter(f);
			bW =  new BufferedWriter(fw);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
