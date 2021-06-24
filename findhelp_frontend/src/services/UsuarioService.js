import UsuarioAgent from "../agents/UsuarioAgent";

class ChatService {

    constructor() {
        this._usuarioAgent = new UsuarioAgent();
        this.logarUsuario = this.logarUsuario.bind(this);
    }

    logarUsuario(nomeUsuario, senha) {
        return new Promise((resolve, reject) => {
            this._usuarioAgent.logarUsuario(nomeUsuario, senha)
                .then((idUsuario) => {
                    resolve(idUsuario);
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