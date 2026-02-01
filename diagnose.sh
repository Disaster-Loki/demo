#!/bin/bash

# 🧪 Script de Diagnóstico da Aplicação

echo "=========================================="
echo "🧪 DIAGNÓSTICO DA APLICAÇÃO"
echo "=========================================="
echo ""

# Verificar se aplicação está rodando
echo "1️⃣ Verificando se aplicação está rodando em localhost:8080..."
if curl -s http://localhost:8080/swagger-ui.html > /dev/null 2>&1; then
    echo "✅ Aplicação está rodando!"
else
    echo "❌ Aplicação NÃO está rodando!"
    echo "   Execute: ./mvnw spring-boot:run"
    exit 1
fi

echo ""
echo "2️⃣ Testando endpoints..."
echo ""

# Teste 1: Criar usuário
echo "📝 Teste 1: Criando usuário..."
RESPONSE=$(curl -s -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Teste User",
    "email": "teste@example.com",
    "password": "TesteSenha123!",
    "phoneNumber": "(11) 9999-9999"
  }')

if echo "$RESPONSE" | grep -q "id"; then
    echo "✅ Usuário criado com sucesso!"
    USER_ID=$(echo "$RESPONSE" | grep -o '"id":"[^"]*' | cut -d'"' -f4)
    echo "   ID: $USER_ID"
else
    echo "❌ Erro ao criar usuário:"
    echo "$RESPONSE"
fi

echo ""

# Teste 2: Listar usuários
echo "📋 Teste 2: Listando usuários..."
USERS=$(curl -s http://localhost:8080/api/v1/users)
if echo "$USERS" | grep -q "id"; then
    echo "✅ Usuários recuperados!"
    COUNT=$(echo "$USERS" | grep -o '"id"' | wc -l)
    echo "   Total de usuários: $COUNT"
else
    echo "⚠️  Nenhum usuário encontrado ou erro"
fi

echo ""

# Teste 3: Swagger
echo "📖 Teste 3: Verificando Swagger..."
if curl -s http://localhost:8080/v3/api-docs | grep -q "openapi"; then
    echo "✅ Swagger API Docs disponível!"
else
    echo "⚠️  Swagger pode estar com problema"
fi

echo ""
echo "=========================================="
echo "✅ DIAGNÓSTICO CONCLUÍDO!"
echo "=========================================="
echo ""
echo "Acessar:"
echo "  🌐 Website: http://localhost:8080/"
echo "  📖 Swagger: http://localhost:8080/swagger-ui.html"
echo ""
