# 🌐 Javascript / Web

É a interface administrativa do **EcoPonto**, desenvolvida exclusivamente para a equipe de gestão. Através dele, a administração tem visibilidade e controle sobre as demandas da cidade em tempo real.

## 🎯 Responsabilidades do Painel

- **Análise de Denúncias:**
- **Gestão de Status:**
- **Despacho de Equipes:**
- **Monitoramento:**

## 🤔 Por que escolhemos?

O **Javascript** foi decidido para a construção do painel por ser a principal linguagem de programação para navegadores, oferecendo bibliotecas úteis para desenvolvimento, assim como, os navegadores já possuem _Engines_ muito otimizadas.
Já o **Vue.js** foi a tecnologia escolhida para a construção do painel de controle por permitir dividir o sistema em componentes reutilizáveis, por oferecer uma maior facilidade para construir interfaces dinâmicas em tempo real com atualização de status e tendo um carregamento rápido, o que é essencial para rotinas operacionais de trabalho.



# 📁 Estrutura da pasta

A estrutura principal do projeto Web está organizada da seguinte maneira:

```text
EcoPonto - Web/
├── public/
├── src/
│   ├── pages/
│   ├── views/
│   ├── App.css
│   ├── App.jsx
│   ├── App.vue
│   ├── index.css
│   ├── main.js
│   ├── main.jsx
│   └── style.css
├── eslint.config.js
├── index.html
├── package-lock.json
├── package.json
├── README_PROFESSOR.md
└── README.md
```

- **public:** reúne arquivos públicos utilizados pela aplicação.
- **src:** contém os códigos-fonte e os arquivos responsáveis pela interface.
- **pages e views:** organizam as páginas e visualizações do sistema.
- **App.css, index.css e style.css:** arquivos relacionados à estilização.
- **App.jsx e App.vue:** arquivos de componentes principais das tecnologias utilizadas no projeto.
- **main.js e main.jsx:** arquivos de entrada das aplicações.
- **eslint.config.js:** configurações de análise e padronização do código.
- **index.html:** página HTML inicial da aplicação.
- **package.json e package-lock.json:** registram as dependências e configurações do projeto JavaScript.
