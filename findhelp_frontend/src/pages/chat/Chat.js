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
    Avatar,
    Message
} from "@chatscope/chat-ui-kit-react";

import React from "react";
import ChatService from "../../services/ChatService";
import UsuarioService from "../../services/UsuarioService";

class Chat extends React.Component {

    constructor(props) {
        super(props);
        this._chatService = new ChatService();
        this._usuarioService = new UsuarioService();
        this._idUsuario = localStorage.getItem('idUsuario');
        this.state = {mensagens: [],
                      exibindoDisponiveis: false,
                      conversasExibidas: [],
                      idContato: null};
    }

    componentDidMount() {
        this.mudarListaConversas = this.mudarListaConversas.bind(this);
        this.obterTextoBotao = this.obterTextoBotao.bind(this);
        this.obterDisponiveis = this.obterDisponiveis.bind(this);
        this.obterHistoricoConversas = this.obterHistoricoConversas.bind(this);
        this.obterListaConversas = this.obterListaConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.atualizarConversa = this.atualizarConversa.bind(this);
        this.obterMensagens = this.obterMensagens.bind(this);
        this.ativarConversa = this.ativarConversa.bind(this);
        this.logout = this.logout.bind(this);
        setInterval(() => {
            if(this.state.exibindoDisponiveis) {
                this.obterDisponiveis();
            } else {
                this.obterHistoricoConversas();
            }
            if(this.state.idContato) {
                this.atualizarConversa();
                this._chatService.marcarMensagensVistas(this.state.idContato)
                    .catch((err) => {
                        console.log(err);
                    })
            }
        }, 500);
    }

    logout() {
        this._usuarioService.deslogarUsuario()
            .then((disponiveis) => {
                this.setState({conversasExibidas: disponiveis});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    mudarListaConversas() {
        if(!this.state.exibindoDisponiveis) {
            this.obterDisponiveis();
        } else {
            this.obterHistoricoConversas();
        }

        this.setState({exibindoDisponiveis: !this.state.exibindoDisponiveis});
    }

    obterDisponiveis() {
        this._chatService.getDisponiveis()
            .then((disponiveis) => {
                this.setState({conversasExibidas: disponiveis});
            })
            .catch((err) => {
                console.log(err);
            });
    }

    obterHistoricoConversas() {
        this._chatService.obterHistoricoConversas()
            .then((historico) => {
                this.setState({conversasExibidas: historico});
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
        return  this.state.conversasExibidas.map((elemento) =>
            <Conversation key = {elemento.idContato} className='conversation' unreadCnt={elemento.naoVisto}
                          onClick={() => this.ativarConversa(elemento.idContato)}>
                <Avatar name={elemento.nomeContato}/>
            </Conversation>
        );
    }

    atualizarConversa() {
        this._chatService.obterMensagensConversa(this.state.idContato)
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
        let cont = 0;
        for(let mensagem of this.state.mensagens) {
            let direcao = "incoming";
            if(mensagem.destinatario.id !== this._idUsuario) {
                direcao = "outgoing";
            }
            listaMensagens.push(<Message key={cont} model={{
                message: mensagem.conteudo,
                sentTime: mensagem.horarioEnvio,
                direction: direcao,
                position: "single"
            }} />);
            cont+=1;
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