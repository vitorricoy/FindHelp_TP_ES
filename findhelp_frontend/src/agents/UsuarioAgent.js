import * as Constantes from '../util/Constantes';
import axios from 'axios';

class ChatAgent {

    constructor() {
        this.logarUsuario = this.logarUsuario.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
    }

    logarUsuario(nomeUsuario, senha) {
        return new Promise((resolve, reject) => {
            axios.get(Constantes.host + Constantes.login, {
                params: {
                    nomeUsuario: nomeUsuario,
                    senha: senha
                }})
                .then(response => resolve(response.data))
                .catch(err => reject(err));
        });
    }

    enviarMensagem(mensagem, idUsuario, idContato) {
        return new Promise((resolve, reject) => {
            axios.post(Constantes.host + Constantes.chat, {
                conteudo: mensagem,
                idRemetente: idUsuario,
                idDestinatario: idContato
            })
                .then(response => resolve(response.data))
                .catch((err) => reject(err));
        });
    }
}

export default ChatAgent;