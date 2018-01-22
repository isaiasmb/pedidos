# pedidos
## Requisitos
1. Java 8 instalado
2. MySQL 5.x

## Instalação
1. clonar repositório
2. No arquivo "src/main/resources/application.properties" ajustar usuário e senha do banco MySQL
3. No arquivo "src/main/java/com/pedidos/api/cors/CorsFilter.java" alterar a variável allowedOrigin para o caminho da aplicação frontend (localhost:8000).
4. Na pasta raíz do projeto rodar o comando mvn spring-boot:run.
