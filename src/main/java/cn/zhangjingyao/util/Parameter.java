package cn.zhangjingyao.util;

import java.io.IOException;
import java.util.Properties;

public final class Parameter {
	private static final Logger LOGGER = Logger
			.getLogger(Parameter.class);

	private static final String PARAMETER_FILE = "/parameter.properties";

	private static final Parameter INSTANCE = new Parameter();

	public static Parameter getInstance() {
		return INSTANCE;
	}

	private Properties prop = null;

	public Properties getProp() {
		return prop;
	}

	private Parameter() {
		prop = new Properties();
		try {
			LOGGER.info("parameter config init");
			prop.load(this.getClass().getResourceAsStream(PARAMETER_FILE));
		} catch (IOException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
