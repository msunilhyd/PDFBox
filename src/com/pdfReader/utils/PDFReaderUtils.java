package com.pdfReader.utils;

import java.util.Map;
import java.util.TreeMap;

import com.pdfReader.mainClasses.PDFReaderModel;

public class PDFReaderUtils 
{
	public static void printMap(PDFReaderModel pdfReaderModel)
	{
		
		Map<String, String> mapToBePrinted = pdfReaderModel.getNameToRenameMap();
		System.out.println("Size of Map is : " + mapToBePrinted.size());
		
		
		Map<String,String> treeMap = new TreeMap<>(mapToBePrinted);
		
		for(Map.Entry<String, String> e : treeMap.entrySet())
		{
			System.out.println(e.getKey() + "-----" + e.getValue());
		}
	}
	
	public static boolean testAllUpperCase(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= 97 && c <= 122) {
				return false;
			}
		}
		// str.charAt(index)
		return true;
	}
	

	public static String removeTrailingChars(String str)
	{
		if(str.contains("_"))
		{
			str= str.replaceAll("_ ?", "");
		}
		str = str.trim();
		return str;
	}
}
