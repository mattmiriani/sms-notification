# Notificacao API

Uma pequena API para notificar de maneira via WhatsApp ou mensagem de texto. Este projeto usa tecnologias como Spring 
Boot, Java e para banco de dados temos como base o PostgreSQL.

## Sumário

- [Configuração](#configuração)
- [Endpoints](#endpoints)
- [Documentação API](#Documentação-API)

## Configuração

### Clonando o Repositório
Para clonar o repositório, use o comando:
git clone https://github.com/mattmiriani/sms-notification.git

### Docker

#### Iniciar com Docker Compose
Para iniciar os containers e visualizar os logs, use:
```bash
docker-compose up
```

#### Parar e Remover Containers
Para parar e remover os containers, use:
```bash
docker-compose down
```

[Voltar ao inicio](#Notificacao-API)

## Endpoints

#### Cliente

- [GET] Obter todos os clientes: http://localhost:8080/customers
```json
{
  "content": [
    {
      "id": "2184be30-fce0-41ed-b06c-5385e795e0ae",
      "name": "admin",
      "email": "admin@example.com",
      "phone": "11987654321",
      "cpf": "12345678900",
      "cnpj": "12345678000195",
      "companyName": "Admin Company",
      "createdAt": "2024-08-27T10:00:00",
      "updatedAt": "2024-08-27T10:00:00",
      "active": true,
      "currentFunds": 500.00,
      "credit": 1000.00,
      "plan": {
        "id": "ed5b1f91-f37f-4201-81fb-d949421ab67b",
        "type": "Credit",
        "messageAmount": 0.05,
        "credit": 1000.00,
        "active": true,
        "createdAt": "2024-08-27T10:00:00",
        "updatedAt": "2024-08-27T10:00:00"
      }
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": false,
      "empty": true,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": false,
    "empty": true,
    "unsorted": true
  },
  "numberOfElements": 1,
  "empty": false
}
```

- [GET] Obter um cliente: http://localhost:8080/customers/{id}
```json
{
  "id": "2184be30-fce0-41ed-b06c-5385e795e0ae",
  "name": "admin",
  "email": "admin@example.com",
  "phone": "11987654321",
  "cpf": "12345678900",
  "cnpj": "12345678000195",
  "companyName": "Admin Company",
  "createdAt": "2024-08-27T10:00:00",
  "updatedAt": "2024-08-27T10:00:00",
  "active": true,
  "currentFunds": 500.00,
  "credit": 1000.00,
  "plan": {
    "id": "ed5b1f91-f37f-4201-81fb-d949421ab67b",
    "type": "Credit",
    "messageAmount": 0.05,
    "credit": 1000.00,
    "active": true,
    "createdAt": "2024-08-27T10:00:00",
    "updatedAt": "2024-08-27T10:00:00"
  }
}
```

- [GET] Obter saldo de um cliente: http://localhost:8080/customers/{id}/balance
```json
{
  "customerName": "admin",
  "balance": {
    "credit": 1000.00,
    "currentFunds": 500.00
  }
}
```

- [POST] Adicionar um cliente: http://localhost:8080/customers
```json
{
  "name": "João da Silva",
  "email": "joao.silva@example.com",
  "phone": "11111111111",
  "cpf": "11111111",
  "cnpj": "58215559000119",
  "companyName": "Empresa Exemplo LTDA",
  "active": true,
  "balance": 1000.00,
  "creditLimit": 5000.00,
  "plan": {
    "id": "695c0848-062e-47ed-bf33-e55f990a27fa"
  }
}
```
```json
{
  "id": "dcf6fbcf-e316-4cac-97b2-9d4c015d77e4",
  "name": "João da Silva",
  "email": "joao.silva@example.com",
  "phone": "11111111111",
  "cpf": "11111111",
  "cnpj": "58215559000119",
  "companyName": "Empresa Exemplo LTDA",
  "createdAt": "2024-08-29T20:07:47.921435591",
  "updatedAt": "2024-08-29T20:07:47.921445534",
  "active": true,
  "currentFunds": 1000.00,
  "credit": 1000.00,
  "plan": {
    "id": "ed5b1f91-f37f-4201-81fb-d949421ab67b",
    "type": "Credit",
    "messageAmount": 0.05,
    "credit": 1000.00,
    "active": true,
    "createdAt": "2024-08-27T10:00:00",
    "updatedAt": "2024-08-27T10:00:00"
  }
}
```

- [PUT] Atualizar um cliente: http://localhost:8080/customers/{id}
```json
{
  "name": "João",
  "email": "joao@example.com",
  "phone": "44998666666",
  "companyName": "Empresa Exemplo"
}
```
```json
{
  "id": "dcf6fbcf-e316-4cac-97b2-9d4c015d77e4",
  "name": "João",
  "email": "joao.silva@example.com",
  "phone": "11111111111",
  "cpf": "11111111",
  "cnpj": "58215559000119",
  "companyName": "Empresa Exemplo",
  "createdAt": "2024-08-29T20:07:47.921436",
  "updatedAt": "2024-08-29T20:10:41.773986917",
  "active": true,
  "currentFunds": 1000.00,
  "credit": 1000.00,
  "plan": null
}
```

- [PUT] Adicionar creditos a um cliente: http://localhost:8080/customers/{id}/credit

- [PUT] Adicionar saldo a um cliente: http://localhost:8080/customers/{id}/funds

- [PUT] Atualizar plano de um cliente: http://localhost:8080/customers/{id}/plan

- [DELETE] Remover um cliente: http://localhost:8080/customers/{id}

[Voltar ao inicio](#Notificacao-API)
#### Mensagem

- [POST] Enviar mensagem: http://localhost:8080/messages/send
```json
{
  "phoneNumber": "5544997282268",
  "whatsApp": true,
  "text": "Hello, this is a test message.",
  "customer": {
    "id": "2184be30-fce0-41ed-b06c-5385e795e0ae"
  }
}
```
```json
{
  "id": "2b894d49-fee6-4ae4-85d8-f867ec169ccc",
  "phoneNumber": "5544997282268",
  "text": "Hello, this is a test message.",
  "customer": {
    "id": "2184be30-fce0-41ed-b06c-5385e795e0ae",
    "name": "admin"
  },
  "createdAt": "2024-08-29T20:15:21.952279826",
  "whatsApp": true
}
```

[Voltar ao inicio](#Notificacao-API)
#### Plano

- [GET] Obter todos os planos: http://localhost:8080/plans
```json
{
  "content": [
    {
      "id": "ed5b1f91-f37f-4201-81fb-d949421ab67b",
      "type": "Credit",
      "messageAmount": 0.05,
      "credit": 1000.00,
      "active": true,
      "createdAt": "2024-08-27T10:00:00",
      "updatedAt": "2024-08-27T10:00:00"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 20,
    "sort": {
      "sorted": false,
      "empty": true,
      "unsorted": true
    },
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 1,
  "first": true,
  "size": 20,
  "number": 0,
  "sort": {
    "sorted": false,
    "empty": true,
    "unsorted": true
  },
  "numberOfElements": 1,
  "empty": false
}
```

-  [GET] Obter um plano: http://localhost:8080/plans/{id}
```json
{
  "id": "ed5b1f91-f37f-4201-81fb-d949421ab67b",
  "type": "Credit",
  "messageAmount": 0.05,
  "credit": 1000.00,
  "active": true,
  "createdAt": "2024-08-27T10:00:00",
  "updatedAt": "2024-08-27T10:00:00"
}
```

- [POST] Criar um plano: http://localhost:8080/plans
```json
{
  "type": "Credit",
  "messageAmount": 1,
  "credit": 1000,
  "active": true
}
```
```json
{
  "id": "947f3d0e-6da9-41a6-8f44-c6c6d36066df",
  "type": "Credit",
  "messageAmount": 1,
  "credit": 1000,
  "active": true,
  "createdAt": "2024-08-29T20:21:30.842338882",
  "updatedAt": "2024-08-29T20:21:30.842350488"
}
```

- [PUT] Atualizar um plano: http://localhost:8080/plans/{id}
```json
{
  "type": "Credit",
  "messageAmount": 2,
  "credit": 1000,
  "active": true
}
```
```json
{
  "id": "947f3d0e-6da9-41a6-8f44-c6c6d36066df",
  "type": "Credit",
  "messageAmount": 2,
  "credit": 1000,
  "active": true,
  "createdAt": "2024-08-29T20:21:30.842339",
  "updatedAt": "2024-08-29T20:26:26.005295089"
}
```

- [DELETE] Deletar um plano: http://localhost:8080/plans/{id}

[Voltar ao inicio](#Notificacao-API)
## Documentação API

### Padroes
- Se não encontrar o objeto ou se o objeto estiver inativo quando deveria estar ativo para a ação, será retornado um erro: 404.
- Qualquer ação mal-sucedida retornará um erro: 400.
- Qualquer falha no servidor retornará um erro: 500.

### Clientes
- Na listagem geral, serão exibidos todos os clientes, independentemente de estarem ativos ou inativos.
- Na busca de um único cliente, será retornado apenas o cliente que estiver ativo.
- A consulta pelo saldo do cliente ocorrerá somente se o cliente estiver ativo.
- Ao criar um cliente, é necessário informar um plano já cadastrado e ativo no sistema, além de um documento que ainda não exista no sistema.
- Para atualizar um cliente, será possível modificar apenas as seguintes informações: nome, e-mail, telefone e nome da empresa.
- A adição de crédito aumentará o saldo de crédito do cliente.
- A adição de fundos aumentará o saldo do cliente.
- A atualização de um plano substituirá a quantidade de crédito do cliente, independentemente do valor atual.
- Deletar um cliente irá inativar o cliente no sistema, sendo possível reativá-lo por meio de uma atualização geral.

### Mensagens
- O envio de mensagens deve ser feito para um cliente existente no sistema. Caso o cliente não exista, será retornado um erro.
- Para que o envio de mensagens seja bem-sucedido, o cliente deve ter saldo suficiente de acordo com o tipo de seu plano. 
O valor correspondente ao envio da mensagem será descontado do saldo.

### Planos
- Na listagem geral, serão exibidos todos os planos, independentemente de estarem ativos ou inativos.
- Na busca de um único plano, será retornado apenas o plano que estiver ativo.
- Ao criar um plano, é necessário informar um valor de crédito, um valor por mensagem e o tipo de plano.
- Para atualizar um plano, será possível modificar apenas as seguintes informações: tipo, valor por mensagem e valor do crédito. 
O valor de credito não ira modificar a quantidade de credito dos clientes.
- Deletar um plano irá inativá-lo no sistema, sendo possível reativá-lo por meio de uma atualização geral.
