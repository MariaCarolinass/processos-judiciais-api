# Gestão de Processos Judiciais com Agendamento de Audiências

API para gerenciar processos judiciais e suas respectivas audiências.

## Requisitos Funcionais 

1. Cadastro de Processo Judicial

    Campos: número do processo (único), vara, comarca, assunto, status (ATIVO, ARQUIVADO, SUSPENSO)
    
    Deve permitir listar e filtrar por status e comarca

2. Agendamento de Audiências

    Cada processo pode ter uma ou mais audiências
    
    Campos: data e hora, tipo de audiência (CONCILIAÇÃO, INSTRUÇÃO, JULGAMENTO), local
    
    Não deve permitir sobreposição de audiências na mesma vara e local

3. Consulta de Agenda

    Endpoint que retorna a agenda de audiências de uma comarca em um determinado dia 

## Regras de Negócio

- O número do processo deve seguir o formato 0000000-00.0000.0.00.0000 (validar via regex)
- Um processo arquivado ou suspenso não pode ter novas audiências agendadas
- Uma audiência só pode ser marcada em dias úteis (segunda a sexta, sem feriados – considerar apenas dias da semana) 

## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JJWT (Java JWT)
- Springdoc OpenAPI (Swagger UI)
- Spring Data JPA
- H2 Database

## Autenticação com JWT

## Documentação da API

### Endpoints principais

## Configuração

### Requisitos

- JDK 17+
- Maven

### Executando API

## Banco de Dados

## Licença

[MIT License](https://github.com/MariaCarolinass/processos-judiciais-api/blob/main/LICENSE)
