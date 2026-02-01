# 🚀 User Management System - Guia Completo

## 📋 Sobre o Sistema

Este é um simples sistema de **gerenciamento de usuários**, aplicando os conhecimentos sobre o MailSender com funcionalidades de:
- ✅ **Registro de usuários** com validação
- ✅ **Recuperação de senha** via OTP (One-Time Password)
- ✅ **Alteração de senha** segura
- ✅ **API RESTful** completa documentada no Swagger
- ✅ **Interface Web** moderna e intuitiva
- ✅ **Testes unitários** com cobertura

---

## 🛠️ Pré-requisitos

1. **Java 21+**
   ```bash
   java -version
   ```

2. **PostgreSQL** (banco de dados)
   - Criar banco: `demo`
   - Usuário: `demo`
   - Senha: `demo`

3. **Maven**
   ```bash
   mvn -version
   ```

---

## 🚀 Como Iniciar

### 1. Compilar o Projeto
```bash
cd demo
mvn clean install
```

### 2. Rodar a Aplicação
```bash
mvn spring-boot:run
```

Ou se preferir:
```bash
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

A aplicação rodará em: **http://localhost:8080**

---

## 🌐 Acessar o Sistema

### 📱 Interface Web (Recomendado)
```
http://localhost:8080/
```
Interface completa com todos os recursos!

### 📖 Swagger API Documentation
```
http://localhost:8080/swagger-ui.html
```
Documentação interativa de todos os endpoints.

---

## 📚 Endpoints da API

### 👥 Usuários

#### Criar novo usuário
```bash
POST /api/v1/users
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "Senha123!",
  "phoneNumber": "(11) 99999-9999"
}
```

#### Listar todos os usuários
```bash
GET /api/v1/users
```

#### Buscar usuário por ID
```bash
GET /api/v1/users/{id}
```

### 🔐 Recuperação de Senha

#### Enviar OTP por email
```bash
POST /api/v1/forgot-password/send/{email}
```

Você receberá um email com um código OTP de 6 dígitos válido por 5 minutos.

#### Verificar OTP
```bash
POST /api/v1/forgot-password/verify-otp
Content-Type: application/json

{
  "email": "joao@example.com",
  "otp": 123456
}
```

#### Alterar senha
```bash
POST /api/v1/forgot-password/change-password/{email}
Content-Type: application/json

