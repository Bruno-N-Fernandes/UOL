# language: pt

Funcionalidade: ListaMoedasHistPag

  Contexto: Limpar todos os parâmetros do contexto

#  Scenario 01: 
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados e valor em "next" da Lista de Moedas Históricas

#  Scenario 02:
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo e valor em "next" da Lista de Moedas Históricas

#  Scenario 03:
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 04:
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "list"
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 05:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON (sem parâmetro JSONP)
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir um JSON dos campos enviados e valor em "next" da Lista de Moedas Histórica Paginada

#  Scenario 06:
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP sem parâmetro tamanho
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 07:
  Cenario: Solicitar todos os dados de uma moeda para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir uma lista paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) e valor em "next" da Lista de Moedas Histórica Paginada

#  Scenario 08:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir um JSON dos campos enviados e valor em "next" da Lista de Moedas Histórica Paginada

#  Scenario 09:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON após inclusão de novos valores
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir uma lista paginada com o JSON dos campos enviados com novo valor no topo e valor em "next" da Lista de Moedas Históricas

#  Scenario 10:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON sem parâmetro campos
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 11:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON sem parâmetro moeda
    Dado que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 12:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON sem parâmetro tamanho
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"currency, fields, size"

#  Scenario 13:
  Cenario: Solicitar todos os dados de uma moeda para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas Histórica para a primeira página
    Entao deve exibir um JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) e valor em "next" da Lista de Moedas Histórica Paginada

#####################################################################################################
  #  Scenario 14:
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista de Moedas Histórica Paginada JSONP
     # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "21641148N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a próxima página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas Histórica Paginada

  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista de Moedas de Hist Paginada JSONP
    # Criar a Primeira Pagina
      Dado que enviamos o formato JSONP "list"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"  
      E processamos a solicitação de Lista de Moedas Histórica para a primeira página
      E mantemos o valor do next gerado para a proxima pagina
  
    # Avancar Proxima Pagina
      E que enviamos o formato JSONP "list"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro next com valor "x" gerado
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a próxima página
      E mantemos o valor do prev gerado para a pagina anterior
   
    # Incluir novos valores / Muda ponteiro 1ª pagina
      E que incluímos novos valores para essa moeda para um dia mais recente		
  
    # Retornar a Pagina Anterior  
      E que enviamos o formato JSONP "list"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro prev com valor "x" gerado
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a página anterior
      E mantemos o valor do prev gerado para a pagina anterior
  
    # Retornar a Pagina Anterior Novamente
      E que enviamos o formato JSONP "list"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro prev com valor "x" gerado novamente
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a página anterior
      Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas de Hist Paginada

  #  Scenario 15:
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista de Moedas Histórica Paginada JSONP
    # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "#  Scenario 19:"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a próxima página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSONP da Lista de Moedas Histórica Paginada

  #  Scenario 16:
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista de Moedas Histórica Paginada JSON
   # Criar a Primeira Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "21641148N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a próxima página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas Histórica Paginada

  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista de Moedas de Hist Paginada JSON
    # Criar a Primeira Pagina
      Dado que enviamos o formato "json"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"  
      E processamos a solicitação de Lista de Moedas Histórica para a primeira página
      E mantemos o valor do next gerado para a proxima pagina
  
    # Avancar Proxima Pagina
      E que enviamos o formato "json"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro next com valor "x" gerado
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a próxima página
      E mantemos o valor do prev gerado para a pagina anterior
   
    # Incluir novos valores / Muda ponteiro 1ª pagina
      E que incluímos novos valores para essa moeda para um dia mais recente
  
    # Retornar a Pagina Anterior  
      E que enviamos o formato "json"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro prev com valor "x" gerado
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a página anterior
      E mantemos o valor do prev gerado para a pagina anterior
  
    # Retornar a Pagina Anterior Novamente
      E que enviamos o formato "json"
      E que enviamos a moeda de ID 18
      E que enviamos o tamanho 3
      E que enviamos o ponteiro prev com valor "x" gerado novamente
      E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
      E processamos a solicitação de Lista de Moedas Histórica para a página anterior
      Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas de Hist Paginada

#  Scenario 17:
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista de Moedas Histórica Paginada JSON
    # Criar a Primeira Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a primeira página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "21641148N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a próxima página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSON da Lista de Moedas Histórica

#  Scenario 18:
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista de Moedas Histórica Paginada JSONP
   # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641140P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a página anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSONP da Lista de Moedas Histórica Paginada

#  Scenario 19:
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista de Moedas Histórica Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641140P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a página anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641146P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a página anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag. no formato JSONP da Lista de Moedas Histórica Paginada

#  Scenario 20:
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista de Moedas Histórica Paginada JSON
   # Acessar Última Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641140P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a página anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSON da Lista de Moedas Histórica Paginada

#  Scenario 21:
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista de Moedas Histórica Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "21641142N"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a próxima página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641140P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Histórica para a página anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos a moeda de ID 18
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "21641146P"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Histórica para a página anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para a proxima pag. no formato JSON da Lista de Moedas Histórica Paginada
