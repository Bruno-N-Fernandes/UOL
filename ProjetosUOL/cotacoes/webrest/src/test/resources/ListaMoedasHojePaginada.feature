# language: pt

Funcionalidade: ListaMoedasHojePag

  Contexto: Limpar todos os parâmetros do contexto

#  Scenario 1:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP para o serviço de Lista de Moedas de Hoje Paginada
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista de Moedas De Hoje

#  Scenario 2:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 3:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "list"
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 4:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro JSONP
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com o JSON dos campos enviados e valor em next da Lista de Moedas De Hoje

#  Scenario 5:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP sem parâmetro tamanho
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 6:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next sem o campo DATA (campo deve ser ignorado) da Lista de Moedas De Hoje

#  Scenario 7:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com o JSON dos campos enviados e valor em next da Lista de Moedas De Hoje

#  Scenario 8:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro campos
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 9:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro moeda
    Dado que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 10:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro tamanho
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 11:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com o JSON dos campos enviados sem o campo DATA e valor em next (campo deve ser ignorado) da Lista de Moedas De Hoje

#  Scenario 12:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo e valor em "next" da Lista de Moedas De Hoje

#  Scenario 13:
  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON após inclusão de novos valores
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    Entao deve exibir uma lista paginada com o JSON dos campos enviados com novo valor no topo e valor em "next" da Lista de Moedas De Hoje

##    #################################################################
#  Scenario 14:
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista de Moedas de Hoje Paginada JSONP
 # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641151N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas de Hoje Paginada

###  Scenario 15:
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista de Moedas de Hoje Paginada JSONP
    # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641151N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSONP da Lista de Moedas de Hoje Paginada

	Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista de Moedas de Hoje Paginada JSONP
	# Criar a Primeira Pagina
	  Dado que enviamos o formato JSONP "list"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"    
	  E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
	  E mantemos o valor do next gerado para a proxima pagina
	
	# Avancar Proxima Pagina
	  E que enviamos o formato JSONP "list"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos o ponteiro next com valor "x" gerado
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
	  E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
	  E mantemos o valor do prev gerado para a pagina anterior
	
	# Incluir novos valores
	  E que incluímos novos valores para essa moeda no mesmo dia
	
	  # Retornar a Pagina Anterior  
	  E que enviamos o formato JSONP "list"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos o ponteiro prev com valor "x" gerado
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
	  E processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
	  E mantemos o valor do prev gerado para a pagina anterior
	  
    # Retornar a Pagina Anterior Novamente
      E que enviamos o formato JSONP "list"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 2
      E que enviamos o ponteiro prev com valor "x" gerado novamente
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
      Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas de Hoje Paginada

##  Scenario 16:
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista de Moedas de Hoje Paginada JSON
   # Criar a Primeira Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641151N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas de Hoje Paginada


	Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista de Moedas de Hoje Paginada JSON
	# Criar a Primeira Pagina
	  Dado que enviamos o formato "json"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"    
	  E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
	  E mantemos o valor do next gerado para a proxima pagina
	
	# Avancar Proxima Pagina
	  E que enviamos o formato "json"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos o ponteiro next com valor "x"
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
	  E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
	  E mantemos o valor do prev gerado para a pagina anterior
	
	# Incluir novos valores
	  E que incluímos novos valores para essa moeda no mesmo dia	  
	
	# Retornar a Primeira Pagina  
	  E que enviamos o formato "json"
	  E que enviamos a moeda de ID 18
	  E que enviamos o tamanho 2
	  E que enviamos o ponteiro prev com valor "x"
	  E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
	  Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
	  E mantemos o valor do prev gerado para a pagina anterior

    # Retornar a Pagina Anterior Novamente
      E que enviamos o formato "json"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 2
      E que enviamos o ponteiro prev com valor "x" gerado novamente
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
      Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas de Hoje Paginada

###  Scenario 17:
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista de Moedas de Hoje Paginada JSON
    # Criar a Primeira Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641151N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSON da Lista de Moedas de Hoje Paginada

##  Scenario 18:
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista de Moedas de Hoje Paginada JSONP
   # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641148P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSONP da Lista de Moedas de Hoje Paginada

##  Scenario 19:
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista de Moedas de Hoje Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641148P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641150N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas de Hoje Paginada

##  Scenario 20:
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista de Moedas de Hoje Paginada JSON
   # Acessar Última Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641148P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSON da Lista de Moedas de Hoje Paginada

##  Scenario 21:
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista de Moedas de Hoje Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "20641149N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641148P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "20641150P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas de Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas de Hoje Paginada
