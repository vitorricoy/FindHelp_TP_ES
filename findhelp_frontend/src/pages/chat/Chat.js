import './Chat.css'
import styles from "@chatscope/chat-ui-kit-styles/dist/default/styles.min.css";
import {
    MainContainer,
    ChatContainer,
    MessageList,
    Message,
    MessageInput,
    Sidebar,
    Button,
    ConversationList,
    Conversation,
Avatar
} from "@chatscope/chat-ui-kit-react";

import React from "react";

class Chat extends React.Component {

    constructor(props) {
        super(props);
        this.state = {mensagens: [],
                      exibindoDisponiveis: false,
                      historicoConversas: this.getHistoricoConversas(),
                      disponiveisConversa: []};
        this.mudarListaConversas = this.mudarListaConversas.bind(this);
        this.getTextoBotao = this.getTextoBotao.bind(this);
        this.getHistoricoDisponiveis = this.getHistoricoDisponiveis.bind(this);
        this.getHistoricoConversas = this.getHistoricoConversas.bind(this);
        this.getListaConversas = this.getListaConversas.bind(this);
    }

    mudarListaConversas() {
        if(!this.state.exibindoDisponiveis) {
            this.getHistoricoDisponiveis();
        } else {
            this.getHistoricoConversas();
        }

        this.setState({exibindoDisponiveis: !this.state.exibindoDisponiveis});
    }

    getHistoricoDisponiveis() {
        return [
            {
                "nome": "Jose",
                "naoVistas": 0
            },
            {
                "nome": "Maria",
                "naoVistas": 0
            },
            {
                "nome": "Joao",
                "naoVistas": 12
            },
            {
                "nome": "Carlos",
                "naoVistas": 0
            }
        ]
    }

    getHistoricoConversas() {
        return [
            {
                "nome": "Lilly",
                "naoVistas": 2
            },
            {
                "nome": "Joe",
                "naoVistas": 0
            },
            {
                "nome": "Emily",
                "naoVistas": 12
            },
            {
                "nome": "Kai",
                "naoVistas": 5
            }
        ]
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
                    <Conversation className='conversation' unreadCnt={elemento.naoVistas}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        } else {
            for(let elemento of this.state.historicoConversas) {
                elementosLista.push(
                    <Conversation className='conversation' unreadCnt={elemento.naoVistas}>
                        <Avatar name={elemento.nome}/>
                    </Conversation>
                )
            }
        }
        return elementosLista;
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
                        <Message
                            model={{
                                message: "Hello my friend",
                                sentTime: "just now",
                                direction: 'incoming'
                            }}
                        />
                        <Message
                            model={{
                                message: "Hello my friend",
                                sentTime: "just now",
                                direction: 'outgoing'
                            }}
                        />
                    </MessageList>
                    <MessageInput attachButton={false} placeholder="Digite sua mensagem..." />
                </ChatContainer>
            </MainContainer>
        </div>;
    }
}

export default Chat;