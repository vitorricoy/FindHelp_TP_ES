import ChatAgent from "../agents/ChatAgent";

class ChatService {

    constructor() {
        this._chatAgent = new ChatAgent();
        this.getDisponiveis = this.getDisponiveis.bind(this);
        this.obterHistoricoConversas = this.obterHistoricoConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.obterMensagensConversa = this.obterMensagensConversa.bind(this);
        this.marcarMensagensVistas = this.marcarMensagensVistas.bind(this);
    }

    getDisponiveis() {
        return new Promise((resolve, reject) => {

        });
    }

    obterHistoricoConversas() {
        return new Promise((resolve, reject) => {
            let idUsuario = localStorage.getItem('idUsuario')
            this._chatAgent.obterHistoricoConversas(idUsuario)
                .then((historicoConversa) => {
                    resolve(historicoConversa);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }

    enviarMensagem(mensagem, idContato) {
        return new Promise((resolve, reject) => {
            let idUsuario = localStorage.getItem('idUsuario')
            this._chatAgent.enviarMensagem(mensagem, idUsuario, idContato)
                .then((idMensagem) => {
                    resolve(idMensagem);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }

    obterMensagensConversa(idContato) {
        return new Promise((resolve, reject) => {
            let idUsuario = localStorage.getItem('idUsuario')
            this._chatAgent.obterMensagensConversa(idUsuario, idContato)
                .then((mensagens) => {
                    resolve(mensagens);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }

    marcarMensagensVistas(idContato) {
        return new Promise((resolve, reject) => {
            let idUsuario = localStorage.getItem('idUsuario')
            this._chatAgent.marcarMensagensVistas(idUsuario, idContato)
                .then((sucesso) => {
                    resolve(sucesso);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }
}

export default ChatService;