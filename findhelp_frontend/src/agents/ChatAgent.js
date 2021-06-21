import * as Constantes from '../util/Constantes';
import axios from 'axios';

class ChatAgent {

    constructor() {
        this.obterHistoricoConversas = this.obterHistoricoConversas.bind(this);
        this.enviarMensagem = this.enviarMensagem.bind(this);
        this.obterMensagensConversa = this.obterMensagensConversa.bind(this);
        this.marcarMensagensVistas = this.marcarMensagensVistas.bind(this);
    }

    obterHistoricoConversas(idUsuario) {
        return new Promise((resolve, reject) => {
            axios.get(Constantes.host + Constantes.historico, {
                params: {
                    idUsuario: idUsuario
                }})
                .then(response => resolve(response.data.total))
                .catch((err) => reject(err));
        });
    }

    enviarMensagem(mensagem, idUsuario, idContato) {
        return new Promise((resolve, reject) => {
            axios.post(Constantes.host + Constantes.chat, {
                params: {
                    conteudo: mensagem,
                    idRemetente: idUsuario,
                    idDestinatario: idContato
                }})
                .then(response => resolve(response.data.total))
                .catch((err) => reject(err));
        });
    }

    obterMensagensConversa(idUsuario, idContato) {
        return new Promise((resolve, reject) => {
            axios.get(Constantes.host + Constantes.chat, {
                params: {
                    idUsuario: idUsuario,
                    idContato: idContato
                }})
                .then(response => resolve(response.data.total))
                .catch((err) => reject(err));
        });
    }

    marcarMensagensVistas(idUsuario, idContato) {
        return new Promise((resolve, reject) => {
            axios.post(Constantes.host + Constantes.visualizarMensagens, {
                params: {
                    idUsuario: idUsuario,
                    idContato: idContato
                }})
                .then(response => resolve(response.data.total))
                .catch((err) => reject(err));
        });
    }
}

export default ChatAgent;