import './Chat.css'
import styles from "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";
import Message, {
    MainContainer,
    ChatContainer,
    MessageList,
    MessageInput,
    Sidebar,
    Button,
    ConversationList,
    Conversation,
Avatar
} from "@chatscope/chat-ui-kit-react";

import React from "react";
import ChatService from "../../services/ChatService";

class Chat extends React.Component {

    constructor(props) {
        super(props);
        this._chatService = new ChatService();
        this._idUsuario = localStorage.getItem('idUsuario');
        this.state = {mensagens: [],
                      exibindoDisponiveis: false,
                      historicoConversas: [],
                      disponiveisConversa: [],
                      idContato: null};
        this.mudarListaConversas = this.mudarListaConversas.bind(this);
        this.obterTextoBotao = this.obterTextoBotao.bind(this);
        this.getDisponiveis = this.getDisponiveis.bind(this);
        this.getHistoricoConversas = this.getHistoricoConversas.bind(this);
        this.obterListaConversas = this.obterListaConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.atualizarConversa = this.atualizarConversa.bind(this);
        this.obterMensagens = this.obterMensagens.bind(this);
        this.ativarConversa = this.ativarConversa.bind(this);
        this.getHistoricoConversas();
    }

    mudarListaConversas() {
        if(!this.state.exibindoDisponiveis) {
            this.getDisponiveis();
        } else {
            this.getHistoricoConversas();
        }

        this.setState({exibindoDisponiveis: !this.state.exibindoDisponiveis});
    }

    getDisponiveis() {
        this._chatService.getDisponiveis()
            .then((disponiveis) => {
                this.setState({disponiveisConversa: disponiveis});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    getHistoricoConversas() {
        this._chatService.obterHistoricoConversas()
            .then((historico) => {
                this.setState({historicoConversas: historico});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    obterTextoBotao() {
        if(this.state.exibindoDisponiveis) {
            return "Mostrar conversas anteriores";
        } else {
            return "Mostrar psicólogos disponíveis";
        }
    }

    ativarConversa(idContato) {
        this._chatService.obterMensagensConversa(idContato)
            .then((mensagens) => {
                this.setState({mensagens: mensagens, idContato: idContato});
                this._chatService.marcarMensagensVistas(idContato)
                    .catch((err) => {
                        console.log(err);
                    })
            })
            .catch((err) => {
                console.log(err);
            });
    }

    obterListaConversas() {
        let elementosLista = []
        if(this.state.exibindoDisponiveis) {
            for(let elemento of this.state.disponiveisConversa) {
                elementosLista.push(
                    <Conversation className='conversation' unreadCnt={elemento.naoVisto} onClick={this.ativarConversa(elemento.idContato)}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        } else {
            for(let elemento of this.state.historicoConversas) {
                elementosLista.push(
                    <Conversation className='conversation' unreadCnt={elemento.naoVisto} onClick={this.ativarConversa(elemento.idContato)}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        }
        return elementosLista;
    }

    atualizarConversa() {
        this._chatService.obterMensagensConversa()
            .then((mensagens)=> {
                this.setState({mensagens: mensagens});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    enviarMensagem(mensagem) {
        this._chatService.enviarMensagem(mensagem, this.state.idContato)
            .then(() => {
               this.atualizarConversa();
            })
            .catch((err) => {
                console.log(err);
            });
    }

    obterMensagens() {
        let listaMensagens = [];
        for(let mensagem of this.state.mensagens) {
            let direcao = "incoming";
            if(mensagem.destinatario !== this._idUsuario) {
                direcao = "outgoing";
            }
            listaMensagens.push(<Message model={{
                message: mensagem.conteudo,
                sentTime: mensagem.horarioEnvio,
                direction: direcao,
                position: "single"
            }} />);
        }
        return listaMensagens;
    }

    render() {
        return <div className="pagina">
            <div className="botoes">
                <Button onClick={this.mudarListaConversas}>{this.obterTextoBotao()}</Button>
                <Button onClick={this.logout}>Sair</Button>
            </div>
            <MainContainer className="chat">
                <Sidebar position="left">
                    <ConversationList>
                        {this.obterListaConversas()}
                    </ConversationList>
                </Sidebar>
                <ChatContainer className="chat-container">
                    <MessageList>
                        {this.obterMensagens()}
                    </MessageList>
                    <MessageInput attachButton={false} placeholder="Digite sua mensagem..." onSend = {this.enviarMensagem} />
                </ChatContainer>
            </MainContainer>
        </div>;
    }
}

export default Chat;