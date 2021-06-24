import React from "react";
import "./Cadastro.css";
import '../../../node_modules/bootstrap/dist/css/bootstrap.min.css';

class Cadastro extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            nomeUsuario: '',
            senha: ''
        };
    }

    componentDidMount() {
        this.validarFormulario  = this.validarFormulario.bind(this);
        this.handleSubmit  = this.handleSubmit.bind(this);
        this.setNomeUsuario  = this.setNomeUsuario.bind(this);
        this.setSenha  = this.setSenha.bind(this);
    }

    validarFormulario() {
        return this.state.nomeUsuario.length > 0 && this.state.senha.length > 0;
    }

    handleSubmit(event) {
        event.preventDefault();
    }

    setNomeUsuario(valor) {
        this.setState({
            nomeUsuario: valor
        });
    }

    setSenha(valor) {
        this.setState({
            senha: valor
        });
    }

    render() {
        return <div className="outer">
            <div className="inner">
                <form>
                    <h3>Cadastro</h3>

                    <div className="form-group">
                        <label>Nome Completo</label>
                        <input type="text" className="form-control" placeholder="Digite o nome completo" />
                    </div>

                    <div className="form-group">
                        <label>Nome de Usuário</label>
                        <input type="email" className="form-control" placeholder="Digite o nome de usuário" />
                    </div>

                    <div className="form-group">
                        <label>Senha</label>
                        <input type="password" className="form-control" placeholder="Digite a senha" />
                    </div>

                    <div className="form-group">
                        <input type="checkbox" className="form-check-input" id="flexCheckDefault"/>
                        <label className="form-check-label" htmlFor="flexCheckDefault">
                            Sou um psicólogo
                        </label>
                    </div>

                    <button type="submit" className="btn btn-dark btn-lg btn-block">Cadastrar</button>
                </form>
            </div>
        </div>;
    }

}

export default Cadastro;