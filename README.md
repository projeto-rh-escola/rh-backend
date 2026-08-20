# rh-backend

## Índice
- [📓 Sobre](#-sobre)
- [🚀 Tecnologias](#-tecnologias)
- [✨ Funcionalidades](#-funcionalidades)
- [⚙️ Instalação](#-instalação)
- [🧱 Estrutura do Projeto](#-estrutura-do-projeto)
- [💻 Autores](#-autores)

<br>

## 📓 Sobre
O **rh-backend** é uma API REST para gerenciamento de funcionários de um sistema de Recursos Humanos (RH). Desenvolvida com foco em Clean Code e organização de pacotes, a aplicação utiliza armazenamento em memória (ArrayList), garantindo a integridade dos dados por meio de validações rigorosas e tratamento global de exceções.

<br>

## 🚀 Tecnologias
- **Linguagem e Framework:** Java (JDK 17+) e Spring Boot 3
- **Web e Validação:** Spring Web e Jakarta Validation (Constraints e Regex)
- **Documentação:** Swagger UI (Springdoc OpenAPI)
- **Utilitários:** Lombok
- **Gerenciador de Build:** Maven

<br>

## ✨ Funcionalidades
* **CRUD em Memória:** Cadastro, listagem, busca (por ID e status), atualização e deleção utilizando `ArrayList`.
* **Validação de Entrada:** Bloqueio de dados inválidos ou nulos direto no Model (`@NotBlank`, `@NotNull`, `@Email`, `@Pattern`).
* **Regras de Negócio:** Prevenção de duplicidade, impedindo o cadastro de e-mails, nomes ou telefones já registrados no sistema.
* **Tratamento Global de Erros:** Interceptador automático (`@RestControllerAdvice`) localizado no pacote `handler` que formata erros de validação e de regras de negócio, retornando um JSON limpo e padronizado (`ErroPadrao`) para o Front-end.
* **Documentação em Interfaces:** Uso de uma interface separada (`FuncionarioSwagger`) dentro do pacote `controller` para as anotações do Swagger, mantendo a classe principal limpa e focada em rotas.

<br>

## ⚙️ Instalação
Certifique-se de ter o **Java JDK** e o **Maven** instalados na sua máquina.

```bash
# 1. Clone o repositório
git clone [https://github.com/projeto-rh-escola/rh-backend.git](https://github.com/projeto-rh-escola/rh-backend.git)

# 2. Entre na pasta do projeto
cd rh-backend

# 3. Execute o projeto usando o Maven Wrapper
./mvnw spring-boot:run # (No Windows, utilize: mvnw.cmd spring-boot:run)

# 4. Acesse a documentação interativa no navegador
# http://localhost:8080/swagger-ui/index.html
```

## 🧱 Estrutura do Projeto
```bash
rh-backend/
├── src/main/java/com/picpay/rh/
│   ├── controller/
│   │   ├── FuncionarioController.java
│   │   └── FuncionarioSwagger.java
│   ├── exception/
│   │   ├── DadoDuplicadoException.java
│   │   └── FuncionarioNaoEncontradoException.java
│   ├── handler/
│   │   ├── ErroPadrao.java
│   │   └── GlobalExceptionHandler.java
│   ├── model/
│   │   ├── enums/
│   │   │   └── Status.java
│   │   └── Funcionario.java
│   ├── service/
│   │   └── FuncionarioService.java
│   └── RhApplication.java
```

## 💻 Autores
- [Kevin Jun](https://github.com/keKvnJun)
