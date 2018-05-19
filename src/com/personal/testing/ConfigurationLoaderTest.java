package com.personal.testing;

import com.personal.utils.ConfigurationLoader;

public class ConfigurationLoaderTest {

	public static void main(String[] args) throws Exception {
		ConfigurationLoader loader = new ConfigurationLoader();
		System.out.println(loader.getNodeValue("mail-temp-path"));
		System.out.println(loader.getNodeAttribValue("smtp-settings", "host"));
	}

}
