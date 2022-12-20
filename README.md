# Crud Spring Boot

Exemplo de Aplicação Spring Boot com insert, update, delete.

Criado a classe **RestExceptionHandler** para tratar as mensagem de validação dos campos

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
