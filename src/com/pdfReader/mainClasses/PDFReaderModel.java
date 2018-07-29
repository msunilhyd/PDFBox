package com.pdfReader.mainClasses;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

import com.pdfReader.utils.PDFReaderUtils;

public class PDFReaderModel
{
	
	private String SRC_FILE_PATH;
	private String DST_FILE_PATH;
	
	// Lists for storing Files for corresponding operations
	private List<String> fileList = new ArrayList<>();
	private List<String> renamedCopyToList = new ArrayList<>();
	private List<String> nameList = new ArrayList<>();
	
	private Map<String, String> nameToRenameMap = new HashMap<>();
	
	public Map<String, String> getNameToRenameMap() {
		return nameToRenameMap;
	}
	public void setNameToRenameMap(Map<String, String> nameToRenameMap) {
		this.nameToRenameMap = nameToRenameMap;
	}
	public List<String> getFileList() {
		return fileList;
	}
	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}
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
	
	public PDFReaderModel(String srcFileDir, String dstFileDir) {
		
		this.SRC_FILE_PATH = srcFileDir;
		this.DST_FILE_PATH = dstFileDir;
		
		readDirForFileNames();
	}
	
	public void readDirForFileNames()
	{
		File folder = new File(SRC_FILE_PATH);
		File[] listOfFiles = folder.listFiles();
	
		for (int i = 0; i < listOfFiles.length; i++) 
		{
		  if (listOfFiles[i].isFile())
		  {
		    System.out.println("File " + listOfFiles[i].getName());
			String srcFilePathAndName = SRC_FILE_PATH + listOfFiles[i].getName();
		    fileList.add(srcFilePathAndName); 
		  }
		}
		System.out.println("No : of documents in " + SRC_FILE_PATH + " = " + fileList.size());

	}
	
	public void printFileNamesAsTheyAreToExtracted(){
		if (fileList.size() != 0) {
			for (int i = 0; i < fileList.size(); i++) {
				System.out.println(fileList.get(i) + " = " + nameList.get(i));
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
	
	public void renameFiles()
	{
		for(int i=0;i<fileList.size();i++)
		{
			String nameListFileAndPath = DST_FILE_PATH;
			String actualFileNameUnderIteration = fileList.get(i); 
			String extractedfileNameUnderIteration = nameList.get(i); 
			String renamedFileName = nameListFileAndPath + extractedfileNameUnderIteration + ".pdf";
			
			//System.out.println("ActualFileNameUnderIteration = " + actualFileNameUnderIteration);
			//System.out.println("ExtractedfileNameUnderIteration = " + extractedfileNameUnderIteration);
			//System.out.println("RenamedFileName = " + renamedFileName);
			
			renamedCopyToList.add(renamedFileName);
			
			 File file = new File(actualFileNameUnderIteration);
		     File newFile = new File(renamedFileName);
		     
		     copyFile(file, newFile);
		}
		printMap();
	}
	
	public void printMap() 
	{
		PDFReaderUtils.printMap(this);
	}
	

	
	public void printFileNamesAfterRenamed() {
		
		File folder = new File(DST_FILE_PATH);
		File[] listOfFiles = folder.listFiles();
		
		
		//System.out.println("No : of documents in /home/sunil/books/renamed = " + listOfFiles.length);

		
		//System.out.println("Printing renamed files.");
		
		for(int i=0;i<listOfFiles.length;i++)
		{
			File file = listOfFiles[i];
			if(file.isFile())
			{
				System.out.println(file.getAbsolutePath()+ "====" + file.getName() + " <<< " + i);
			}
		}
	}
	
	public void bookNameExtractorMethod() throws InvalidPasswordException, IOException
	{
		for (String s : fileList) 
		{

			// System.out.println("FileName is : " + s);

			PDDocument pd = PDDocument.load(new File(s));
			pd.getClass();

			if (!pd.isEncrypted()) {
				PDFTextStripper tStripper = new PDFTextStripper();

				String pdfFileInText = tStripper.getText(pd);

				String[] lines = pdfFileInText.split("\\r?\\n");

				int count = 0;
				int lineNumber = 0;
				String str = "";
				for (String line : lines) {
					if (line.length() > 0) {
						if (PDFReaderUtils.testAllUpperCase(line)) {
							lineNumber += 1;
							//System.out.println(lineNumber + "-----" + line);
							str =  str + " " +line + " ";
							str = PDFReaderUtils.removeTrailingChars(str);
						}
					}
					count = count + 1;
					if ((count == 7))
						break;
				}
				nameList.add(str);
				System.out.println(s + " ----- " + str);
				nameToRenameMap.put(s, str);
				pd.close();
			}
		}
		//printFileNamesAsTheyAreToExtracted();
		renameFiles();
		//printFileNamesAfterRenamed();	
	}
	
}
