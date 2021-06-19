import ChatAgent from "../agents/ChatAgent";

class ChatService {

    constructor() {
        this._chatAgent = new ChatAgent();
        this.getDisponiveis.bind(this);
        this.getHistoricoConversas.bind(this);
        this.enviarMensagem.bind(this);
        this.getMensagensConversa.bind(this);
    }

    getDisponiveis() {
        return new Promise((resolve, reject) => {

        });
    }

    getHistoricoConversas() {
        return new Promise((resolve, reject) => {
            let idUsuario = localStorage.getItem('idUsuario')
            this._chatAgent.getHistoricoConversas(idUsuario)
                .then((historicoConversa) => {
                    resolve(historicoConversa);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }

    enviarMensagem(mensagem) {
        return new Promise((resolve, reject) => {

        });
    }

    getMensagensConversa() {
        return new Promise((resolve, reject) => {

        });
    }
}

export default ChatService;