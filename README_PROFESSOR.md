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

É a interface administrativa do **EcoPonto**, desenvolvida exclusivamente para a equipe de gestão. Através dele, a administração tem visibilidade e controle sobre as demandas da cidade em tempo real.

#### 🎯 Responsabilidades do Painel

- **Análise de Denúncias:**
- **Gestão de Status:**
- **Despacho de Equipes:**
- **Monitoramento:**

O desenvolvimento do Front-end será realizado utilizando:

- **Vue.js**
- **JavaScript**

O Javascript foi decidido por ser a principal linguagem de programação para navegadores, oferecendo bibliotecas úteis para desenvolvimento, assim como, os navegadores já possuem _Engines_ muito otimizadas.
Já o Vue.js foi a tecnologia escolhida por permitir dividir o sistema em componentes reutilizáveis, por oferecer uma maior facilidade para construir interfaces dinâmicas em tempo real com atualização de status e tendo um carregamento rápido, o que é essencial para rotinas operacionais de trabalho.

Consulte o [README do serviço Javascript](https://github.com/lucimarjo/EcoPonto-Projeto-Anderlan/tree/feature/geoprocessamento-python/src/front-ends/web-JS) para detalhes de arquitetura, e tomadas de decisão.

### 📱 Front-end Mobile

Responsável pela interface e interação do usuário final no aplicativo do projeto, dando acesso aos serviços de coleta e descarte

#### 🎯 Responsabilidades do Painel

- **Geolocalização e mapas**
- **Envio de denúncias**
- **Consulta de coleta**
- **Pontos de descarte**

O desenvolvimento do Front-end será realizado utilizando:

- **Kotlin**
- **Jetpack Compose**

O Kotlin foi escolhido por ser a linguagem oficial e recomendada pelo Google para o desenvolvimento Android nativo, sendo excelente e desempenho por isso.
Já o Framework Jetpack Compose foi escolhido por reduzir a quantidade de XML de código necessário e por apenas descrever como a tela deve ser o Compose cuidará da atualização da interface, agilizando, assim, a entrega do projeto.

Consulte o [README do serviço Kotlin](https://github.com/lucimarjo/EcoPonto-Projeto-Anderlan/blob/feature/geoprocessamento-python/src/front-ends/web-JS/README.md) para detalhes de arquitetura, e tomadas de decisão.

### ⚙️ Back-end

O back-end do projeto é dividido em dois microsserviços independentes que se complementam para formar a API completa. Cada serviço possui responsabilidades específicas e tecnologias dedicadas:

#### 📍 Python

Responsável por:

- Microsserviço de geoprocessamento;

- Integração direta com as APIs do _OPenStreetMap_.

Utiliza **FastAPI** para comunicar com a API.

Python foi escolhido para comunicar com a API por que possui um ecossitema de bibliotecas geográficas mais simples e eficiente do mercado e permite integrar funcionalidades de análise de dados ou até inteligência artificial caso queiramos adicionar no futuro. Já o FastAPI foi preferido por gerar a documentação interativa da API de forma instantânea, como também, ser considerado o Framework mais rápido do mercado.

Consulte o [README do serviço Python](https://github.com/lucimarjo/EcoPonto-Projeto-Anderlan/blob/feature/geoprocessamento-python/src/back-ends/python/README.md) para detalhes de arquitetura, e tomadas de decisão.

#### 🔒 Java

Responsável por:

- Gerenciar o banco de dados;
- Regras de negócio;
- Manusear Usuários, denúncias e horários de caminhões;
- Autenticação de segurança.

Usa **SpringBoot** como Framework.

O Java foi escolhido para ser o responsável pelas regras de negócio da aplicação graças ao sua alta segurança, otimização ao criar banco de dados utilizando recursos como data classes.
Já o Framework Spring Boot foi escolhido por já ser o padrão na indústria por oferecer uma estrutura estável, escalável e bem organizada para gerenciar as regras de negócio, assim como, é seguro por facilitar a criação de autenticação de usuários e o controle rigoroso de transações com o banco de dados.

Consulte o [README do serviço Java](https://github.com/lucimarjo/EcoPonto-Projeto-Anderlan/blob/feature/geoprocessamento-python/src/back-ends/Java/README.md) para detalhes de arquitetura, e tomadas de decisão.

#### 🗺️ OpenStreetMap (OSM)

A API escolhida para fornecer os dados de geolocalização e mapas para a aplicação foi o **OpenStreetMap (OSM)**. Diferente de alternativas como o Google Maps, o OSM é **totalmente gratuito e de código aberto**, sem necessidade de pagar por licença, com os dados sendo mantidos e atualizados por uma comunidade global ativa.

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
