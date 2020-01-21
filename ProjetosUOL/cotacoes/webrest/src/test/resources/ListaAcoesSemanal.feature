# language: pt

Funcionalidade: ListaAcoesSemanal

  Contexto: Limpar todos os parâmetros do contexto

##  Scenario 01:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSONP
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir uma Lista de Ações da Semana com a funcao de callback contendo o JSON dos campos enviados

#  Scenario 02:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista de Ações Semanal
    E que incluimos novos valores para o item 232 para um dia mais recente
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir uma Lista de Ações da Semana com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo

#  Scenario 03:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 04:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSONP sem parâmetro ação
    Dado que enviamos o formato JSONP "list"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

#  Scenario 05:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON sem parâmetro JSONP
    Dado que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um JSON da Lista de Ações da Semana com os campos enviados

#  Scenario 06:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir uma Lista de Ações da Semana com a funcao de callback contendo o JSON dos campos enviados sem o campo ASKED (campo deve ser ignorado)

#  Scenario 07:
  Cenario: Solicitar todos os dados de uma ação na semana para formato CSV
    Dado que enviamos o formato "csv"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um CSV contendo os campos enviados da Lista de Ações Semanal

#  Scenario 08:
  Cenario: Solicitar todos os dados de uma ação na semana para formato CSV após inclusão de novos valores
    Dado que enviamos o formato "csv"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista de Ações Semanal
    E que incluimos novos valores para o item 232 para um dia mais recente
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Ações Semanal

#  Scenario 09:
  Cenario: Solicitar todos os dados de uma ação na semana para formato CSV sem parâmetro campos
    Dado que enviamos o formato "csv"
    E que enviamos o item 232
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 10:
  Cenario: Solicitar todos os dados de uma ação na semana para formato CSV sem parâmetro ação
    Dado que enviamos o formato "csv"
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

#  Scenario 11:
  Cenario: Solicitar todos os dados de uma ação na semana para formato CSV com um campo inválido
    Dado que enviamos o formato "csv"
    E que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um CSV dos campos enviados sem o campo ASKED (campo deve ser ignorado) da Lista de Ações Semanal

#  Scenario 12:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON
    Dado que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um JSON da Lista de Ações da Semana com os campos enviados

#  Scenario 13:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON após inclusão de novos valores
    Dado que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    E processamos a solicitação de Lista de Ações Semanal
    E que incluimos novos valores para o item 232 para um dia mais recente
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Ações Semanal

#  Scenario 14:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON sem parâmetro campos
    Dado que enviamos o item 232
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 15:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON sem parâmetro ação
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ask", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'item' is not present"

#  Scenario 16:
  Cenario: Solicitar todos os dados de uma ação na semana para formato JSON com um campo inválido
    Dado que enviamos o item 232
    E que enviamos os campos: "date", "price", "exchangeasset", "high", "low", "open", "volume", "close","bid", "ASKED", "change" e "pctChange"
    Quando processamos a solicitação de Lista de Ações Semanal
    Entao deve exibir um JSON dos campos enviados sem o campo ASKED (campo deve ser ignorado) da Lista de Ações Semanal