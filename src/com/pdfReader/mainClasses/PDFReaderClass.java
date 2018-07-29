package com.pdfReader.mainClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReaderClass {

	private List<String> fileList = new ArrayList<>();
	private List<String> renamedCopyToList = new ArrayList<>();


	public List<String> getRenamedCopyToList() {
		return renamedCopyToList;
	}

	public void setRenamedCopyToList(List<String> renamedCopyToList) {
		this.renamedCopyToList = renamedCopyToList;
	}

	public List<String> getNameList() {
		return nameList;
	}

	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}

	private List<String> nameList = new ArrayList<>();

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public PDFReaderClass() {

		readDirForFileNames();
	}

	public void printFileNamesAsTheyAreToExtracted() {
		if (fileList.size() != 0) {
			for (int i = 0; i < fileList.size(); i++) {
				System.out.println(fileList.get(i) + " = " + nameList.get(i));
			}
		}
	}

	public void printFileNamesAfterRenamed() {
		
		File folder = new File("/home/sunil/books/renamed");
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("No : of documents in /home/sunil/books/renamed = " + listOfFiles.length);

		
		System.out.println("Printing renamed files.");
		
		for(int i=0;i<listOfFiles.length;i++)
		{
			File file = listOfFiles[i];
			if(file.isFile())
			{
				System.out.println(file.getName());
			}
		}
	}
	
	
	public void copyFile(File src, File dest)
	{	
		try {
			FileUtils.copyFile(src,dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readDirForFileNames()
	{
		File folder = new File("/home/sunil/books/");
		File[] listOfFiles = folder.listFiles();
		
		System.out.println("No : of documents in /home/sunil/books/ = " + listOfFiles.length);

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		   // System.out.println("File " + listOfFiles[i].getName());
			  String path ="/home/sunil/books/";
		    fileList.add(path + listOfFiles[i].getName());
		  } else if (listOfFiles[i].isDirectory()) {
		    System.out.println("Directory " + listOfFiles[i].getName());
		  }
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

	public String removeTrailingChars(String str)
	{
		if(str.contains("_"))
		{
			str= str.replaceAll("_ ?", "");
		}
		str = str.trim();
		return str;
	}
	
	public void renameFiles()
	{
		for(int i=0;i<fileList.size();i++)
		{
			String nameListFileAndPath = "/home/sunil/books/renamed/";
			String actualFileNameUnderIteration = fileList.get(i); 
			String extractedfileNameUnderIteration = nameList.get(i); 
			String renamedFileName = nameListFileAndPath + extractedfileNameUnderIteration + ".pdf";
			
			System.out.println("ActualFileNameUnderIteration = " + actualFileNameUnderIteration);
			//System.out.println("ExtractedfileNameUnderIteration = " + extractedfileNameUnderIteration);
			System.out.println("RenamedFileName = " + renamedFileName);
			
			renamedCopyToList.add(renamedFileName);
			
			 File file = new File(actualFileNameUnderIteration);
		     File newFile = new File(renamedFileName);
		     
		     copyFile(file, newFile);
		}
	}
	
	
	public static void main(String[] args) throws IOException {

		
	}
}
