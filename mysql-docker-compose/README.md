## Docker compose mysql 8.0.32
Arquivo para executar um container docker com o mysql. 
Cria o banco de dados `pcr-db-dev` com usuario admin e senha admin;

### Docker comandos principais

Inicializar container com docker compose
```bash
docker compose up -d
```

Docker logs
```bash
docker container logs -f ID_OU_NOME_CONTAINER
```

Acessar container
```bash
docker exec -it CONTAINER_NAME /bin/bash
```

Verificar status do mysql
```bash
service mysql status
```

Abrir o mysql no terminal
```bash
mysql -u admin -p
```

Comandos mysql
```sh
> SHOW GLOBAL VARIABLES LIKE 'PORT';
``` 

```sh
> SHOW DATABASES;
```

```sh
> USE NOME_DO_BD;
```

Sair do mysql
```sh
> exit
```

Parar container e remover volume e conexão com docker compose
```sh
docker compose down -v
```

Parar container e remove conexão e imagem docker
```sh
docker-compose down --rmi local
```

Remover imagens e volumes
```bash
docker compose down --rmi all -v
```

Listar Container
```bash
docker container ls -a
```

Remover container
```bash
docker container rm ID_CONTAINER
```

Parar todos os contêineres em execução
```bash
docker stop $(docker ps -a -q)
```

Excluir todos os contêineres parados
```bash
docker rm $(docker ps -a -q)
```


### Diretório dos volumes principais mysql

Inicializar dados no bancos
```sh
- './docker/db/sql-init:/docker-entrypoint-initdb.d/:ro'
```

Salvar bd na maquina hospedeira
```sh
- './docker/db/data:/var/lib/mysql'
```

Arquivos de configuração
```sh
- './docker/db/my.cnf:/etc/mysql/conf.d/my.cnf'
```

diretorio local padrão dos volumes
```sh
/var/lib/docker/volumes/
```

### Docker comandos básicos

| Comandos                                      | Descrição                                 |
|-----------------------------------------------|-------------------------------------------|
| docker container ls -a                        | Listar container                          |
| docker ps -a -q                               | Listar container (-q para exibir apenas id) |
| docker image ls -a                            | Listar Imagens                            |
| docker volume ls                              | Listar volumes                            |
| docker container rm ID_CONTAINER              | Remover container                         |
| docker rmi -f ID_IMAGE                        | Remover imagem                            |
| docker volume rm ID_VOLUME                    | Remover volume                            |
| docker events                                 | Inspecionar o que tá acontencendo (usar aba separada) |
| docker stats                                  | Estatisticas da maquina com uso de containers em execução (usar aba separada)|
| docker top CONTAINER_ID                       | Conferir o processo que está sendo executado no momento  |
| docker run -p 81:81 -d -m 512m --cpu-quota 50000 IMAGE_NAME  |Executar container com parametros de memória e cpu de uso limitados |
| docker container stop CONTAINER_ID_OU_NAME   | Parar container                            |
| docker system df                             | Informações do sistema do docker           |
| docker container inspect CONTAINER_ID        | Inspecionar o container                    |
|                                                |                                            |



___
### Docker Instalação

> https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-on-ubuntu-20-04-pt

Verificar se o docker está instalado
```bash
$ service docker.io status
Unit docker.io.service could not be found.
```

Verificar versão do kernel
```bash
uname -a
```

Atualizar pacotes
```bash
apt-get update
```

Instalar Docker
```bash
apt-get install -y docker.io
```

Versão docker
```bash
docker -v
```
```sh
docker --version
```

```bash
docker version
```

Informações docker
```bash
docker info
```

Teste docker run
```sh
docker run hello-world
```

Ver logs docker
```sh
docker logs -f 0
```

Comando remove todos os contêineres não utilizados
```bash
docker system prune -a
```

Remove todos os volumes não utilizados
```bash
docker volume prune
```

___
#### Docker Instalar via documentação

Link com informações para instalar o docker
> https://docs.docker.com/engine/install/ubuntu/#install-from-a-package

```sh
docker version
```

```sh
docker compose version
```

Seguir passos descritos em `Install using the repository`

1. Set up the repository

Update the apt package index and install packages to allow apt to use a repository over HTTPS:
```sh
 sudo apt-get update
 sudo apt-get install \
    ca-certificates \
    curl \
    gnupg \
    lsb-release
```

Add Docker’s official GPG key:
```sh
 sudo mkdir -m 0755 -p /etc/apt/keyrings
 curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
```

comando com treço alterado `$(lsb_release -cs) stable` para  `focal stable`
```sh
echo   "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/ubuntu \
  $(focal) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null

```

2. Install Docker Engine

Install Docker Engine, containerd, and Docker Compose.
```sh
sudo apt-get update
```
```sh
sudo chmod a+r /etc/apt/keyrings/docker.gpg
```
```sh
sudo apt-get update
```
```sh
sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin
```

```sh
horta@horta:~$ docker version
Client: Docker Engine - Community
 Version:           23.0.1
 API version:       1.42
 Go version:        go1.19.5
 Git commit:        a5ee5b1
 Built:             Thu Feb  9 19:46:56 2023
 OS/Arch:           linux/amd64
 Context:           default
permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/version": dial unix /var/run/docker.sock: connect: permission denied

```

Para corrigir `permission denied while trying to connect to the Docker daemon socket...`
seguir os passos do link `https://docs.docker.com/engine/install/linux-postinstall/`

Comandos pós instalação o docker

```sh
$ sudo groupadd docker
grupo 'docker' já existe
$ sudo usermod -aG docker $USER
$ newgrp docker
```

___
#### Docker compose instalação

> https://docs.docker.com/compose/install/linux/#install-the-plugin-manually

```bash
$  DOCKER_CONFIG=${DOCKER_CONFIG:-$HOME/.docker}
$  mkdir -p $DOCKER_CONFIG/cli-plugins
$  curl -SL https://github.com/docker/compose/releases/download/v2.16.0/docker-compose-linux-x86_64 -o $DOCKER_CONFIG/cli-plugins/docker-compose

$chmod +x $DOCKER_CONFIG/cli-plugins/docker-compose

$ docker compose version
Docker Compose version v2.16.0
```

Outra forma de instalar docker compose
```bash
$ sudo apt install docker-compose

$ docker-compose version
docker-compose version 1.29.2, build unknown
docker-py version: 5.0.3
CPython version: 3.10.6
OpenSSL version: OpenSSL 3.0.2 15 Mar 2022
```

```bash
$ docker compose version
Docker Compose version v2.5.0

$ docker --version
Docker version 20.10.14, build a224086349

$ docker version
Client:
 Version:           20.10.12
 API version:       1.41
 ...
Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/version": dial unix /var/run/docker.sock: connect: permission denied
...
```

Corrigir erro de permissão
```sh
$ docker run hello-world
docker: Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Post "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/containers/create": dial unix /var/run/docker.sock: connect: permission denied.
See 'docker run --help'.

sudo chmod 666 /var/run/docker.sock
```
> https://www.digitalocean.com/community/questions/how-to-fix-docker-got-permission-denied-while-trying-to-connect-to-the-docker-daemon-socket


Gerar jar com maven (dentro da raiz do projeto):
```bash
mvn clean package -DskipTests 
```