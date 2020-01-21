# language: pt

Funcionalidade: ListaInflacaoHojePaginada

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON para o serviço de Lista de Inflacao de Hoje Paginada
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir um JSON com os campos enviados e valor em next da Lista Inflacao Hoje Paginada

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro item
    Dado que enviamos o formato "json"
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: format={json}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={5}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro campos
    Dado que enviamos o formato "json"
    E que enviamos o tamanho 5
    E que enviamos o item 1172
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: format={json}, item={1172}, size={5}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro tamanho
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: format={json}, item={1172}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON com um campo inválido
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir um JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Inflacao Hoje Paginada

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP para o serviço de Lista de Inflacao de Hoje Paginada
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir uma Lista Inflacao Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados e valor em next da Lista Inflacao Hoje Paginada

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos o tamanho 5
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={1172}, size={5}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}, size={5}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro tamanho
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields, size\" OR \"item, fields, size, next\" OR \"item, fields, size, prev\" not met for actual request parameters: jsonp={callback}, item={1172}, fields={date,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos o tamanho 5
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje Paginada
    Entao deve exibir uma Lista Inflacao Hoje Paginada com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED e valor em next da Lista Inflacao Hoje Paginada

  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Inflacao Hoje Paginada JSONP
 # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSONP da Lista Inflacao Hoje Paginada

  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Inflacao Hoje Paginada JSONP
    # Criar a Primeira Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSONP da Lista Inflacao Hoje Paginada

  Cenario: Avançar proxima pag. a partir da 1ª pag. no serviço de Lista Inflacao Hoje Paginada JSON
 # Criar a Primeira Pagina
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

  # Avancar Proxima Pagina
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a primeira pag. e ponteiro next para a proxima pag. no formato JSON da Lista Inflacao Hoje Paginada

  Cenario: Avançar proxima pag. novamente a partir de outra pag. no serviço de Lista Inflacao Hoje Paginada JSON
    # Criar a Primeira Pagina
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Primeira Página
    E mantemos o valor do next gerado para a proxima pagina

    # Avancar Proxima Pagina
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    E mantemos o valor do next gerado para a proxima pagina

    #3 Avancar Proxima Novamente
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Próxima Página
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next nulo no formato JSON da Lista Inflacao Hoje Paginada

  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Inflacao Hoje Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "161122147N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Última Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSONP da Lista Inflacao Hoje Paginada

  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Inflacao Hoje Paginada JSONP
    # Acessar Última Pagina
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "161122147N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Última Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos o formato JSONP "list"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSONP da Lista Inflacao Hoje Paginada

  Cenario: Voltar a pag. anterior a partir da última pag. no serviço de Lista Inflacao Hoje Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "161122147N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Última Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev para a pag. anterior e ponteiro next para a ultima pag. no formato JSON da Lista Inflacao Hoje Paginada

  Cenario: Voltar a pag. anterior novamente a partir de outra pag. no serviço de Lista Inflacao Hoje Paginada JSON
    # Acessar Última Pagina
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro next com valor fixo "161122147N"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Última Página
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    E mantemos o valor do prev gerado para a pagina anterior

    # Voltar Pagina Anterior Novamente
    E que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos o tamanho 2
    E que enviamos o ponteiro prev com valor "x"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje para a Página Anterior
    Entao deve exibir o ponteiro prev nulo e ponteiro next para proxima pag. no formato JSON da Lista Inflacao Hoje Paginada