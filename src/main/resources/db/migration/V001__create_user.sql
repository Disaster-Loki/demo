-- V001 - Criação da tabela de USER

-- Tabela de usuário
CREATE TABLE IF NOT EXISTS users (
    id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    phone_number VARCHAR(20) NOT NULL UNIQUE,
);

-- Índices para performance
CREATE INDEX idx_user_email ON user(email);
CREATE INDEX idx_user_phone_number ON user(phone_number);
CREATE INDEX idx_user_name ON user(name);

-- Comentários
COMMENT ON TABLE user IS 'Tabela de usuários do sistema';
COMMENT ON COLUMN user.id IS 'Identificador único do usuário';
COMMENT ON COLUMN user.name IS 'Nome completo do usuário';
COMMENT ON COLUMN user.email IS 'Email único do usuário';
COMMENT ON COLUMN user.password IS 'Senha criptografada do usuário';
COMMENT ON COLUMN user.phone_number IS 'Número de telefone do usuário';