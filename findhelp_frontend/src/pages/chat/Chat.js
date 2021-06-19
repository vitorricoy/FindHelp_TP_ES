import './Chat.css'
import styles from "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";
import {
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
        this.state = {mensagens: [],
                      exibindoDisponiveis: false,
                      historicoConversas: [],
                      disponiveisConversa: []};
        this.mudarListaConversas = this.mudarListaConversas.bind(this);
        this.getTextoBotao = this.getTextoBotao.bind(this);
        this.getDisponiveis = this.getDisponiveis.bind(this);
        this.getHistoricoConversas = this.getHistoricoConversas.bind(this);
        this.getListaConversas = this.getListaConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.atualizarConversa = this.atualizarConversa.bind(this);
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
        this._chatService.getHistoricoConversas()
            .then((historico) => {
                this.setState({historicoConversas: historico});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    getTextoBotao() {
        if(this.state.exibindoDisponiveis) {
            return "Mostrar conversas anteriores";
        } else {
            return "Mostrar psicólogos disponíveis";
        }
    }

    getListaConversas() {
        let elementosLista = []
        if(this.state.exibindoDisponiveis) {
            for(let elemento of this.state.disponiveisConversa) {
                elementosLista.push(
                    <Conversation className='conversation' unreadCnt={elemento.naoVisto}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        } else {
            for(let elemento of this.state.historicoConversas) {
                elementosLista.push(
                    <Conversation className='conversation' unreadCnt={elemento.naoVisto}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        }
        return elementosLista;
    }

    atualizarConversa() {
        this._chatService.getMensagensConversa()
            .then((mensagens)=> {
                this.setState({mensagens: mensagens});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    enviarMensagem(mensagem) {
        this._chatService.enviarMensagem(mensagem)
            .then(() => {
               this.atualizarConversa();
            })
            .catch((err) => {
                console.log(err);
            });
    }

    render() {
        return <div className="pagina">
            <div className="botoes">
                <Button onClick={this.mudarListaConversas}>{this.getTextoBotao()}</Button>
                <Button onClick={this.logout}>Sair</Button>
            </div>
            <MainContainer className="chat">
                <Sidebar position="left">
                    <ConversationList>
                        {this.getListaConversas()}
                    </ConversationList>
                </Sidebar>
                <ChatContainer className="chat-container">
                    <MessageList>
                        {this.enviarMensagem}
                    </MessageList>
                    <MessageInput attachButton={false} placeholder="Digite sua mensagem..." onSend = {this.enviarMensagem} />
                </ChatContainer>
            </MainContainer>
        </div>;
    }
}

export default Chat;