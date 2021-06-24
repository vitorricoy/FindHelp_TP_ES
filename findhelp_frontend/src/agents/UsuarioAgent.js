import * as Constantes from '../util/Constantes';
import axios from 'axios';

class ChatAgent {

    constructor() {
        this.logarUsuario = this.logarUsuario.bind(this);
        this.cadastrarUsuario = this.cadastrarUsuario.bind(this);
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

    cadastrarUsuario(nomeCompleto, nomeUsuario, senha, psicologo) {
        return new Promise((resolve, reject) => {
            axios.post(Constantes.host + Constantes.usuario, {
                nome: nomeCompleto,
                nomeUsuario: nomeUsuario,
                senha: senha,
                psicologo: psicologo
            })
                .then(response => resolve(response.data))
                .catch((err) => reject(err));
        });
    }

    deslogarUsuario(idUsuario) {
        return new Promise((resolve, reject) => {
            axios.post(Constantes.host + Constantes.deslogar, {
                idUsuario: idUsuario
            })
                .then(response => resolve(response.data))
                .catch((err) => reject(err));
        });
    }

    obterPsicologosDisponiveis() {
        return new Promise((resolve, reject) => {
            axios.get(Constantes.host + Constantes.ativos)
                .then(response => resolve(response.data))
                .catch(err => reject(err));
        });
    }
}

export default ChatAgent;