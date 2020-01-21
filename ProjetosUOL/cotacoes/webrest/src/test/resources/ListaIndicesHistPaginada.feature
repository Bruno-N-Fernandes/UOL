# language: pt

Funcionalidade: ListaIndicesHistPaginada

  Contexto: Limpar todos os parâmetros do contexto

#    1
  Cenario: Solicitar todos os dados de indices historicos para formato JSON para o serviço de Lista de Indices de Hist Paginada
    Dado que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir um JSON com os campos enviados e valor em next da Lista Indice Hist Paginada

#  2
  Cenario: Solicitar todos os dados de indices historicos para formato JSON sem parâmetro item
    Dado que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={3}"}"

#  3
  Cenario: Solicitar todos os dados de indices historicos para formato JSON sem parâmetro campos
    Dado que enviamos o tamanho 3
    E que enviamos o item 10
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: item={10}, size={3}"}"

#  4
  Cenario: Solicitar todos os dados de indices historicos para formato JSON sem parâmetro tamanho
    Dado que enviamos o formato "json"
    E que enviamos o item 10
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: item={10}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

#  5
  Cenario: Solicitar todos os dados de indices historicos para formato JSON com um campo inválido
    Dado que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hist Paginada

#  6
  Cenario: Solicitar todos os dados de indices historicos para formato JSONP para o serviço de Lista de Indices de Hist Paginada
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir uma Lista Indice Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Indice Hist Paginada

#  7
  Cenario: Solicitar todos os dados de indices historicos para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={10}, size={3}"}"

#    8
  Cenario: Solicitar todos os dados de indices historicos para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={3}"}"

#    9
  Cenario: Solicitar todos os dados de indices historicos para formato JSON sem parâmetro tamanho
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 10
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={10}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

#    10
  Cenario: Solicitar todos os dados de indices historicos para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir uma Lista Indice Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Indice Hist Paginada

############################################################################################
#  11
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Indice Hist Paginada JSONP
 # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Indice Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Indice Hist para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSONP da Lista Indice Hist Paginada

  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista Indice Hist Paginada JSONP
   # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Indice Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

   # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Indice Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

   # Incluir novos valores / Muda ponteiro 1ª pagina
    E que incluimos novos valores para o item 10 para um dia mais recente

   # Retornar a Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Indice Hist para a Página Anterior
    Entao deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag. no formato JSONP da Lista Indice Hist Paginada
    E mantemos o valor do prev gerado para a pagina anterior

   # Retornar a Pagina Anterior Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 10
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x" gerado novamente
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Indice Hist para a Primeira Página
    Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSONP da Lista Indice Hist Paginada


##  12
#  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Indice Hist Paginada JSONP
#    # Criar a Primeira Pagina
#    Dado que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Primeira Página
#    E com o valor do next gerado para a proxima pagina
#
#    # Avancar Proxima Pagina
#    E que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do next gerado para a proxima pagina
#
#    #3 Avancar Proxima Novamente
#    E que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Próxima Página
#    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSONP da Lista Indice Hist Paginada
#
##  13
#  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Indice Hist Paginada JSON
# # Criar a Primeira Pagina
#    Dado que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Primeira Página
#    E com o valor do next gerado para a proxima pagina
#
#  # Avancar Proxima Pagina
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Próxima Página
#    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSON da Lista Indice Hist Paginada
#
#  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista Indice Hist Paginada JSON
#    # Criar a Primeira Pagina
#    Dado que enviamos o formato "json"
#    E que enviamos o item 10
#    E que enviamos o tamanho 2
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice para a Primeira Página
#    E mantemos o valor do next gerado para a proxima pagina
#
#    # Avancar Proxima Pagina
#    E que enviamos o formato "json
#    E que enviamos o item 10
#    E que enviamos o tamanho 2
#    E que enviamos o ponteiro next com valor "x" gerado
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice para a Próxima Página
#    E mantemos o valor do prev gerado para a pagina anterior
#
#    # Incluir novos valores / Muda ponteiro 1ª pagina
#    E que incluimos novos valores atuais nos campos "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange" para o item 10
#
#    # Retornar a Pagina Anterior
#    E que enviamos o formato "json"
#    E que enviamos o item 10
#    E que enviamos o tamanho 2
#    E que enviamos o ponteiro prev com valor "x" gerado
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Primeira Página
#    Entao deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag. no formato JSON da Lista Indice Hist Paginada
#    E mantemos o valor do prev gerado para a pagina anterior novamente
#
#    # Retornar a Pagina Anterior Novamente
#    E que enviamos o formato "json"
#    E que enviamos o item 10
#    E que enviamos o tamanho 2
#    E que enviamos o ponteiro prev com valor "x" gerado novamente
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Primeira Página
#    Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSON da Lista Indice Hist Paginada
#
##  14
#  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Indice Hist Paginada JSON
#    # Criar a Primeira Pagina
#    Dado que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Primeira Página
#    E com o valor do next gerado para a proxima pagina
#
#    # Avancar Proxima Pagina
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do next gerado para a proxima pagina
#
#    #3 Avancar Proxima Novamente
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Próxima Página
#    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSON da Lista Indice Hist Paginada
#
##  15
#  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Indice Hist Paginada JSONP
#    # Acessar Última Pagina
#    Dado que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor fixo "123456759N"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior
#    E que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Página Anterior
#    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSONP da Lista Indice Hist Paginada
#
##  16
#  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Indice Hist Paginada JSONP
#    # Acessar Última Pagina
#    Dado que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor fixo "123456759N"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior
#    E que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Página Anterior
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior Novamente
#    E que enviamos o formato JSONP "list"
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Página Anterior
#    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSONP da Lista Indice Hist Paginada
#
##  17
#  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Indice Hist Paginada JSON
#    # Acessar Última Pagina
#    Dado que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor fixo "123456759N"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Página Anterior
#    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSON da Lista Indice Hist Paginada
#
##  18
#  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Indice Hist Paginada JSON
#    # Acessar Última Pagina
#    Dado que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro next com valor fixo "123456759N"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Próxima Página
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    E processamos a solicitação de Lista Indice Hist para a Página Anterior
#    E com o valor do prev gerado para a pagina anterior
#
#    # Voltar Pagina Anterior Novamente
#    E que enviamos o item 10
#    E que enviamos o tamanho 3
#    E que enviamos o ponteiro prev com valor "x"
#    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
#    Quando processamos a solicitação de Lista Indice Hist para a Página Anterior
#    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSON da Lista Indice Hist Paginada