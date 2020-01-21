# language: pt

Funcionalidade: ListaMoedasSemanal

  Contexto: Limpar todos os parâmetros do contexto

##  Scenario 01:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSONP
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados

#  Scenario 02:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Semanal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo

##  Scenario 03:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

##  Scenario 04:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "list"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

##  Scenario 05:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON sem parâmetro JSONP
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um JSON da Lista de Moedas da Semana com os campos enviados

##  Scenario 06:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir uma Lista de Moedas da Semana com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA (campo deve ser ignorado)

#  Scenario 07:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato CSV
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um CSV contendo os campos enviados da Lista de Moedas Semanal

#  Scenario 08:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato CSV após inclusão de novos valores
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Semanal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Moedas Semanal

#  Scenario 09:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato CSV sem parâmetro campos
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 10:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato CSV sem parâmetro moeda
    Dado que enviamos o formato "csv"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

#  Scenario 11:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato CSV com um campo inválido
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um CSV dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas Semanal

#  Scenario 12:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um JSON dos campos enviados da Lista de Moedas Semanal

#  Scenario 13:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON após inclusão de novos valores
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Lista de Moedas Semanal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas Semanal

#  Scenario 14:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON sem parâmetro campos
    Dado que enviamos a moeda de ID 18
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 15:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON sem parâmetro moeda
    Dado que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

#  Scenario 16:
  Cenario: Solicitar todos os dados de uma moeda na semana para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Lista de Moedas Semanal
    Entao deve exibir um JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas Semanal
