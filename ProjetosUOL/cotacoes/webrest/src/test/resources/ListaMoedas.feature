# language: pt

Funcionalidade: ListaMoedas

	Cenario: Solicitar todos os dados de moedas para formato JSON 
		Dado que enviamos os campos: "id", "name", "codinvestor", "codunit", "codconversion", "converted" e "countries"
		Quando for processada a solicitação sem callback da Lista de Moedas
		Entao deve exibir um JSON com os campos enviados da Lista de Moedas

	Cenario: Solicitar todos os dados de moedas para formato JSON com um campo inválido
		Dado que enviamos os campos: "id", "name", "codinvestor", "codunit", "codconversion", "converted" e "COUNTRY"
		Quando for processada a solicitação sem callback da Lista de Moedas
		Entao deve exibir um JSON dos campos enviados sem o campo "COUNTRY" da Lista de Moedas
		
	Cenario: Solicitar todos os dados de moedas para formato JSONP
		Dado que enviamos o formato JSONP "callback"
		E que enviamos os campos: "id", "name", "codinvestor", "codunit", "codconversion", "converted" e "countries"
		Quando for processada a solicitação com callback da Lista de Moedas
		Entao deve exibir um JSONP com os campos enviados da Lista de Moedas
		
	Cenario: Solicitar todos os dados de moedas para formato JSONP com um campo inválido
		Dado que enviamos o formato JSONP "callback"
		E que enviamos os campos: "id", "name", "codinvestor", "codunit", "codconversion", "converted" e "COUNTRY"
		Quando for processada a solicitação com callback da Lista de Moedas
		Entao deve exibir um JSONP dos campos enviados sem o campo "COUNTRY" da Lista de Moedas