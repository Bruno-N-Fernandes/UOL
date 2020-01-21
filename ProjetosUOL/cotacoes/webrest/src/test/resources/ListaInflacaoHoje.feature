# language: pt

Funcionalidade: ListaInflacaoHoje

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir um JSON com os campos enviados da Lista Inflacao Hoje

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro item
    Dado que enviamos o formato "json"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters: fields={id,price,exchangeasset,high,low,open,volume,close,bid,ask,change,pctChange}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON sem parâmetro campos
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters: item={1172}"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSON com um campo inválido
    Dado que enviamos o formato "json"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir um JSON dos campos enviados sem o campo CHANGED da Lista Inflacao Hoje

  Cenario: Solicitar todos os dados de inflacao na data atual para formato CSV
    Dado que enviamos o formato "csv"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir um CSV com os campos enviados da Lista Inflacao Hoje

  Cenario: Solicitar todos os dados de inflacao na data atual para formato CSV sem parâmetro item
    Dado que enviamos o formato "csv"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters:"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato CSV sem parâmetro campos
    Dado que enviamos o formato "csv"
    E que enviamos o item 1172
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters:"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato CSV com um campo inválido
    Dado que enviamos o formato "csv"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir um CSV dos campos enviados sem o campo CHANGED da Lista Inflacao Hoje

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir uma Lista Inflacao Hoje com a funcao de callback contendo o JSON dos campos enviados

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters:"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir mensagem de erro com status "500" e causa "Parameter conditions \"item, fields\" not met for actual request parameters:"

  Cenario: Solicitar todos os dados de inflacao na data atual para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1172
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "CHANGED" e "pctChange"
    Quando processamos a solicitação de Lista Inflacao Hoje
    Entao deve exibir uma Lista Inflacao Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo CHANGED