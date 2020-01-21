package br.com.uol.cotacoes.info;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VersionControl {
	
	private static final String UNDEFINED = "undefined";
	private String coreVersion;
	private String webrestVersion;
	
    @Autowired
    private Logger logger;
	
	@PostConstruct
	public void initialize(){

		try {
			webrestVersion = loadModuleVersion("webrest");
			coreVersion = loadModuleVersion("core");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);	    
		}		
    	
    	logVersion();
	
	}

	private String loadModuleVersion(String module) throws IOException {		
		InputStream resourceAsStream = VersionControl.class.getResourceAsStream("/META-INF/maven/br.com.uol.cotacoes/" + module + "/pom.properties");
		if(resourceAsStream == null){
			return UNDEFINED;
		}
		Properties prop = new Properties();		    
		prop.load( resourceAsStream );		        
		return prop.getProperty("version");
	}

	public String getCoreVersion() {
		return coreVersion;
	}

	public String getWebrestVersion() {
		return webrestVersion;
	}
	
	/**
	 * Gera o log com as versões do sistema na subida da aplicação
	 */
	private void logVersion() {
		String info = "Versão do webrest "
				+ webrestVersion
				+ " Versão do core "
				+ coreVersion;

        logger.info(info);
	}
	
}
