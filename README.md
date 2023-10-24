# Documentação da API

Esta API fornece recursos para gerenciar tarefas e usuários em um aplicativo de lista de tarefas.

## Endpoints

### Criar um Usuário

- **URL**: `/users/`
- **Método**: `POST`
- **Descrição**: Cria um novo usuário.
- **Corpo da Solicitação**:
  ```json
  {
    "name": "Nome do Usuário",
    "userName": "Nome de Usuário",
    "password": "Senha"
  }
