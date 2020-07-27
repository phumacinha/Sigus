# Sistema de Gerenciamento de Usuários de um Sistema (Sigus)
O Sistema de Gerenciamento de Usuários de um Sistema (Sigus) é um projeto desenvolvido para o processo seletivo de estágio na empresa Zetta.

### Instalação e execução
Para instalação das dependências, é necessária a utilização do Maven Apache.

* Clone o repositório do [Sigus](http://github.com/phumacinha/Sigus) com o comando `git clone https://github.com/phumacinha/Sigus.git`
* Acesse o diretório com o comando `cd Sigus`
* No diretório `resources` há um arquivo chamado `sigus.sql` com o banco de dados necessário para persitência dos dados.
* Altere a classe `src/main/java/com/dao/Conexao.java` com os dados do seu banco de dados.
* Instale as dependências com o comando `mvn clean package`
* Será gerado um arquivo `Sigus-1.0-SNAPSHOT.jar` na pasta `target/`.
* Atribua permissão de execução ao arquivo `Sigus-1.0-SNAPSHOT.jar` e rode-o.
