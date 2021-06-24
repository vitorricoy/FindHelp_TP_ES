import React from "react";
import "./Cadastro.css";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import md5 from "md5";
import UsuarioService from "../../services/UsuarioService";

class Cadastro extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nomeUsuario: '',
            nomeCompleto: '',
            senha: '',
            psicologo: false
        };
        this._usuarioService = new UsuarioService();
        this.cadastrar  = this.cadastrar.bind(this);
        this.setNomeUsuario  = this.setNomeUsuario.bind(this);
        this.setNomeCompleto  = this.setNomeCompleto.bind(this);
        this.setSenha  = this.setSenha.bind(this);
        this.setPsicologo  = this.setPsicologo.bind(this);
    }

    cadastrar(event) {
        event.preventDefault();
        this._usuarioService.cadastrarUsuario(this.state.nomeCompleto, this.state.nomeUsuario, md5(this.state.senha), this.state.psicologo)
            .then((idUsuario) => {
                localStorage.setItem('idUsuario', idUsuario);
                this._usuarioService.logarUsuario(this.state.nomeUsuario, md5(this.state.senha))
                    .then((sucesso) => {
                        let url = window.location.href;
                        let tokens = url.split('/');
                        tokens.pop();
                        window.location.href = tokens.join('/')+'/chat';
                    })
                    .catch((err) => {
                        console.log(err);
                    });
            })
            .catch((err) => {
                console.log(err);
            });
    }

    setNomeUsuario(valor) {
        this.setState({
            nomeUsuario: valor.target.value
        });
    }

    setNomeCompleto(valor) {
        this.setState({
            nomeCompleto: valor.target.value
        });
    }

    setSenha(valor) {
        this.setState({
            senha: valor.target.value
        });
    }

    setPsicologo(valor) {
        this.setState({
            psicologo: valor.target.checked
        });
    }

    render() {
        return <div className="outer">
            <div className="inner">
                <form>
                    <h3>Cadastro</h3>

                    <div className="form-group">
                        <label>Nome Completo</label>
                        <input type="text" className="form-control" placeholder="Digite o nome completo" value={this.state.nomeCompleto} onChange={this.setNomeCompleto}/>
                    </div>

                    <div className="form-group">
                        <label>Nome de Usuário</label>
                        <input type="email" className="form-control" placeholder="Digite o nome de usuário" value={this.state.nomeUsuario} onChange={this.setNomeUsuario}/>
                    </div>

                    <div className="form-group">
                        <label>Senha</label>
                        <input type="password" className="form-control" placeholder="Digite a senha" value={this.state.senha} onChange={this.setSenha}/>
                    </div>

                    <div className="form-group">
                        <input type="checkbox" className="form-check-input" id="psicologoCheck" checked={this.state.psicologo} onChange={this.setPsicologo}/>
                        <label className="form-check-label" htmlFor="psicologoCheck">
                            Sou um psicólogo
                        </label>
                    </div>

                    <button type="submit" onClick={this.cadastrar} className="btn btn-dark btn-lg btn-block">Cadastrar</button>
                </form>
            </div>
        </div>;
    }

}

export default Cadastro;