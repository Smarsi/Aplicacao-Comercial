# aplicacaocomercial
Um projeto de TCC, utilizando MySQL, PHP, Java, JSon e Android.

---------------------------------------------------APIProjeto:
Esta é uma API para o projeto integrador do curso Técnico em informática da instituição de ensino Senac Tatuapé.
Nessa API usamos o PHP para criar funções de conexão com um banco de dados, onde temos as seguintes tabelas : Contato, endereco,
funcionarios, clientes, fornecedores, produtos, estoque, pedidos, detalhespedido. Todas essas tabelas estão relacionadas entre si,
o papel da API é realizar todas as funções de CRUD para cada uma dessas tabelas, ou seja, é ela quem cadastra, lista, atualiza e deleta
cada um dos itens presentes nessas tabelas, para isso acontecer de forma ampla e mais clara, estamos transformando todos os dados em 
json, assim podemos fazer com que qualquer aplicação possa se comunicar (enviar e receber dados) mais facilmente.


---------------------------------------------------Lojinhob:
Este é o projeto que contém os códigos e telas do android e do java que remete ao mesmo. Esse aplicativo tem como principal objetivo ser usado por funcionários de uma loja física voltada para qualquer tipo de produtos. Esse app irá ajudar para que o atendimento dentro da loja seja mais rápido e eficiente, sendo assim procuramos a praticidade em realizar pedidos e cadastrar novos clientes através de um smartphone ou tablet por exemplo. Nosso app está diretamente ligado com nossa API, assim puxando todos os dados que estão cadastrados no nosso banco de dados, sejam esses produtos ou até mesmo clientes, ele necessita de internet para poder funcionar corretamente.



O Java é usado para um sistema Desktop que estará apontado para a API.





O Android é usado para um app que estará apontado para API.
