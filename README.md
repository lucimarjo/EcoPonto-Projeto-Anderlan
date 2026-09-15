# 🌱 EcoPonto

O **EcoPonto** é uma plataforma desenvolvida como projeto acadêmico do curso de **Ciência da Computação**, com o objetivo de facilitar o descarte correto de resíduos e melhorar a comunicação entre a população e os serviços de coleta.

## 🎯 Objetivo

O projeto busca incentivar práticas mais conscientes de descarte e facilitar o acesso da população a informações relacionadas à coleta de resíduos.

Por meio da plataforma, o usuário poderá:

- Consultar informações relacionadas à coleta de resíduos;
- Visualizar pontos de coleta;
- Consultar dias e horários dos caminhões de coleta;
- Registrar denúncias de locais com acúmulo de lixo;
- Acompanhar suas denúncias;
- Gerenciar sua conta.

## 💻 Tecnologias

O desenvolvimento do projeto está dividido entre diferentes áreas.

### Front-end

O Front-end está sendo desenvolvido utilizando:

- React
- Vite
- JavaScript
- HTML
- CSS

### Banco de dados

O banco de dados será desenvolvido utilizando uma tecnologia **NoSQL**.

A integração entre o Front-end, a API e o banco de dados será realizada posteriormente.

## 📱 Funcionalidades

O sistema contará inicialmente com as seguintes telas:

### 🏠 Home

Página inicial da plataforma, apresentando o EcoPonto e suas principais funcionalidades.

### 👤 Cadastro

Permite que o usuário crie uma conta para utilizar os recursos da plataforma.

### 🗺️ Mapa

Permite visualizar os pontos de coleta disponíveis e encontrar locais adequados para o descarte de resíduos.

### 📝 Criar denúncia

Permite registrar denúncias sobre locais com acúmulo ou descarte irregular de lixo.

### 📋 Minhas denúncias

Permite acompanhar as denúncias realizadas pelo usuário e consultar o andamento das solicitações.

### ⚙️ Minha conta

Permite visualizar e gerenciar os dados da conta do usuário.

## 🏗️ Estrutura do projeto

Atualmente, a estrutura principal do Front-end está organizada da seguinte forma:

```text
EcoPonto-Projeto-Anderlan/
│
├── public/
│
├── src/
│   ├── assets/
│   │
│   ├── pages/
│   │   ├── Home.jsx
│   │   ├── Cadastro.jsx
│   │   ├── Denuncia.jsx
│   │   ├── MinhasDenuncias.jsx
│   │   └── Conta.jsx
│   │
│   ├── App.jsx
│   ├── App.css
│   ├── index.css
│   └── main.jsx
│
├── .gitignore
├── eslint.config.js
├── index.html
├── package.json
├── package-lock.json
├── vite.config.js
└── README.md