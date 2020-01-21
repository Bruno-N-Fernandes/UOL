# language: pt

Funcionalidade: ListaAcoes

  Cenario: Solicitar todos os dados de acoes para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    Quando processamos a solicitação de todas Lista Acao
    Entao deve exibir um JSON com os campos enviados da Lista Acao

  Cenario: Solicitar todos os dados de acoes para formato JSON sem parâmetro campos
    Dado que enviamos o formato "json"
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de acoes para formato JSON com um campo inválido
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "TIPE"
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSON dos campos enviados sem o campo TIPE da Lista Acao

  Cenario: Buscar acoes COTEMINAS para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "COTEMINAS"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 resultados com COTEMINAS da Lista Acao

  Cenario: Buscar acoes Companhia de Tecidos Norte de Minas para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "Companhia de Tecidos Norte de Minas"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 resultados com Companhia de Tecidos Norte de Minas da Lista Acao

  Cenario: Buscar acoes CTNM para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 resultados com CTNM da Lista Acao

  Cenario: Buscar acoes CTNM4 para formato JSON
    Dado os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM4"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 1 resultado com CTNM4 da Lista Acao

  Cenario: Buscar acoes CTNM4.SA para formato JSON
    Dado os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM4.SA"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 1 resultado com CTNM4.SA da Lista Acao

  Cenario: Buscar acoes Participações formato JSON
    Dado os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "Participações"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir todos os resultados com Participacoes da Lista Acao

  Cenario: Solicitar todos os dados de acoes para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    Quando processamos a solicitação de todas Lista Acao
    Entao deve exibir um JSONP com os campos enviados da Lista Acao

  Cenario: Solicitar todos os dados de acoes para formato JSONP sem parâmetro campos
    Dado que enviamos o formato JSONP "callback"
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir mensagem de erro com status "400" e causa "Required String parameter 'fields' is not present"

  Cenario: Solicitar todos os dados de acoes para formato JSONP com um campo inválido
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "TIPE"
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSONP dos campos enviados sem o campo TIPE da Lista Acao

  Cenario: Buscar acoes COTEMINAS para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "COTEMINAS"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 JSONP com COTEMINAS da Lista Acao

  Cenario: Buscar acoes Companhia de Tecidos Norte de Minas para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "Companhia de Tecidos Norte de Minas"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 JSONP com Companhia de Tecidos Norte de Minas da Lista Acao

  Cenario: Buscar acoes CTNM para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 2 JSONP com CTNM da Lista Acao

  Cenario: Buscar acoes CTNM4 para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM4"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 1 JSONP com CTNM4 da Lista Acao

  Cenario: Buscar acoes CTNM4.SA para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM4.SA"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 1 JSONP com CTNM4.SA da Lista Acao

  Cenario: Buscar acoes Participações formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "Participações"
    E que enviamos o tamanho 3
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir todos os JSONP com Participacoes da Lista Acao

  Cenario: Buscar acoes CTNM para formato JSONP após atualização da lista com nova ação
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "CTNM"
    E que enviamos o tamanho 3
    E processamos a solicitação de Lista Acao
    Entao deve exibir 2 JSONP com CTNM da Lista Acao
    E que incluimos nova acao CTNM na Lista Acao
    E chamamos o job de atualizar as ações por letra
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir 3 JSONP com CTNM da Lista Acao incluindo a ação nova que foi inserida

  Cenario: Buscar acoes com historico de mais de 1 ano para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "BRC"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSON com os dados da acao BBRC11.SA

  Cenario: Buscar acoes com historico de mais de 1 ano para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation" e "type"
    E as letras "BRC"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir o JSONP com os dados da acao BBRC11.SA

  Cenario: Buscar acoes que tem dados atualizados em todos os periodos até a data atual para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation", "type" e "services"
    E as letras "BOVA11.SA"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSON com os dados da acao em todos os periodos

  Cenario: Buscar acoes que tem dados atualizados em todos os periodos até a data atual para formato JSONP
    Dado que enviamos o formato JSONP "callback"
    E os campos: "id", "name", "companies", "exchange", "abbreviation", "type" e "services"
    E as letras "BOVA11.SA"
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSONP com os dados da acao em todos os periodos

  ##Continuar a partir deste cenario
  Cenario: Buscar acoes que tem dados atualizados da última até a data atual semana para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation", "type" e "services"
    E as letras ""
    E que enviamos o tamanho 2
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSON com os dados da acao da última semana até a data atual

Cenario: Buscar de novo acoes que tem dados atualizados em todos os periodos até a data atual para formato JSON
    Dado que enviamos o formato "json"
    E os campos: "id", "name", "companies", "exchange", "abbreviation", "type" e "services"
    E as letras "AFLU5.SA"
    E que enviamos o tamanho 200
    Quando processamos a solicitação de Lista Acao
    Entao deve exibir um JSON com os dados da acao afluente com apenas a informacao de ano