# language: pt

Funcionalidade: ResumoAcoes

  Contexto: Limpar todos os parâmetros do contexto

  Cenario: Solicitar o resumo de uma acao para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo de ação

  Cenario: Solicitar o resumo de uma acao para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar o resumo de uma acao para formato JSONP sem parâmetro item
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

  Cenario: Solicitar o resumo de uma acao para formato JSON sem o parâmetro JSONP
    Dado que enviamos o item 232
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir um JSON dos campos enviados do resumo de ação

  Cenario: Solicitar o resumo de uma acao para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 232
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo "ASKED" (campo deve ser ignorado) do resumo de ação

  Cenario: Solicitar o resumo de uma acao para formato JSON
    Dado que enviamos o item 232
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir um JSON dos campos enviados do resumo de ação

  Cenario: Solicitar o resumo de uma acao para formato JSON sem parâmetro campos
    Dado que enviamos o item 232
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar o resumo de uma acao para formato JSON sem parâmetro item
    Dado que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

  Cenario: Solicitar o resumo de uma acao para formato JSON com um campo inválido
    Dado que enviamos o item 232
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir um JSON dos campos enviados sem o campo "ASKED" (campo deve ser ignorado) do resumo de ação

  Cenario: Solicitar os dados de uma acao sem negociacao na data atual
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 84
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir os dados disponíveis da ultima negociacao da acao em 10/07/2017

  Cenario: Solicitar os dados de uma acao sem negociacao
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o item 85
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de resumo de acao
    Entao deve exibir dados de acoes vazios
