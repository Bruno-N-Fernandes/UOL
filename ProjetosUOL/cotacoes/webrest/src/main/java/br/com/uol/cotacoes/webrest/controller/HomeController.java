package br.com.uol.cotacoes.webrest.controller;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.uol.cotacoes.core.business.service.CurrencyService;
import br.com.uol.cotacoes.info.VersionControl;

/**
 * Controller home da aplicação.
 * 
 * Pode conter a documentação dos serviços disponibilizados ou o link para doumentação.
 * 
 * @author mzp_dferraz
 *
 */
@RestController
public class HomeController {
	
	private static final String SYSTEM_OK = "System online";
	private static final String SYSTEM_NOK = "System offline";
	
    @Autowired
    private Logger logger;
    
    @Autowired
    private VersionControl versionControl;
    private ObjectNode jsonVersion;
    
    @Autowired
    private CurrencyService service;
    
	@PostConstruct
	public void initialize(){
		webVersion();
	}

	@RequestMapping("/")
    public String home() {
        logger.info("Home");
        return "Home sweet home";
    }
    
    @RequestMapping(path="/version")
    public ObjectNode info() {        
        return jsonVersion;
    }
    
	/**
	 * Gera o objeto JSON com as versões do sistema
	 */
	private void webVersion() {
		ObjectNode jsonVersion = JsonNodeFactory.instance.objectNode();
        
		jsonVersion.put("system", versionControl.getWebrestVersion());
		jsonVersion.put("core", versionControl.getCoreVersion());
		
    	this.jsonVersion = jsonVersion;
	}
	
	@RequestMapping(path="/healthcheck" )
	public String healthCheckSystem(){
		if(service.healthCheckWithDolar()){
			return SYSTEM_OK;
		}
		return SYSTEM_NOK;
	}

}
