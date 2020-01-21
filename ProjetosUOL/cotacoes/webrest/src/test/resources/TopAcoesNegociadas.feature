# language: pt

Funcionalidade: TopAcoesNegociadas

  Contexto: Limpar todos os parâmetros do contexto

#    1
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do top acoes negociadas

#    2
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#    3
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSONP sem parâmetro tamanho
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'size' is not present"

#    4
  Cenario: Solicitar as acoes com maiores negociadas do dia sem o formato JSONP
    Dado que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir um JSON dos campos enviados do top acoes negociadas

#    5
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "DATA", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo "DATA" (campo deve ser ignorado) do top acoes negociadas

#    6
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSONP após inclusão de novos valores (nova ordenação)
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    E processamos a solicitação de top acoes negociadas
    E validamos a ordem das 2 primeiras ações: "A" e "B" de top acoes negociadas no formato JSONP
    E que incluímos novos valores para a ação "B" acima dos valores de "A" ("B" com mais negociação que "A")
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir uma lista no formato JSONP de top acoes negociadas com nova ordem: "B" e "A"

#    7
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSON
    Dado que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir um JSON dos campos enviados do top acoes negociadas

#    8
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSON sem parâmetro campos
    Dado que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#    9
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSON sem parâmetro tamanho
    Dado que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'size' is not present"

#    9
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSON com um campo inválido
    Dado que enviamos os campos: "DATA", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir uma lista contendo o JSON dos campos enviados sem o campo "DATA" (campo deve ser ignorado) do top acoes negociadas

#    10
  Cenario: Solicitar as acoes com maiores negociadas do dia para formato JSON após inclusão de novos valores (nova ordenação)
    Dado que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E que enviamos o tamanho 3
    E processamos a solicitação de top acoes negociadas
    E validamos a ordem das 2 primeiras ações: "A" e "B" de top acoes negociadas no formato JSON
    E que incluímos novos valores para a ação "B" acima dos valores de "A" ("B" com mais negociação que "A")
    Quando processamos a solicitação de top acoes negociadas
    Entao deve exibir uma lista no formato JSON de top acoes negociadas com nova ordem: "B" e "A"