# language: pt

Funcionalidade: ResumoMultiplo

  Contexto: Limpar todos os parâmetros do contexto

#MOEDAS
#1
  Cenario: Solicitar o resumo multiplo para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON das moedas enviadas do resumo multiplo
#2
  Cenario: Solicitar o resumo multiplo para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#3
  Cenario: Solicitar o resumo multiplo sem o parâmetro JSONP
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON das moedas enviadas do resumo multiplo
#4
  Cenario: Solicitar o resumo multiplo para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON das moedas enviadas sem o campo "DATA" (campo deve ser ignorado) do resumo multiplo
#5
  Cenario: Solicitar o resumo multiplo para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para essas moedas no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON das moedas atualizadas do resumo multiplo
#6
  Cenario: Solicitar o resumo multiplo para formato JSONP (ordenação resposta)
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: "4", "5", "18"
    E que enviamos as moedas: "5", "18", "4"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: "5", "18", "4"
#7
  Cenario: Solicitar o resumo multiplo para formato JSON
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON das moedas enviadas do resumo multiplo
#8
  Cenario: Solicitar o resumo multiplo para formato JSON sem parâmetro campos
    Dado que enviamos as moedas: "4", "5", "18"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#9
  Cenario: Solicitar o resumo multiplo para formato JSON com um campo inválido
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON das moedas enviadas sem o campo "DATA" (campo deve ser ignorado) do resumo multiplo
#10
  Cenario: Solicitar o resumo multiplo para formato JSON após inclusão de novos valores
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para essas moedas no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON das moedas atualizadas do resumo multiplo
#11
  Cenario: Solicitar o resumo multiplo para formato JSON (ordenação resposta)
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: "4", "5", "18"
    E que enviamos as moedas: "5", "18", "4"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: "5", "18", "4"

#INDICES
#12
  Cenario: Solicitar o resumo multiplo para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos itens enviados do resumo multiplo
#13
  Cenario: Solicitar o resumo multiplo para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os itens: "232","10"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#14
  Cenario: Solicitar o resumo multiplo sem o parâmetro JSONP
    Dado que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos dos itens enviados do resumo multiplo
#15
  Cenario: Solicitar o resumo multiplo para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "CLOSED","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos intens enviados sem o campo "CLOSED" (campo deve ser ignorado) do resumo multiplo
#16
  Cenario: Solicitar o resumo multiplo para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para esses itens no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos dos itens atualizados do resumo multiplo
#17
  Cenario: Solicitar o resumo multiplo para formato JSONP (ordenação resposta)
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSONP com os itens na mesma ordem de solicitação: "232", "10"
    E que enviamos os itens: "10","232"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSONP com os itens na mesma ordem de solicitação: "10", "232"
#18
  Cenario: Solicitar o resumo multiplo para formato JSON
    Dado que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos dos itens enviados do resumo multiplo
#19
  Cenario: Solicitar o resumo multiplo para formato JSON sem parâmetro campos
    Dado que enviamos os itens: "232","10"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#20
  Cenario: Solicitar o resumo multiplo para formato JSON com um campo inválido
    Dado que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "CLOSED","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos dos itens enviados sem o campo "CLOSED" (campo deve ser ignorado) do resumo multiplo
#21
  Cenario: Solicitar o resumo multiplo para formato JSON após inclusão de novos valores
    Dado que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para esses itens no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos dos itens atualizados do resumo multiplo
#22
  Cenario: Solicitar o resumo multiplo para formato JSON (ordenação resposta)
    Dado que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSON com os itens na mesma ordem de solicitação: "232", "10"
    E que enviamos os itens: "10","232"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSON com os itens na mesma ordem de solicitação: "10", "232"

# #MOEDAS e INDICES
#23
  Cenario: Solicitar o resumo multiplo para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do resumo multiplo
#24
  Cenario: Solicitar o resumo multiplo para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#25
  Cenario: Solicitar o resumo multiplo sem o parâmetro JSONP
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos enviados do resumo multiplo
#26
  Cenario: Solicitar o resumo multiplo para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "DATA", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo "DATA" (campo deve ser ignorado) do resumo multiplo
#27
  Cenario: Solicitar o resumo multiplo para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para essas moedas e indices no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do resumo multiplo
#28
  Cenario: Solicitar o resumo multiplo para formato JSONP (ordenação resposta)
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSONP com as moedas na mesma ordem de solicitação: "4", "5", "18" e depois os itens na mesma ordem de solicitação: "232", "10"
    E que enviamos as moedas: "5", "18", "4"
    E que enviamos os itens: "10","232"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSONP com as moedas e os itens na mesma ordem de solicitação:"5", "18", "4" , "10", "232"
#29
  Cenario: Solicitar o resumo multiplo para formato JSON
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos enviados do resumo multiplo
#30
  Cenario: Solicitar o resumo multiplo para formato JSON sem parâmetro campos
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"
#31
  Cenario: Solicitar o resumo multiplo para formato JSON com um campo inválido
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "DATA", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir um JSON dos campos enviados sem o campo "DATA" (campo deve ser ignorado) do resumo multiplo
#32
  Cenario: Solicitar o resumo multiplo para formato JSON após inclusão de novos valores
    Dado que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E que incluímos novos valores para essas moedas e indices no mesmo dia
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista com o JSON dos campos atualizados do resumo multiplo
#33
  Cenario: Solicitar o resumo multiplo para formato JSON (ordenação resposta)
    Dado que enviamos o formato "json"
    E que enviamos as moedas: "4", "5", "18"
    E que enviamos os itens: "232","10"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação do resumo multiplo
    E entao deve exibir uma lista no formato JSON com as moedas na mesma ordem de solicitação: "4", "5", "18" e depois os itens na mesma ordem de solicitação: "232", "10"
    E que enviamos as moedas: "5", "18", "4"
    E que enviamos os itens: "10","232"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir uma lista no formato JSON com as moedas e os itens na mesma ordem de solicitação:"5", "18", "4" , "10", "232"
#34
  Cenario: Solicitar os dados de uma moeda sem cotação e uma acao sem negociacao na data atual
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "84", "84", "84"
    E que enviamos os itens: "84","84"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir os dados disponíveis da ultima cotação/negociação em 10/07/2017

  Cenario: Solicitar os dados de uma moeda sem cotação e uma acao sem negociacao
    Dado que enviamos o formato JSONP "callback"
    E que enviamos as moedas: "85", "85", "85"
    E que enviamos os itens: "85","85"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue", "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação do resumo multiplo
    Entao deve exibir dados de multiplos vazios

