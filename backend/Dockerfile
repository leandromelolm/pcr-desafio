FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

# Fazer antes:
# 1. Alterar a variável do application.properties para dev
# 2. Gerar o jar com o comando mvn clean package -DskipTests
# 3. Verificar se a imagem "pcr-desafio-app" já existe.
#    Caso exista, para criar uma nova imagem com novas configurações
#    é preciso remover a imagem que foi criada.
#    listar imagem: docker image ls
#    remover imagem pelo id (pcr-desafio-app): docker rmi -f IMAGE_ID
# 4. para subir e iniciar os 'Containers' docker: docker compose up -d
#    Comando do docker compose deve ser executado no mesmo nível da pasta docker-compose.yml
# 5. Conexão bd é feita com os valores declarados no arquivo .env
