package net.qwertysam.main;

import java.io.File;

import net.qwertysam.util.FileUtil;
import net.qwertysam.util.JarUtil;

public class Main
{
	public static void main(String[] args)
	{
		FileUtil.copyFile(new File("AfroMan.jar"), new File("AfroMan-mp3.jar"));
		JarUtil.createRawDistributionJar(true);
		
		FileUtil.copyFile(new File("AfroMan.jar"), new File("AfroMan-wav.jar"));
		JarUtil.createRawDistributionJar(false);
	}
}
