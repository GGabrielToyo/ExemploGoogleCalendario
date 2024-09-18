
# Exemplo de integração com Google Calendar (JAVA)

Demonstração de como pode-se realizar a integração com a agenda do Google utilizando contas de serviço do Google Cloud Console.

Uma conta de serviço permite que operações sejam feitas sem a necessidade de uma autenticação do usuário via interface web por exemplo.

Estou demonstrando a integração utilizando JAVA + Spring Boot em um exemplo de api rest.




## Stack utilizada

**Back-end:** JAVA 17 + Spring Boot 3.3.3


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/GGabrielToyo/ExemploGoogleCalendario.git
```

Entre no diretório do projeto

Abra sua IDE de preferência e inicie o projeto.

Para a integração funcionar será necessário a cofiguração de uma conta de serviço no Google Console de sua conta google.

Após a criação da conta de serviço, um email será criado. Será ele que iremos utilizar no próximo passo.

Será necessário também que você crie uma chave desta conta de serviço. Depois que a chave é criada, faça download do arquivo no formato json e salve em um local seguro do seu computador.

- [Criando uma conta de serviço Google](https://cloud.google.com/iam/docs/service-accounts-create?hl=pt-br)

Após a criação da conta de serviço será necessário uma pequena configuração na agenda do Google. Você precisará compartilhar a agenda que deseja fazer a integração com o email da conta de serviço criada.

- [Compartilhando sua agenda Google](https://support.google.com/calendar/answer/37082?hl=pt-BR)

Pronto. Agora vamos para a IDE realizar as últimas configurações.
No controller você verá que tem uma variável que guardará o Id do calendário

private String calendarId = "<Calendar-ID>";

Altere para o ID do seu calendário Google.

No application.properties você terá que alterar o caminho de google.service.account.key.file para o local o qual você guardou a chave criada.

Fica neste formato, dependendo de onde está o arquivo:
google.service.account.key.file=${C:\projetos\web-development\BackEnd\credentials-exemplo-calendar-google.json}

Feito isso, basta iniciar a aplicação e fazer o teste com uma requisição a api.





## Autores

- [Gabriel Toyo](https://github.com/GGabrielToyo)


