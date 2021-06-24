import React from "react";
import "./Login.css";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';
import UsuarioService from "../../services/UsuarioService";
import md5 from 'md5';

class Login extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nomeUsuario: '',
            senha: '',
            mensagemErro: false
        };
        this._usuarioService = new UsuarioService();
        this.validarFormulario  = this.validarFormulario.bind(this);
        this.logar = this.logar.bind(this);
        this.setNomeUsuario  = this.setNomeUsuario.bind(this);
        this.setSenha  = this.setSenha.bind(this);
        this.mensagemErro = this.mensagemErro.bind(this);
    }

    validarFormulario() {
        return this.state.nomeUsuario.length > 0 && this.state.senha.length > 0;
    }

    logar(event) {
        event.preventDefault();
        this._usuarioService.logarUsuario(this.state.nomeUsuario, md5(this.state.senha))
            .then((idUsuario) => {
                localStorage.setItem('idUsuario', idUsuario);
                this.setState({mensagemErro: false});
                window.location.href+='chat';
            })
            .catch((err) => {
                console.log(err);
                this.setState({mensagemErro: true});
            });
    }

    setNomeUsuario(valor) {
        this.setState({
            nomeUsuario: valor.target.value
        });
    }

    setSenha(valor) {
        this.setState({
            senha: valor.target.value
        });
    }

    mensagemErro() {
        if(this.state.mensagemErro) {
            return <div className="form-group">
                     <p className="erro">Login inválido. Verifique o nome de usuário e senha.</p>
                   </div>;
        } else {
            return <div></div>;
        }
    }

    render() {
        return <div className="outer">
                <div className="inner">
                    <form>
                        <h3>Log in</h3>
                        <div className="form-group">
                            <label>Nome de Usuário</label>
                            <input type="text" className="form-control" placeholder="Digite o nome de usuário" value={this.state.nomeUsuaro} onChange={this.setNomeUsuario} />
                        </div>
                        <div className="form-group">
                            <label>Senha</label>
                            <input type="password" className="form-control" placeholder="Digite a senha" value={this.state.senha} onChange={this.setSenha}/>
                        </div>
                        <div className="container-button">
                            <button type="submit" className="btn btn-dark btn-lg btn-block" onClick={this.logar}>Logar</button>
                            <a href="/cadastro" className="btn btn-dark btn-lg btn-block">Cadastrar</a>
                        </div>
                        <div>
                            {this.mensagemErro()}
                        </div>
                    </form>
                </div>
            </div>;
    }

}

export default Login;