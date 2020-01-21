# language: pt

Funcionalidade: ListaMoedasMensal

  Contexto: Limpar todos os parâmetros do contexto

#  Scenario 01:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSONP
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir uma Lista de Moedas Mensal com a funcao de callback contendo o JSON dos campos enviados

#  Scenario 02:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 03:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "list"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

#  Scenario 04:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON (sem parâmetro JSONP)
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um JSON dos campos enviados da Lista de Moedas Mensal

#  Scenario 05:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas Mensal

#  Scenario 06:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato CSV
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um CSV contendo os campos enviados da Lista de Moedas Mensal

#  Scenario 07:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato CSV sem parâmetro campos
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 08:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato CSV sem parâmetro moeda
    Dado que enviamos o formato "csv"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

#  Scenario 09:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato CSV com um campo inválido
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um CSV dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas Mensal

#  Scenario 10:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um JSON dos campos enviados da Lista de Moedas Mensal

#  Scenario 11:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON sem parâmetro campos
    Dado que enviamos a moeda de ID 18
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

#  Scenario 12:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON sem parâmetro moeda
    Dado que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

#  Scenario 13:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas Mensal

#  Scenario 14:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E for processada a solicitação de Lista de Moedas Mensal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo da Lista de Moedas Mensal

#  Scenario 15:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato CSV após inclusão de novos valores
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E for processada a solicitação de Lista de Moedas Mensal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um CSV contendo os campos enviados com novo valor no topo da Lista de Moedas Mensal

#  Scenario 16:
  Cenario: Solicitar todos os dados de uma moeda no mês para formato JSON após inclusão de novos valores
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E for processada a solicitação de Lista de Moedas Mensal
    E que incluímos novos valores para essa moeda para um dia mais recente
    Quando for processada a solicitação de Lista de Moedas Mensal
    Entao deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas Mensal