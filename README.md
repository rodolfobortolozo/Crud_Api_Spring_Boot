# Exemplo de API Rest com Spring Boot, JPA e Hibernate

O objetivo deste projeto é construir um exemplo de API Rest usando Spring Boot, que poderá servir de guia para quem estiver estudando o assunto, será usado Java 8, Maven, Spring Boot, JPA, Hibernate e MySQL. Futuramente outras tecnologias podem ser adicionadas, mas o foco é manter o projeto o mais simples possível para que cumpra o objetivo de ser um exemplo inicial de API Rest, e que assim os desenvolvedores que queiram estudar o assunto usando Java e Spring Boot, possam usar este exemplo como ponto de partida.

Com o tempo novas funcionalidades podem ser adicionadas, conforme as ideias e sugetões forem surgindo.

A classe **RestExceptionHandler** tratar as mensagem de validação dos campos que estiverem faltando

` {
	"message": "Requisição possui campos inválidos",
	"code": 400,
	"status": "400 BAD_REQUEST",
	"objectName": "contact",
	"errors": [
		{
			"message": "Nome Obrigatório",
			"field": "name",
			"parameter": null
		},
		{
			"message": "E-mail Inváido",
			"field": "email",
			"parameter": "asdasd.com"
		}
	]
} `
### 📋 Pré-requisitos

Utilizado Spring Boot 3.0.0 com SDK 19


## 🛠️ Desenvolvido com

* [SpringBoot](https://spring.io/projects/spring-boot) - Spring Boot 3.0.0
* [Maven](https://maven.apache.org/) - Gerente de Dependência
* [Visual Studio Code](https://code.visualstudio.com/) - Editor de Código 
