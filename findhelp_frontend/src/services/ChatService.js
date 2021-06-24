import ChatAgent from "../agents/ChatAgent";
import UsuarioAgent from "../agents/UsuarioAgent";

class ChatService {

    constructor() {
        this._chatAgent = new ChatAgent();
        this._usuarioAgent = new UsuarioAgent();
        this.obterPsicologosDisponiveis = this.obterPsicologosDisponiveis.bind(this);
        this.obterHistoricoConversas = this.obterHistoricoConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.obterMensagensConversa = this.obterMensagensConversa.bind(this);
        this.marcarMensagensVistas = this.marcarMensagensVistas.bind(this);
    }

    obterPsicologosDisponiveis() {
        return new Promise((resolve, reject) => {
            this._usuarioAgent.obterPsicologosDisponiveis()
                .then((historicoConversa) => {
                    resolve(historicoConversa);
                })
                .catch((err) => {
                    reject(err);
                });
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
                });
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