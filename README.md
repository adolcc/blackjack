**Blackjack** API project implemented with Spring Boot (WebFlux), designed to be purely reactive. The API manages the complete logic of the Blackjack game and utilizes a dual database configuration: MongoDB for non-relational data and MySQL for relational data.
___
---
### 🧪 Pruebas Funcionales (Postman)

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/d0f0e969-3a0e-4dee-b2b6-7e70d4f9f13a" width="320" alt="Crear Partida"/>
      <br><strong>1. Crear Partida</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/5d020c14-f3a4-435a-8d86-ef68dd685969" width="320" alt="Apostar"/>
      <br><strong>2. Apostar (Bet)</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/a24c268e-4efd-40d5-8a0e-98aa23a2dbc2" width="320" alt="Hit y Stand"/>
      <br><strong>3. Pedir Carta (Hit)</strong>
    </td>
  </tr>
  <tr>
    <td align="center" colspan="3">
      <img src="https://github.com/user-attachments/assets/1d4c2912-deaf-413f-a6be-a679cf4a2575" width="400" alt="Resultado de la Partida"/>
      <br><strong>4. Resultado (Stand)</strong>
    </td>
  </tr>
</table>

---
### 🌐 Documentación y Despliegue (Swagger & Render)

<table>
  <tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/21d2cebf-a541-47fe-984b-a1b40d9bffb4" width="450" alt="Swagger UI"/>
      <br><strong>5. Documentación (Swagger UI)</strong>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/c4db241b-3271-483b-b4fe-93b19cb3ac8c" width="450" alt="Render Deploy"/>
      <br><strong>6. Despliegue (Render Status)</strong>
    </td>
  </tr>
</table>
---
___
### Technology and Dependencies

- Java

- Spring Boot 3

- Spring WebFlux (Reactive Approach)

- Spring Data MongoDB Reactive

- Spring Data JPA (for MySQL)

- MySQL/ Cleverd Cloud.

- MongoDB

- JUnit 5 and Mockito (for Testing)

- Swagger/OpenAPI (for Documentation)

- Docker

- Render

- GitHub Actions
___

Reactive Implementation: Use of Spring WebFlux and Reactive MongoDB.

Dual Database Management: Connection and data persistence in both MongoDB and MySQL.

Blackjack Game: Creation of games, execution of plays, and game rules.

Global Exception Handling: Implementation of GlobalExceptionHandler.

Testing: Unit test coverage for Controllers and Services using JUnit and Mockito.

Documentation: Automatic API documentation generation with Swagger.
___
## Author
https://github.com/adolcc 
