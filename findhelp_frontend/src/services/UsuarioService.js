import UsuarioAgent from "../agents/UsuarioAgent";

class ChatService {

    constructor() {
        this._usuarioAgent = new UsuarioAgent();
        this.logarUsuario = this.logarUsuario.bind(this);
        this.cadastrarUsuario = this.cadastrarUsuario.bind(this);
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

    cadastrarUsuario(nomeCompleto, nomeUsuario, senha, psicologo) {
        return new Promise((resolve, reject) => {
            this._usuarioAgent.cadastrarUsuario(nomeCompleto, nomeUsuario, senha, psicologo)
                .then((idUsuario) => {
                    resolve(idUsuario);
                })
                .catch((err) => {
                    reject(err);
                })
        });
    }
}

export default ChatService;