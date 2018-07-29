package com.pdfReader.mainClasses;

import org.apache.commons.lang3.StringUtils;

public class AllUpperCaseClass 
{
	public static void main(String[] args) 
	{
		String str = " BUDDHA OR KARL MARX  _________________________________________________________   ";
		System.out.println("StrLenght is : " + str.length());
		
		if(str.contains("_"))
		{
			str= str.replaceAll("_ ?", "");
		}
		str = str.trim();
	}
	


	
}
