# DivulgaCandidatoP2P
Um protótipo de sistema de divulgação de candidatos por controle de reputação

# Objetivo da Aplicação

Criar uma aplicação que se baseie no sistema de reputação do Freechains (https://github.com/Freechains/README) na implementação de parte de seu próprio sistema de créditos. A aplicação foi imaginada para divulgação de arquivos para leitura e repasse, podendo o conteúdo ser, por exemplo, propaganda de candidatos, como um panfleto físico que a gente recebe na rua. A diferença é que a quantidade de arquivos (panfletos) que podem ser distribuida é escalonada, ou seja, começa com uma permissão de envio de arquivos para 5 pessoas, elas leem, se gostarem informa que gostaram, e ao final da leitura dos primeiros 5 panfletos o sistema poderá dar 1 panfleto para a pessoa que gostou passar para outra pessoa e o candidato poderá dar mais um para essa pessoa se ela se manisfestar nesse sentido, pulando para um segundo nivel de divulgações, criando uma corrente do tipo amigo do amigo. No caso de não gostar, ao final da leitura dos primeiros 5 panfletados, o sistema vai contabilizar o saldo, se for negativo em 3 unidades, o candidato não poderá mais divulgar seus arquivos (panfletos). Mesmo se passar para um segundo nível de contatos, o saldo deve ser sempre maior que -3 para que ele possa continuar panfletando. No caso de -1 e menos -2 chegando no candidato, seus panfletos são debitados. No caso de zeramento de panfletos, não será mais possível repasse de arquivos. E no final se contabilizara o quanto ele conseguiu de gostei no caminho, com o registro nos arquivos.

# Justificativa

Esse sistema é uma atividade acadêmica da UERJ para aprendizagem de conceitos relacionados as redes distribuidas, no caso P2P não estruturadas. A descrição acima do sistema é relacionado à um desenvolvimento de prototipado do sistema, ou seja, nem todas as funções estão implementadas no algoritmo, como tratamento de exceções, problemas em saídas de funções, etc. Basicamente é um ponto inicial para discutir as regras de negócio que estão implentadas em um primeiro nível de visão.

# Atividades

Para esta atividade foram utilizados referencias de scripts de desenvolvedores consultados em sites como Stack Overflow, DevMedia, Guj, Viva o Linux, etc. Foi consultado também o Maven e Nexus repositorios. Também foram vistos sites de IA para rerencia de estudos. Como ferramentas de trabalho, basicamente, apostilas de aulas de redes P2P (uerj pós graduação) e de Programação 3 Java (UFF - Graduaçao)

# Execução e Desenvolvimento

Use o código como uma referencia de estudos de implementação de algoritmos. É aberto a alterações e assim que eu puder eu vou tornar ele cada vez melhor, até se tornar um sistema viável para utilização. Basicamente, baixar o código e compilar (java 20)
