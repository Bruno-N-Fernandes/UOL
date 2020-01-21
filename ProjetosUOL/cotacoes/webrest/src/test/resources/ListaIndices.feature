# language: pt

Funcionalidade: ListaIndice

  Cenario: Solicitar todos os dados de indices para formato JSON
    Dado os campos: "id", "name", "exchange", "abbreviation" e "type"
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir um JSON com os campos enviados da Lista Indice

  Cenario: Solicitar todos os dados de indices para formato JSON sem parâmetro campos
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de indices para formato JSON com um campo inválido
    Dado os campos: "id", "name", "exchange", "abbreviation" e "TIPE"
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Indice

  Cenario: Solicitar todos os dados de indices para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "exchange", "abbreviation" e "type"
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir um JSONP com os campos enviados da Lista Indice

  Cenario: Solicitar todos os dados de indices para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de indices para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "exchange", "abbreviation" e "TIPE"
    Quando processamos a solicitação de Lista Indice
    Entao deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Indice