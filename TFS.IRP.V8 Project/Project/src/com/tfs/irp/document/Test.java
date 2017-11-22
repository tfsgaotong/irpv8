package com.tfs.irp.document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test {
	public static void readFile(String path, String duibiString,int j) throws IOException{
        FileInputStream inputStream = new FileInputStream(path);  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
        String str = null; 
        StringBuffer sBuffer=new StringBuffer();
        if(j==0){
        	sBuffer.append("CJK_WORDS###");
        }
        while((str = bufferedReader.readLine()) != null)  
        {  
        	String first=str.split("/")[0];
        	String two=duibiString.split("/")[0];
        	System.out.println(str);
        	if(!first.equals(two)){
        		sBuffer.append(str+"###");
        	}
        }
        inputStream.close();  
        bufferedReader.close();
        //写入
        FileWriter writer = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(writer);
        String[] readString=sBuffer.toString().split("###");
        for (int i = 0; i < readString.length; i++) {
			bw.write(readString[i]);
        	bw.newLine();
		}
        bw.close();
        writer.close();
	}
	
	public static void replaceFile(String path, String replacePath) throws IOException{
        FileInputStream inputStream = new FileInputStream(replacePath);  
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));  
        String str = null; 
        int j=0;
        while((str = bufferedReader.readLine()) != null){  
        	readFile(path,str,j);
        	j++;
        }
        inputStream.close();  
        bufferedReader.close();
	}
	public static void main(String[] args) throws IOException {
		String path="d:\\test";
		String replacePath="d:\\test.lex";
		File file=new File(path);
		File[] files=file.listFiles();
		for (File file2 : files) {
			String nameString=file2.getName();
			System.out.println("nameString::::::::::::::::::::"+nameString);
			replaceFile(path+"\\"+nameString,replacePath);
			//replaceFile(path,replacePath);
		}
		
	}
}
