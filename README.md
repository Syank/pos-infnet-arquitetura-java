# Concessionária Global
Repositório para o projeto da disciplina Arquitetura Java da pós graduação Arquitetura de Software

Este repositório contêm o projeto exigido para a disciplina Arquitetura Java, sendo o projeto uma aplicação Java desenvolvida no framework spring.

## Execução
Para executar o projeto, realize o clone do repositório em sua máquina e abra a pasta *src* em sua IDE de preferência.

O projeto foi construído em cima da versão 21 do Java, por isso, é necessário que utilize a versão 21+ para a execução do projeto.

A ferramenta Maven foi utilizada como ferramenta de construção (build) do projeto, a mesma se encarregará de realizar o download das dependências necessárias para a execução do projeto.

## Interfaces
O projeto foi desenvolvido utilizando o banco H2, que é executado em memória, para facilitar utilização do projeto.
Para acessar a interface do banco, após a execução do projeto, o banco pode ser acessado pela URL localhost:8080/h2-console, com o usuário "sa" e senha "admin".
Se atente à URL de conexão do banco "jdbc:h2:mem:concessionaria;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1"

Para utilização da API, pode-se utilizar a interface do Swagger, acessível através da URL "http://localhost:8080/swagger-ui/index.html"

## Carregamento dos dados
Dentro de *src/data* encontram-se arquivos respectivos para o carregamento dos dados de cada entidade da aplicação
