import * as Constantes from '../util/Constantes';
import axios from 'axios';

class ChatAgent {

    constructor() {
        this.getHistoricoConversas.bind(this);
        this.enviarMensagem.bind(this);
        this.getMensagensConversa.bind(this);
    }

    getHistoricoConversas(idUsuario) {
        return new Promise((resolve, reject) => {
            axios.get(Constantes.host + Constantes.historico, {
                params: {
                    idUsuario: idUsuario
                }})
                .then(response => resolve(response.data.total))
                .catch((err) => reject(err));
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

export default ChatAgent;