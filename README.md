# ⚖️ Gestão de Processos Judiciais com Agendamento de Audiências

API REST desenvolvida com **Spring Boot** para o gerenciamento de processos judiciais e agendamento de audiências, com autenticação JWT e documentação Swagger/OpenAPI.

---

## ✅ Funcionalidades

### 1. **Cadastro de Processo Judicial**
- Campos: número do processo (único), vara, comarca, assunto, status (`ATIVO`, `ARQUIVADO`, `SUSPENSO`)
- Listagem e filtros por **status** e **comarca**

### 2. **Agendamento de Audiências**
- Um processo pode conter múltiplas audiências
- Campos: data e hora, tipo (`CONCILIAÇÃO`, `INSTRUÇÃO`, `JULGAMENTO`), local
- Não permite **audiências sobrepostas** na mesma vara e local

### 3. **Consulta de Agenda**
- Endpoint para consultar a agenda de audiências por **comarca** e **data**

---

## 📜 Regras de Negócio

- O número do processo deve seguir o formato: `0000000-00.0000.0.00.0000` (validação via regex)
- Processos com status `ARQUIVADO` ou `SUSPENSO` **não podem receber novas audiências**
- Audiências só podem ser marcadas em **dias úteis** (segunda a sexta)

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (Web, Security, Data JPA)
- **JWT** (Autenticação com `jjwt`)
- **Springdoc OpenAPI** (Swagger UI)
- **H2 Database** (padrão) ou PostgreSQL
- **Jakarta Servlet API**
- **JUnit 5**
- **Maven**

---

## ⚙️ Como Executar

### 🔧 Requisitos

Pré-instalar:

- JDK 17+
- Maven 3+

### ▶️ Passos

```bash
# Clone o repositório
git clone https://github.com/MariaCarolinass/processos-judiciais-api.git
cd processos-judiciais-api

# Rodar a aplicação
mvn spring-boot:run
```

Acesse a aplicação:  
[http://localhost:8080](http://localhost:8080)

---

## 🔐 Autenticação com JWT

### 1. Obter um token de acesso

```bash
curl -X POST "http://localhost:8080/auth/login?username=admin&password=123"
```

Por padrão use admin como username e 123 como senha.

### 2. Enviar o token no header das requisições:

```
Authorization: Bearer SEU_TOKEN
```

---

## 📌 Exemplos de Requisições

### 📁 Processos

#### ➕ Adicionar processo

```bash
curl -X POST http://localhost:8080/api/v1/processos   -H "Authorization: Bearer SEU_TOKEN"   -H "Content-Type: application/json"   -d '{
        "numProcesso": "1234567-89.2024.0.12.3456",
        "status": "ATIVO",
        "vara": "Vara Cível",
        "comarca": "Natal",
        "assunto": "Cobrança"
      }'
```

#### 📄 Listar todos

```bash
curl -H "Authorization: Bearer SEU_TOKEN" http://localhost:8080/api/v1/processos
```

#### 🔍 Filtrar por status e comarca

```bash
curl -H "Authorization: Bearer SEU_TOKEN"   "http://localhost:8080/api/v1/processos?status=ATIVO&comarca=Natal"
```

---

### 🕒 Audiências

#### ➕ Adicionar audiência

```bash
curl -X POST http://localhost:8080/api/v1/audiencias   -H "Authorization: Bearer SEU_TOKEN"   -H "Content-Type: application/json"   -d '{
        "data": "2025-07-01",
        "hora": "14:00:00",
        "local": "Fórum Central",
        "tipoAudiencia": "INSTRUCAO",
        "processo": { "id": 1 }
      }'
```

#### 📄 Listar todas

```bash
curl -H "Authorization: Bearer SEU_TOKEN" http://localhost:8080/api/v1/audiencias
```

#### 📅 Consultar agenda

```bash
curl -H "Authorization: Bearer SEU_TOKEN"   "http://localhost:8080/api/v1/audiencias/agenda?comarca=Natal&data=2025-07-01"
```

---

## 📘 Documentação da API

Acesse via Swagger UI:

```
http://localhost:8080/swagger-ui/index.html
```

### Endpoints principais

| Verbo | Rota                                | Descrição                                     |
|-------|-------------------------------------|-----------------------------------------------|
| POST  | `/auth/login`                       | Autenticação e geração de token JWT           |
| GET   | `/api/v1/processos`                 | Listagem e filtro de processos                |
| POST  | `/api/v1/processos`                 | Cadastro de novo processo                     |
| GET   | `/api/v1/audiencias`                | Listagem de audiências                        |
| POST  | `/api/v1/audiencias`                | Agendamento de nova audiência                 |
| GET   | `/api/v1/audiencias/agenda`         | Consulta de agenda por comarca e data         |

---

## 🔒 Observações importantes

- ✔️ O número do processo deve seguir o padrão `0000000-00.0000.0.00.0000`
- ❌ Não é permitido agendar audiência em fim de semana
- ❌ Não é permitido agendamento duplicado (mesmo local e hora)
- 🔐 Todos os endpoints (exceto `/auth/login`) exigem **token JWT**

---

## 📄 Licença

Distribuído sob a [Licença MIT](https://github.com/MariaCarolinass/processos-judiciais-api/blob/main/LICENSE).
