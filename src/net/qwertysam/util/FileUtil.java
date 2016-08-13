package net.qwertysam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtil
{
	public static void copyFile(File inFile, File outFile)
	{
		try
		{
			// if file, then copy it
			// Use bytes stream to support all file types
			InputStream in = new FileInputStream(inFile);
			OutputStream out = new FileOutputStream(outFile);
			
			byte[] buffer = new byte[1024];
			
			int length;
			// copy the file content in bytes
			while ((length = in.read(buffer)) > 0)
			{
				out.write(buffer, 0, length);
			}
			
			in.close();
			out.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
