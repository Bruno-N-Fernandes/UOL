# language: pt

Funcionalidade: ResumoIndicadores

  Contexto: Limpar todos os parâmetros do contexto

  Cenario: Solicitar o resumo de um indicador para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1169
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo de Indicadores

  Cenario: Solicitar o resumo de um indicador para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1169
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar o resumo de um indicador para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

  Cenario: Solicitar o resumo de um indicador para formato JSON sem o parâmetro JSONP
    Dado que enviamos o item 1169
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir um JSON dos campos enviados do resumo de Indicadores

  Cenario: Solicitar o resumo de um indicador para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 1169
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo "ASKED" (campo deve ser ignorado) do resumo de Indicadores

  Cenario: Solicitar o resumo de um indicador para formato JSON
    Dado que enviamos o item 1169
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir um JSON dos campos enviados do resumo de Indicadores

  Cenario: Solicitar o resumo de um indicador para formato JSON sem parâmetro campos
    Dado que enviamos o item 1169
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar o resumo de um indicador para formato JSON sem parâmetro item
    Dado que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

  Cenario: Solicitar o resumo de um indicador para formato JSON com um campo inválido
    Dado que enviamos o item 1169
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de resumo de Indicadores
    Entao deve exibir um JSON dos campos enviados sem o campo "ASKED" (campo deve ser ignorado) do resumo de Indicadores