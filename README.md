# aplicacaocomercial
Um projeto de TCC, utilizando MySQL, PHP, Java, JSon e Android.

---------------------------------------------------APIProjeto:
Esta é uma API para o projeto integrador do curso Técnico em informática da instituição de ensino Senac Tatuapé.
Nessa API usamos o PHP para criar funções de conexão com um banco de dados, onde temos as seguintes tabelas : Contato, endereco,
funcionarios, clientes, fornecedores, produtos, estoque, pedidos, detalhespedido. Todas essas tabelas estão relacionadas entre si,
o papel da API é realizar todas as funções de CRUD para cada uma dessas tabelas, ou seja, é ela quem cadastra, lista, atualiza e deleta
cada um dos itens presentes nessas tabelas, para issom acontecer de forma ampla e mais clara, estamos transformando todos os dados em 
json, assim podemos fazer com que qualquer aplicação possa se comunicar (enviar e receber dados) mais facilmente.
O Java é usado para um sistema Desktop que estará apontado para a API.
O Android é usado para um app que estará apontado para API.
