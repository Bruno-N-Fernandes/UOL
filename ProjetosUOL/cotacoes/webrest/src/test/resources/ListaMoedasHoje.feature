# language: pt

Funcionalidade: ListaMoedasHoje

  Contexto: Limpar todos os parâmetros do contexto

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma Lista de Moedas De Hoje com a funcao de callback contendo o JSON dos campos enviados

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "list"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro JSONP
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma Lista de Moedas De Hoje com o JSON dos campos enviados

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma Lista de Moedas De Hoje com a funcao de callback contendo o JSON dos campos enviados sem o campo DATA (campo deve ser ignorado)

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato CSV
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir um CSV contendo os campos enviados da Lista de Moedas De Hoje

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato CSV sem parâmetro campos
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato CSV sem parâmetro moeda
    Dado que enviamos o formato "csv"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato CSV com um campo inválido
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir um CSV dos campos enviados sem o campo DATA (campo deve ser ignorado) da Lista de Moedas De Hoje

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma Lista de Moedas De Hoje com o JSON dos campos enviados

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro campos
    Dado que enviamos a moeda de ID 18
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON sem parâmetro moeda
    Dado que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma Lista de Moedas De Hoje com o JSON dos campos enviados sem o campo DATA (campo deve ser ignorado)

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSON após inclusão de novos valores
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação da Lista de Moedas De Hoje
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir um JSON dos campos enviados com novo valor no topo da Lista de Moedas De Hoje

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "list"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação da Lista de Moedas De Hoje
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados com novo valor no topo da Lista de Moedas De Hoje

  Cenario: Solicitar todos os dados de uma moeda na data atual para formato CSV após inclusão de novos valores
    Dado que enviamos o formato "csv"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação da Lista de Moedas De Hoje
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação da Lista de Moedas De Hoje
    Entao deve exibir um CSV contendo os campos enviados com o novo valor no topo da Lista de Moedas De Hoje