{
  "password": "NovaSenha123!",
  "repeatPassword": "NovaSenha123!"
}
```

---

## 💾 Banco de Dados

### Tabelas Criadas Automaticamente

#### users
```sql
CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)
);
```

#### forgot_password
```sql
CREATE TABLE forgot_password (
    id UUID PRIMARY KEY,
    otp INTEGER NOT NULL,
    expiration_time TIMESTAMP NOT NULL,
    user_id UUID FOREIGN KEY
);
```

---

## ✅ Fluxo de Teste Completo

### 1️⃣ Criar uma Conta
1. Acesse **http://localhost:8080/**
2. Vá para a seção "👤 Criar Conta"
3. Preencha os campos:
   - Nome: João Silva
   - Email: joao@example.com
   - Senha: Senha123!
   - Telefone: (11) 99999-9999
4. Clique em "Criar Conta"

### 2️⃣ Recuperar Senha
1. Vá para a seção "🔐 Recuperar Senha"
2. **Passo 1 - Email:**
   - Digite: joao@example.com
   - Clique em "Enviar OTP"
   - Você receberá um email com o código

3. **Passo 2 - OTP:**
   - Digite o email novamente
   - Digite o código OTP que recebeu
   - Clique em "Verificar OTP"

4. **Passo 3 - Nova Senha:**
   - Digite o email
   - Digite a nova senha: NovaSenha123!
   - Confirme a senha
   - Clique em "Alterar Senha"

### 3️⃣ Listar Usuários
1. Vá para "📋 Listar Usuários"
2. Clique em "Listar Todos"
3. Veja todos os usuários cadastrados

### 4️⃣ Buscar Usuário
1. Vá para "🔍 Buscar Usuário"
2. Digite o ID do usuário (obtido na etapa anterior)
3. Clique em "Buscar"

---

## 📧 Configuração de Email

Para que o OTP seja enviado por email, configure as variáveis de ambiente:

```bash
export EMAIL_USERNAME=seu@gmail.com
export EMAIL_PASSWORD=sua_senha_app
```

### Usando Gmail:
1. Ative **2-Factor Authentication** na sua conta Google
2. Gere uma **Senha de App** em: https://myaccount.google.com/apppasswords
3. Use esta senha no `EMAIL_PASSWORD`

---

## 🧪 Testes

### Rodar todos os testes
```bash
mvn test
```

### Rodar testes específicos
```bash
mvn test -Dtest=UserControllerTest
```

### Rodar com cobertura de código
```bash
mvn test jacoco:report
```

---

## 📊 Estrutura do Projeto

```
demo/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo/
│   │   │       ├── config/
│   │   │       │   └── OpenApiConfig.java      # Configuração Swagger
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java     # Página inicial
│   │   │       │   ├── UserController.java     # Endpoints de usuários
│   │   │       │   └── ForgotPasswordController.java  # Endpoints de senha
│   │   │       ├── dto/
│   │   │       │   ├── UserRequest.java
│   │   │       │   ├── UserResponse.java
│   │   │       │   ├── ChangePassword.java
│   │   │       │   └── VerifyOtpRequest.java
│   │   │       ├── entities/
│   │   │       │   ├── User.java
│   │   │       │   └── ForgotPassword.java
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java
│   │   │       │   └── ForgotPasswordRepository.java
│   │   │       ├── service/
│   │   │       │   ├── UserService.java
│   │   │       │   └── EmailService.java
│   │   │       └── DemoApplication.java
│   │   └── resources/
│   │       ├── application.yaml
│   │       ├── templates/
│   │       │   └── index.html              # Interface Web
│   │       └── db/migration/
│   │           └── V001__create_user.sql
│   └── test/
│       └── java/com/example/demo/
│           └── DemoApplicationTests.java
└── pom.xml
```

---

## 🔍 Validações Implementadas

### Usuário
- ✅ Email válido (formato correto)
- ✅ Senha mínimo 8 caracteres
- ✅ Nome entre 3 e 100 caracteres
- ✅ Email único (não permitir duplicatas)

### Recuperação de Senha
- ✅ OTP válido por 5 minutos
- ✅ Código OTP de 6 dígitos
- ✅ Confirmação de nova senha

---

## 🔐 Segurança

- ✅ **Validação de entrada** em todos os endpoints
- ✅ **OTP aleatório e seguro** usando `SecureRandom`
- ✅ **Expiração de OTP** para evitar brute force
- ✅ **Senhas validadas** (mínimo 8 caracteres)
- ✅ **HTTPS ready** (Swagger configurado)

---

## 🐛 Troubleshooting

### Erro: "Banco de dados não conecta"
```bash
# Verifique se PostgreSQL está rodando
psql -U wundu -d demo

# Se não existir, crie:
createdb -U wundu demo
```

### Erro: "OTP não chega no email"
1. Verifique as variáveis de ambiente
2. Verifique a pasta de spam
3. Ative "Permissões de apps menos seguras" no Gmail (se aplicável)

### Erro: "Porta 8080 já em uso"
```bash
# Mude a porta no application.yaml
server:
  port: 8081
```

---

## 📞 Suporte

Documentação Swagger completa: **http://localhost:8080/swagger-ui.html**

---

## ✨ Features Implementadas

- ✅ API RESTful completa
- ✅ Documentação Swagger/OpenAPI
- ✅ Interface Web responsiva
- ✅ Validação de dados
- ✅ Autenticação via OTP
- ✅ Recuperação de senha
- ✅ Banco de dados PostgreSQL
- ✅ Migrations com Flyway
- ✅ Testes unitários
- ✅ Tratamento de exceções
- ✅ CORS habilitado
- ✅ Logs estruturados

---

**Desenvolvido com ❤️ usando Spring Boot 4.0.2**
