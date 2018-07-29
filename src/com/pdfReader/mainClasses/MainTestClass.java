package com.pdfReader.mainClasses;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public class MainTestClass 
{
	public static void main(String[] args)
	{
		String srcFileDir = "/home/sunil/books/";
		String dstFileDir = "/home/sunil/books/renamed/";
		
		PDFReaderModel pdfModel = new PDFReaderModel(srcFileDir, dstFileDir);
		try {
			pdfModel.bookNameExtractorMethod();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
