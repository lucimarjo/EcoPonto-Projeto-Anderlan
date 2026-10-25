# 🌱 EcoPonto

O **EcoPonto** é uma plataforma desenvolvida como projeto acadêmico do curso de Ciência da Computação para o professor Anderlan Henrique.

O objetivo do projeto é facilitar o descarte correto de resíduos e melhorar a comunicação entre a população e os serviços responsáveis pela coleta e limpeza da cidade.

[**Site do projeto**](https://sites.google.com/view/ecoponto-projeto/in%C3%ADcio?authuser=0 "Site do EcoPonto") ♻️

## 🎯 Objetivo

A plataforma busca incentivar o descarte consciente de resíduos, facilitar o acesso às informações e permitir que os cidadãos participem ativamente da melhoria da cidade.

## 💻 Funcionalidades

O EcoPonto terá as seguintes funcionalidades:

- 👤 Cadastro de usuário;
- 🔐 Login e acesso à conta;
- 🗺️ Visualização de pontos de coleta;
- 📝 Criação de denúncias sobre locais com acúmulo ou descarte irregular de lixo;
- 📋 Acompanhamento das denúncias realizadas;
- ⚙️ Gerenciamento da conta do usuário;
- ♻️ Informações sobre descarte correto de resíduos;
- 🚛 Informações relacionadas à coleta de resíduos.

## Tecnologias

Terá duas versões da aplicação, a versão **Mobile** e a versão **Web**

### 🖥️ Front-end Web

O desenvolvimento do Front-end será realizado utilizando:

- **Vue.js**
- **JavaScript**

### 📱 Front-end Mobile

Responsável pela interface e interação do usuário no aplicativo do projeto, usando:

- **Kotlin**
- **Jetpack Compose**

### ⚙️ Back-end

O back-end foi dividido em duas que se unirão para tornarem-se uma única API. São elas:

#### 📍 Python

Responsável por:

- Microsserviço de geoprocessamento;

- Integração direta com as APIs do _OPenStreetMap_.

Utiliza FastAPI para comunicar com a API.

#### 🔒 Java

Responsável por:

- Gerenciar o banco de dados;
- Regras de negócio;
- Manusear Usuários, denúncias e horários de caminhões;
- Autenticação de segurança.

Usa SpringBoot como Framework.

### 🗄️ Banco de dados

A integração com a API e o banco de dados será realizada posteriormente. Neste momento, o desenvolvimento está concentrado na construção das interfaces e funcionalidades do Front-end.
O projeto utiliza dois banco de dados um SQL, PostgreSQL e NoSQL, MongoDB.

#### 🙍 PostgreSQL

Cuida dos:

1. Usuários;
2. Denúncias;
3. Horários e calendários dos caminhões.

#### 🔔 MongoDB

Responsável por:

1. Notificações;
2. Informações complementares das denúncias.

## 📂 Estrutura do projeto

A estrutura principal do projeto será organizada da seguinte maneira:

```text
EcoPonto-Projeto-Anderlan/
│
├── .vscode/
│
├── public/
│
├── src/                      # Código-fonte da aplicação
│   ├── assets/               # Imagens, logotipos e outros recursos
│   ├── components/           # Componentes reutilizáveis da interface
│   ├── views/                # Telas e páginas da aplicação
│   ├── App.vue               # Componente principal da aplicação
│   ├── main.js               # Ponto de entrada da aplicação
│   └── style.css             # Estilos globais
│
├── .gitignore
├── eslint.config.js
├── index.html                # Página HTML principal
├── package.json
├── package-lock.json
├── vite.config.js
└── README.md                 # Documentação do projeto
```
