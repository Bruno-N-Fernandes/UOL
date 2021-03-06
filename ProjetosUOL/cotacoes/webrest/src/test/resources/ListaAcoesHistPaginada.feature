# language: pt

Funcionalidade: ListaAcoesHistPaginada

#1
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON para o serviço de Lista de Acoes de Hist Paginada
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir um JSON com os campos enviados e valor em next da Lista Acao Hist Paginada

#  2
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON sem parâmetro item
    Dado que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={3}"}"

#    3
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON sem parâmetro campos
    Dado que enviamos o tamanho 3
    E que enviamos o item 232
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: item={232}, size={3}"}"

#    4
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON sem parâmetro tamanho
    Dado que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: item={232}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

#  5
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON com um campo inválido
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Acao Hist Paginada

#  6
  Cenario: Solicitar todos os dados de acoes historicos para formato JSONP para o serviço de Lista de Acoes de Hist Paginada
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir uma Lista Acao Hist Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Acao Hist Paginada

#  7
  Cenario: Solicitar todos os dados de acoes historicos para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={232}, size={3}"}"

#    8
  Cenario: Solicitar todos os dados de acoes historicos para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={3}"}"

#    9
  Cenario: Solicitar todos os dados de acoes historicos para formato JSON sem parâmetro tamanho
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={232}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

#  10
  Cenario: Solicitar todos os dados de acoes historicos para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir uma Lista Acao Hist Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Acao Hist Paginada

######################################################################################################
#  11
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Acao Hist Paginada JSONP
 # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSONP da Lista Acao Hist Paginada

#    12
  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista Acao Hist Paginada JSONP
   # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

   # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Incluir novos valores / Muda ponteiro 1ª pagina
    E que incluimos novos valores para o item 232 para um dia mais recente

    # Retornar a Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag. no formato JSONP da Lista Acao Hist Paginada
    E mantemos o valor do prev gerado para a pagina anterior

    # Retornar a Pagina Anterior Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x" gerado novamente
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSONP da Lista Acao Hist Paginada

#  13
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Acao Hist Paginada JSONP
    # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSONP da Lista Acao Hist Paginada

#  14
  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Acao Hist Paginada JSON
 # Criar a Primeira Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSON da Lista Acao Hist Paginada

#  15
  Cenario: Avançar proxima pag. a partir da 1ª pag. e após inclusao de novos valores retornar no serviço de Lista Acao Hist Paginada JSON
  # Criar a Primeira Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x" gerado
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Incluir novos valores / Muda ponteiro 1ª pagina
    E que incluimos novos valores para o item 232 para um dia mais recente

    # Retornar a Pagina Anterior
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x" gerado
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev diferente de nulo e ponteiro next para a proxima pag. no formato JSON da Lista Acao Hist Paginada
    E mantemos o valor do prev gerado para a pagina anterior

    # Retornar a Pagina Anterior Novamente
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x" gerado novamente
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    Entao deve exibir o ponteiro prev igual a nulo e ponteiro next para a proxima pag. no formato JSON da Lista Acao Hist Paginada


#  16
  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Acao Hist Paginada JSON
    # Criar a Primeira Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSON da Lista Acao Hist Paginada

#  17
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Acao Hist Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "123456779N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSONP da Lista Acao Hist Paginada

#  18
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Acao Hist Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "123456779N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSONP da Lista Acao Hist Paginada

#  19
  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Acao Hist Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "123456779N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSON da Lista Acao Hist Paginada

#  20
  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Acao Hist Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro next com valor fixo "123456779N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Próxima Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Acao Hist para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    Dado que enviamos o item 232
    E que enviamos o tamanho 3
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Acao Hist para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSON da Lista Acao Hist Paginada