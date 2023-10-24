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
  ```
- **Exemplo de Resposta (usuário criado com sucesso)**:
  ```json
  {
    "id": "8ce35757-4074-413e-9570-85fc287570b9",
    "name": "Nome do Usuário",
    "userName": "Nome de Usuário"
  }
  ```

### Criar uma Tarefa

- **URL**: `/tasks/`
- **Método**: `POST`
- **Descrição**: Cria uma nova tarefa.
- **Corpo da Solicitação**:
  ```json
  {
    "description": "Descrição da Tarefa",
    "title": "Título da Tarefa",
    "priority": "Prioridade",
    "startAt": "Data de Início (formato ISO)",
    "endAt": "Data de Término (formato ISO)"
  }
  ```
- **Exemplo de Resposta (tarefa criada com sucesso)**:
  ```json
  {
    "id": "8ce35757-4074-413e-9570-85fc287570b9",
    "description": "Descrição da Tarefa",
    "title": "Título da Tarefa",
    "priority": "Prioridade",
    "startAt": "Data de Início",
    "endAt": "Data de Término"
  }
  ```

### Obter Tarefas do Usuário

- **URL**: `/tasks/`
- **Método**: `GET`
- **Descrição**: Obtém todas as tarefas do usuário autenticado.
- **Exemplo de Resposta (lista de tarefas)**:
  ```json
  [
    {
      "id": "8ce35757-4074-413e-9570-85fc287570b9",
      "description": "Descrição da Tarefa",
      "title": "Título da Tarefa",
      "priority": "Prioridade",
      "startAt": "Data de Início",
      "endAt": "Data de Término"
    },
    // Outras tarefas
  ]
  ```

### Atualização Parcial de Tarefa

- **URL**: `/tasks/{id}`
- **Método**: `PUT`
- **Descrição**: Atualiza parcialmente uma tarefa especificada.
- **Corpo da Solicitação (atualização parcial)**:
  ```json
  {
    "title": "Novo Título da Tarefa"
  }
  ```
- **Exemplo de Resposta (tarefa atualizada com sucesso)**:
  ```json
  {
    "id": "8ce35757-4074-413e-9570-85fc287570b9",
    "description": "Descrição da Tarefa",
    "title": "Novo Título da Tarefa",
    "priority": "Prioridade",
    "startAt": "Data de Início",
    "endAt": "Data de Término"
  }
  ```

## Autenticação

- Para endpoints que requerem autenticação, use Basic Auth para enviar as credenciais do usuário.

## Validação de Datas

- As datas `startAt` e `endAt` têm validações para garantir que `startAt` seja anterior a `endAt`.
