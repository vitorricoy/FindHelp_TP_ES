# FindHelp
##### TP1 de Engenharia de Software

## Grupo:
* Gustavo Couto Alves - 2019430007 - Front-end
* Vitor Assunção Rabello de Oliveira - 2019007104 - Back-end
* Vitor Rodarte Ricoy - 2019007112 - Full Stack

## Objetivo:
Sistema para colocar em contato pacientes e psicólogos de forma online.

## Features Básicas:
* Cadastro de pacientes e psicólogos
* Tela de Login
* Busca de Psicólogos por Horário de Atendimento
* Chat Integrado para Contato entre Psicológo e Paciente

## Tecnologias:
* MySQL
* Java (Framework Spring)
* Javascript (ReactJS)
* GitHub (Controle de Versão)
* Trello (Organização)

## Backlog do Produto
* Como usuário, quero me cadastar no sistema.
* Como usuário, quero logar e deslogar do sistema.
* Como paciente, quero buscar psicólogos disponíveis para atendimento no momento.
* Como usuário, quero ter acesso a um chat de texto entre psicólogo e paciente.
* Como paciente, quero marcar uma consulta presencial com um psicólogo.
* Como usuário, quero ter acesso a um chat de vídeo entre psicólogo e paciente.

## Diagrama de Arquitetura
![Diagrama de Arquitetura](https://drive.google.com/uc?export=view&id=1fR2LOe9VXjB_mxPqrh5OxZc4HVCtPn8Z)

## Decisões de Projeto
* Após logar o usuário irá para a página do chat.
* Na página do chat, existe um botão para os pacientes buscarem psicólogos para iniciarem uma nova conversa.
* O usuário é deslogado ao fechar a página e as sessões não são mantidas.

## Backlog da Sprint
* História: Como usuário, quero me cadastar no sistema.
    * Tarefa 1: Projetar a interface da página web (Vitor Rodarte)
    * Tarefa 2: Implementar e testar a página web (Vitor Rodarte)
    * Tarefa 3: Implementar a camada de acesso aos dados (Vitor Assunção)
    * Tarefa 4: Implementar a camada de lógica de negócio para a a operação de cadastrar pacientes (Vitor Assunção)
    * Tarefa 5: Implementar a camada de controle com a operação de cadastrar pacientes (Vitor Assunção)
* História: Como usuário, quero logar e deslogar do sistema.
    * Tarefa 1: Projetar a interface da página web (Vitor Rodarte)
    * Tarefa 2: Implementar e testar a página web (Vitor Rodarte)
    * Tarefa 3: Implementar a camada de acesso aos dados (Vitor Assunção)
    * Tarefa 4: Implementar a camada de lógica de negócio para a operação de logar com um usuário (Vitor Assunção)
    * Tarefa 5: Implementar a camada de controle com a operação de logar com um usuário (Vitor Assunção)
* História: Como paciente, quero buscar psicólogos disponíveis para atendimento no momento.
    * Tarefa 1: Projetar a interface da página web (Vitor Rodarte)
    * Tarefa 2: Implementar e testar a página web (Vitor Rodarte)
    * Tarefa 3: Implementar o acesso aos dados para a busca de psicólogos logados (Vitor Assunção)
    * Tarefa 4: Implementar o acesso aos dados para salvar os usuários logados (Vitor Assunção)
    * Tarefa 5: Implementar a camada de lógica de negócio para a operação de busca dos psicólogos logados no momento (Vitor Assunção)
    * Tarefa 6: Implementar a camada de controle com a operação de busca dos psicólogos logados no momento (Vitor Assunção)
    * Tarefa 7: Implementar a camada de lógica de negócio para guardar se o usuário está logado (Vitor Assunção)
* História: Como usuário, quero ter acesso a um chat de texto entre psicólogo e paciente
    * Tarefa 1: Projetar a interface da página web (Vitor Rodarte)
    * Tarefa 2: Implementar e testar a página web (Vitor Rodarte)
    * Tarefa 3: Implementar a camada de acesso aos dados (Vitor Rodarte)
    * Tarefa 4: Implementar a camada de lógica de negócio para a operação de carregar o histórico de mensagens (Vitor Rodarte)
    * Tarefa 5: Implementar a camada de controle com a operação de carregar o histórico de mensagens (Vitor Rodarte)
    * Tarefa 6: Implementar a camada de lógica de negócio para a operação de envio de mensagens (Vitor Rodarte)
    * Tarefa 7: Implementar a camada de controle com a operação de envio de mensagens (Vitor Rodarte)
