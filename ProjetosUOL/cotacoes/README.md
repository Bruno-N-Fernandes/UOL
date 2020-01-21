# Cotações em V2

## DAP
![Cotacoes] (cotacoes.jpg)

## A aplicação usa as seguintes tecnologias:
SpringBoot

MySQL

Hibernate

Java OpenJdk 

Docker


## Banco de Dados
Por enquanto a aplicação está configurada para acessar o MySQL da minha máquina local que está rodando o contâiner.  

Configure as properties do MySQL no arquivo application.properties da seguinte maneira:

Troque o IP em spring.datasource.url

Troque o usuário e a senha

Verifique que o usuário tenha acesso remoto! É possível fazer um teste para verificar isso, basta tentar criar uma nova conexão no MySQL Workbench trocando o Hostname por um IP remoto.


## Liberação de versão
### Configuração de ambiente:
Configurar o **~/m2/settings.xml** 



	<servers>  
	    <server>	    
	      <id>uol</id>  	          
	      <username>{usuario}</username>	      
	      <password>{senha}</password>	      
	      <configuration>	      
	        <email>{email}@uolinc.com</email>	        
	      </configuration>      
	    </server>    
	</servers>
  

  
### Executar docker sem sudo:
Para evitar problemas com as permissões da aplicação é aconselhado que o docker seja configurado para executar sem sudo:

sudo gpasswd -a $USER docker (Adicionar se usuário ao grupo do docker)

newgrp docker (Atualizar o grupo)

LINK: https://askubuntu.com/questions/477551/how-can-i-use-docker-without-sudo


### Para liberar a versão seguir o procedimento:
Executar:

**mvn release:prepare -Dresume=false -P release -e** *(Este comando realiza todo o procedimento de execução de testes automatizados, cria o tag no git e envia para o servidor, gera a imagem do docker e faz o push da imagem para o kubernetes)*


## Para rodar a aplicação em container rode os seguintes comandos no terminal:
cd cotacoes

mvn package -DskipTests -e -P release

docker build -t cotacoes .

docker run cotacoes


## Para testar que a aplicação está rodando acesse a seguinte URL no browser:
http://IPDoContainer:8080/currency/list?fields=id,name,countries

Obs: não esqueça de substituir a variavel IPDoContainer. 

Para descobrir o IP do container, rode:

sudo docker exec IDdoContainer ifconfig

Para descobrir o ID do container "cotacoes", rode:

sudo docker ps


## Trocar versão no Kubernets
### Configuração de ambiente:
Realizar configuração conforme link: 

https://confluence.intranet.uol.com.br/confluence/display/CONTUOL/Kubernetes+Sandbox

Para QA realizar as operações de login utilizando o ambiente de DEV: https://login.service.infra.development.kubernetes.intranet:5555/


### Para liberar a versão seguir o procedimento:
kubectl get pods *(Lista as pods em execução)*

kubectl edit deployment <Nome da raiz da POD> *(Achar o campo image, trocar versão para a mais nova e salvar)*

kubectl delete pods <Nome da pod> *(Para acelerar o processo de atualização, pode ser forçada a eliminação da POD, mas se não fizer naad ele faz a atualização automaticamente)*


##Links Auxiliares
Requisição no ambiente de Development:
http://nginx.cotacoes.svc.cluster.development.kubernetes.intranet/currency/list?fields=id,name,countries

Kibana - Sistema de log:
http://kibana-logging.kube-system.svc.cluster.development.kubernetes.intranet/app/kibana

Acesso aos wire frames:
http://ik3huk.axshare.com

Senha: #economiapgv22017#

Link do prometheus (Analise de métricas):
http://prometheus.kube-system.svc.cluster.development.kubernetes.intranet/graph

Links de autenticação dos ambientes
DEV:
https://login.service.infra.development.kubernetes.intranet:5555

PROD:
https://login.service.infra.production.kubernetes.intranet:5555

Ambientes Kubernetes
DEV:
kubectl config use-context development

PROD:
kubectl config use-context production