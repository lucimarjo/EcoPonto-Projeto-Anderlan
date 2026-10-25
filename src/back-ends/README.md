# ⚙️ Back-Ends

O back-end do projeto é dividido em dois microsserviços independentes que se complementam para formar a API completa. Cada serviço possui responsabilidades específicas e tecnologias dedicadas:

- 🐍 **Python**, utiliza FastAPI para comunicar com a API
  Consulte o [README do serviço Python](./python/README.md) para detalhes de arquitetura, e tomadas de decisão.
- ☕ **Java**, utiliza SpringBoot como Framework
  Consulte o [README do serviço Java](./Java/README.md) para detalhes de arquitetura, e tomadas de decisão.

## 🗺️ OpenStreetMap (OSM)

A API escolhida para fornecer os dados de geolocalização e mapas para a aplicação foi o **OpenStreetMap (OSM)**. Diferente de alternativas como o Google Maps, o OSM é **totalmente gratuito e de código aberto**, sem necessidade de pagar por licença, com os dados sendo mantidos e atualizados por uma comunidade global ativa.
