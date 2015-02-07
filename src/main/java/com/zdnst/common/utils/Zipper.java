package com.zdnst.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.log4j.Logger;
import org.apache.tools.zip.ZipOutputStream;

public class Zipper {
	
	private static Logger logger = Logger.getLogger(Zipper.class);
	
	public void zip(String outputZipFile, File inputFile) throws Exception {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
				outputZipFile));
		logger.info("zip begin");
		zip(out, inputFile, "");
		logger.info("zip done");
		out.close();
	}

	private void zip(ZipOutputStream out, File inputFile, String base) throws Exception {
		if (inputFile.isDirectory()) {
			File[] files = inputFile.listFiles();
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base + "/"));
			base = base.length() == 0 ? "" : base + "/";
			for (File file : files) {
				zip(out, file, base + file.getName());
			}
		} else {
			out.putNextEntry(new org.apache.tools.zip.ZipEntry(base));
			FileInputStream in = new FileInputStream(inputFile);
			int b;
			System.out.println(base);
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
		}
	}
}