package cn.zhangjingyao.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

/**
 * @author
 */
public final class Parameter {
	private static final Logger LOGGER = LogManager.getLogger(Parameter.class);

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
