# language: pt

Funcionalidade: ResumoMoeda

  Contexto: Limpar todos os parâmetros do contexto

    # Cenario 1
  Cenario: Solicitar o resumo de uma moeda na data atual para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados do Resumo da Moeda

    # Cenario 2
  Cenario: Solicitar o resumo de uma moeda na data atual para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 18
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

    # Cenario 3
  Cenario: Solicitar o resumo de uma moeda para formato JSONP sem parâmetro moeda
    Dado que enviamos o formato JSONP "callback"
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir mensagem de erro com status "400" e causa "Required Integer parameter 'currency' is not present"

    # Cenario 4
  Cenario: Solicitar o resumo de uma moeda para formato JSON sem o parâmetro JSONP
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir um JSON dos campos enviados do Resumo da Moeda

    # Cenario 5
  Cenario: Solicitar o resumo de uma moeda para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos enviados sem o campo "DATA" (campo deve ser ignorado) do Resumo da Moeda

    # Cenario 6
  Cenario: Solicitar o resumo de uma moeda para formato JSON com um campo inválido
    Dado que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "DATA"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir um JSON dos campos enviados sem o campo DATA (campo deve ser ignorado) do Resumo da Moeda

    # Cenario 7
  Cenario: Solicitar o resumo de uma moeda na data atual para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Resumo da Moeda
    E que incluímos novos valores para essa moeda no mesmo dia
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do Resumo da Moeda

    # Cenario 8
  Cenario: Solicitar o resumo de uma moeda no dia seguinte para formato JSONP após inclusão de novos valores
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 18
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    E processamos a solicitação de Resumo da Moeda
    E que incluímos novos valores para essa moeda no dia seguinte
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir uma lista com a funcao de callback contendo o JSON dos campos atualizados do dia seguinte do Resumo da Moeda

    # Cenario 9
  Cenario: Solicitar os dados de uma moeda sem cotação na data atual
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 84
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir os dados disponíveis da ultima cotação da moeda em 10/07/2017

     # Cenario 10
  Cenario: Solicitar os dados de uma moeda sem cotação
    Dado que enviamos o formato JSONP "callback"
    E que enviamos a moeda de ID 85
    E que enviamos os campos: "id", "name", "bidvalue", "askvalue", "maxbid", "minbid", "variationbid", "variationpercentbid","openbidvalue" e "date"
    Quando processamos a solicitação de Resumo da Moeda
    Entao deve exibir dados de moedas vazios

