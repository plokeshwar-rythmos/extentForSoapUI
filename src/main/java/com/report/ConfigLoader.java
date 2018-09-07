package com.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;

public class ConfigLoader {
	
	Logger log;
	
	public ConfigLoader(Logger logger) {
		this.log = logger;
	}

	

	public Properties loadProperties(String folderPath, String fileName) {

		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream(folderPath + "/"+fileName);
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}

	public boolean fileExists(String folderPath, String fileName){
		log.info("Searching for file "+fileName+" in folder "+folderPath);
		
		if (!new File(folderPath + "/" + fileName).exists()) {
			if (folderPath.lastIndexOf("\\") > 0) {
				String path = folderPath.substring(0, folderPath.lastIndexOf("\\"));
				fileExists(path, fileName);
			}else{
				
				log.error(fileName+" file not found.");
			}
		} else {
			log.info(fileName+" file found in folder "+folderPath);
			return true;
		}

		return false;
	}


}
