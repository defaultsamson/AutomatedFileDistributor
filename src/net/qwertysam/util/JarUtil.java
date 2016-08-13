package net.qwertysam.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class JarUtil
{
	public static void createRawDistributionJar(boolean isMp3)
	{
		try
		{
			File zipFile = new File("AfroMan-" + (isMp3 ? "mp3" : "wav") + ".jar");
			
			// get a temp file
			File tempFile = File.createTempFile(zipFile.getName(), null);
			// delete it, otherwise you cannot rename your existing zip to it.
			tempFile.delete();
			
			boolean renameOk = zipFile.renameTo(tempFile);
			if (!renameOk)
			{
				throw new RuntimeException("could not rename the file " + zipFile.getAbsolutePath() + " to " + tempFile.getAbsolutePath());
			}
			byte[] buf = new byte[1024];
			
			ZipInputStream zin = new ZipInputStream(new FileInputStream(tempFile));
			JarOutputStream out = new JarOutputStream(new FileOutputStream(zipFile));
			
			ZipEntry entry;
			while ((entry = zin.getNextEntry()) != null)
			{
				String name = entry.getName();
				boolean deleteFile = false;
				
				if ((isMp3 && name.contains("/wav")) || (!isMp3 && name.contains("/mp3")))
				{
					System.out.println("Deleting File: " + name);
					deleteFile = true;
				}
				
				if (!isMp3 && (name.contains("tritonus_share.jar") || name.contains("mp3spi1.9.5.jar") || name.contains("jl1.0.1.jar")))
				{
					System.out.println("Deleting File: " + name);
					deleteFile = true;
				}
				
				if (!deleteFile)
				{
					// Add ZIP entry to output stream.
					out.putNextEntry(new ZipEntry(name));
					// Transfer bytes from the ZIP file to the output file
					int len;
					while ((len = zin.read(buf)) > 0)
					{
						out.write(buf, 0, len);
					}
				}
			}
			// Close the streams
			zin.close();
			out.close();
			tempFile.delete();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
